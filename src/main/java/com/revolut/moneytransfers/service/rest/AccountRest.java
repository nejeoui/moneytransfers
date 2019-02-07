package com.revolut.moneytransfers.service.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.service.AccountService;

@Path("/Account")
public class AccountRest {
	@Inject
	private AccountService accountService;
	

	@GET
	@Path("{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getAccountsByBeneficiaryPhone(@PathParam("phone") String phone) {
		System.out.println("layer1");
		List<Account> accounts = null;
		try {
			accounts = accountService.selectByBeneficiaryPhone(phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (accounts != null)
			return accounts;
		else
			return new ArrayList<Account>();
	}

}
