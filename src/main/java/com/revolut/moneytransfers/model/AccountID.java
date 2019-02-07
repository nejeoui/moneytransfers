package com.revolut.moneytransfers.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class AccountID implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String phone;
	private String currency;

	public AccountID() {
		super();
	}

	public AccountID(String currency) {
		super();
		this.currency = currency;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AccountID))
			return false;
		AccountID accountID = (AccountID) obj;
		return phone.equals(accountID.phone) && currency.equals(accountID.currency);
	}

	@Override
	public int hashCode() {
		return new StringBuilder(phone).append(currency).toString().hashCode();
	}

}
