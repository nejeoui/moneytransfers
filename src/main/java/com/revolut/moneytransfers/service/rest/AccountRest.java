package com.revolut.moneytransfers.service.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.service.AccountService;

@Path("/Account")
public class AccountRest {
	@Inject
	private AccountService accountService;
	
	/**
	 * Self4j Logger
	 */
	@Inject
	private transient Logger logger;

	@GET
	@Path("{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getAccountsByBeneficiaryPhone(@PathParam("phone") String phone) {
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
	/**
	 * 
	 * @param account in Json format
	 * @return OK 200 response if the new account is created successfully, Otherwise returns the status error message
	 */
	@Path("/newAccount")
	@PUT
	@Consumes("application/json")
	public Response createNewAccount( Account account) {
		if (account == null) {
			logger.info("PUT Entity is null: ");
			return Response.status(Status.BAD_REQUEST).build();
		}
		try {
			logger.info("PUT Entity : "+account.toString());
			account = accountService.save(account);
		} catch (Exception e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getMessage()).build();
		}

		return Response.status(Status.OK).build();
	}
	/**
	 * 
	 * @param account in Json format
	 * @return OK 200 response if the new account is created successfully, Otherwise returns the status error message
	 */
	@Path("/topUpAccount")
	@POST
	@Consumes("application/json")
	public Response topUpAccount(TopUpParams topUpParams) {
		if (topUpParams.getAmount() < 0) {
			logger.info("Negative amount ");
			return Response.status(Status.BAD_REQUEST).build();
		}
		try {
			logger.info(" topUpParams : "+topUpParams.toString());
		Account	account = accountService.selectByBeneficiaryPhoneAndCurrency(topUpParams.getPhone(), topUpParams.getCurrency()).get();
		account.topUp(topUpParams.getAmount());
		accountService.topUp(account);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage()).build();
		}

		return Response.status(Status.OK).build();
	}

}
