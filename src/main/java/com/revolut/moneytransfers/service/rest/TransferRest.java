package com.revolut.moneytransfers.service.rest;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.service.AccountService;
import com.revolut.moneytransfers.service.TransferService;

@Path("/Transfers")
public class TransferRest {

	@Inject
	private TransferService transferService;

	@Inject
	private AccountService accountService;

	/**
	 * Self4j Logger
	 */
	@Inject
	private transient Logger logger;

	/**
	 * 
	 * @param account in Json format
	 * @return OK 200 response if the new account is created successfully, Otherwise
	 *         returns the status error message
	 */
	@Path("/transfer")
	@PUT
	@Consumes("application/json")
	public Response createNewTransfer(TransferDTO transferDTO) {
		int r=0;
		if (transferDTO == null || transferDTO.getAmount() <= 0 || transferDTO.getCreditedAccountID() == null
				|| transferDTO.getDebitedAccountID() == null) {
			logger.info("Invalide Accounts: ");
			return Response.status(Status.BAD_REQUEST).build();
		}
		logger.info("transferDTO: "+transferDTO);
		Account toBeDebitedAccount = null;
		Account toBeCreditedAccount = null;
		Optional<Account> toBeDebitedAccountOptional = null;
		Optional<Account> toBeCreditedAccountOptional = null;
		try {
			toBeDebitedAccountOptional = accountService.findAccountByID(
					transferDTO.getDebitedAccountID().getPhone(), transferDTO.getDebitedAccountID().getCurrency());

			toBeCreditedAccountOptional = accountService.findAccountByID(
					transferDTO.getCreditedAccountID().getPhone(), transferDTO.getCreditedAccountID().getCurrency());
		} catch (Exception e) {
			logger.info("Invalide Accounts: ");
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (toBeDebitedAccountOptional.isPresent())
			toBeDebitedAccount = toBeDebitedAccountOptional.get();
		if (toBeCreditedAccountOptional.isPresent())
			toBeCreditedAccount = toBeCreditedAccountOptional.get();

		if (toBeDebitedAccount == null || toBeCreditedAccount == null) {
			logger.info("Invalide Accounts: ");
			return Response.status(Status.BAD_REQUEST).build();
		}
		try {
			logger.info("PUT TransferDTO : " + transferDTO.toString());
			r=transferService.transfer(transferDTO.getDebitedAccountID(), transferDTO.getCreditedAccountID(),
					transferDTO.getAmount(), transferDTO.getReference(), transferDTO.getComment());
		if(r==0)return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.info("Invalide Transfer: ");
			e.printStackTrace();
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(e.getMessage()).build();
		}

		return Response.status(Status.OK).build();
	}
}
