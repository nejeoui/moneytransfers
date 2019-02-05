package com.revolut.moneytransfers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.ManyToOne;

@Entity
public class Transfer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long id;
	@ManyToOne
	private Account debitedAccount;
	@ManyToOne
	private Account creditedAccount;
	private double amount;
	private String reference;
	@Column(length = 3600)
	private String comment;
	private long time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Account getDebitedAccount() {
		return debitedAccount;
	}

	public void setDebitedAccount(Account debitedAccount) {
		this.debitedAccount = debitedAccount;
	}

	public Account getCreditedAccount() {
		return creditedAccount;
	}

	public void setCreditedAccount(Account creditedAccount) {
		this.creditedAccount = creditedAccount;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Transfer() {
		super();
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Transfer(Account debitedAccount, Account creditedAccount, double amount, String reference, String comment,
			long time) {
		super();
		this.debitedAccount = debitedAccount;
		this.creditedAccount = creditedAccount;
		this.amount = amount;
		this.reference = reference;
		this.comment = comment;
		this.time = time;
	}

}
