package com.revolut.moneytransfers.service.rest;

import java.util.List;
import java.util.Optional;

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
import com.revolut.moneytransfers.model.AccountID;
import com.revolut.moneytransfers.service.AccountService;

@Path("/Account")
public class AccountRest {
	/**
	 * AccountService offering CRUD 
	 */
	@Inject
	private AccountService accountService;

	/**
	 * Self4j Logger
	 */
	@Inject
	private transient Logger logger;
	/**
	 * 
	 * @param phone String
	 * @return List<Account> in Json format  if OK, Otherwise Response with Error Status
	 *         returns the status error message
	 */
	@GET
	@Path("{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountsByBeneficiaryPhone(@PathParam("phone") String phone) {
		List<Account> accounts = null;
		try {
			accounts = accountService.selectByBeneficiaryPhone(phone);
		} catch (Exception e) {
			logger.info("selectByBeneficiaryPhone fail: " + e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		if (accounts != null)
			return Response.ok(accounts, MediaType.APPLICATION_JSON).build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();
	}
	/**
	 * 
	 * @param phone String
	 * @return List<Account> in Json format  if OK, Otherwise Response with Error Status
	 *         returns the status error message
	 */
	@GET
	@Path("AllAccounts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAccounts() {
		List<Account> accounts = null;
		try {
			accounts = accountService.selectAll();
		} catch (Exception e) {
			logger.info("selectByBeneficiaryPhone fail: " + e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		if (accounts != null)
			return Response.ok(accounts, MediaType.APPLICATION_JSON).build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();
	}
	/**
	 * 
	 * @param phone String
	 * @return List<Account> in Json format  if OK, Otherwise Response with Error Status
	 *         returns the status error message
	 */
	@POST
	@Path("/id")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountsByBeneficiaryPhoneAndCurrency(AccountID accountID) {
		if(accountID==null||accountID.getPhone()==null||accountID.getCurrency()==null)
		{
			logger.info(" /id POST Entity is null: ");
			return Response.status(Status.BAD_REQUEST).build();
		}
		logger.info(" /id POST Entity =  "+accountID);
		Account account = null;
		try {
		Optional<Account>	accountOptional = accountService.findAccountByID(accountID.getPhone(), accountID.getCurrency());
		if(accountOptional.isPresent()) account=accountOptional.get();
		} catch (Exception e) {
			logger.info("selectByBeneficiaryPhoneAndCurrency fail: " + e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		if (account != null)
			return Response.ok(account, MediaType.APPLICATION_JSON).build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();
	}

	/**
	 * 
	 * @param account in Json format
	 * @return CREATED 201 response if the new account is created successfully, Otherwise
	 *         returns the status error message
	 */
	@Path("/newAccount")
	@PUT
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewAccount(Account account) {
		if (account == null||account.getAccountID()==null) {
			logger.info("PUT Entity is null: ");
			return Response.status(Status.BAD_REQUEST).build();
		}
		try {
			logger.info("PUT Entity : " + account.toString());
			account = accountService.save(account);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

		return Response.status(Status.CREATED).build();
	}

	/**
	 * 
	 * @param TopUpParams in Json format
	 * @return CREATED  201 response if the balance has been credited successfully, Otherwise
	 *         returns the status error message
	 */
	@Path("/topUpAccount")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response topUpAccount(TopUpParams topUpParams) {
		if (topUpParams==null||topUpParams.getAmount() < 0) {
			logger.info("Negative amount ");
			return Response.status(Status.BAD_REQUEST).entity("Negative amount ").build();
		}
		try {
			logger.info(" topUpParams : " + topUpParams.toString());
			Optional<Account> accountOptional = accountService
					.findAccountByID(topUpParams.getPhone(), topUpParams.getCurrency());
			if(accountOptional.isPresent()) {
				Account account= accountOptional.get();
				account.topUp(topUpParams.getAmount());
				accountService.topUp(account);	
			}
			else {
				/**
				 * in case the account not found
				 */
				logger.info("Account not found ");
				return Response.status(Response.Status.NOT_FOUND).entity(topUpParams.toString()).build();	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

		return Response.status(Status.CREATED).build();
	}

}
