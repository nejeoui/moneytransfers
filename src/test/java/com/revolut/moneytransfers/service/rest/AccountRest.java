package com.revolut.moneytransfers.service.rest;


import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.service.AccountService;

@Path("/Account")
public class AccountRest {
	private AccountService accountService;
	//TODO Make this container available for the whole application.
	private SeContainer seContainer;
	{
		SeContainerInitializer initializer=SeContainerInitializer.newInstance();
		try{
			seContainer=initializer.initialize();
			Instance<AccountService> LazyAccountService=seContainer.select(AccountService.class);
			 accountService=LazyAccountService.get();
			 System.out.println(accountService.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Path("/list")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAccountsByBeneficiaryPhone(){
		String phone="123456";
		System.out.println("layer1");
		List<Account> accounts=null;
		try {
        accounts=accountService.selectByBeneficiaryPhone(phone);
        }catch (Exception e) {
			e.printStackTrace();
		}
        if(accounts!=null) return accounts;
        else return new ArrayList<Account>();
    }
	
}
