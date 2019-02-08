package com.revolut.moneytransfers.service.rest;

import com.revolut.moneytransfers.model.AccountID;

import lombok.Data;
@Data
public class TransferDTO {
	private AccountID debitedAccountID;
	private AccountID creditedAccountID;
	private double amount;
	private String reference;
	private String comment;
	public TransferDTO(AccountID debitedAccountID, AccountID creditedAccountID, double amount, String reference,
			String comment) {
		super();
		this.debitedAccountID = debitedAccountID;
		this.creditedAccountID = creditedAccountID;
		this.amount = amount;
		this.reference = reference;
		this.comment = comment;
	}
	
	public TransferDTO() {
		super();
	}
	
}
