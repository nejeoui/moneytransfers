package com.revolut.moneytransfers.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import lombok.Data;

@Embeddable
@Data
public class AccountID implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	@Transient
	private transient static final long serialVersionUID = 1L;
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
		if (obj == this)
			return true;
		if (obj == null || !(obj instanceof AccountID)) {
			return false;
		}
		AccountID accountID = (AccountID) obj;
		if (phone == null ||currency==null||accountID.phone==null||accountID.currency==null)
		{
			return false;
		}
		return phone.equals(accountID.phone) && currency.equals(accountID.currency);
	}

	@Override
	public int hashCode() {
		return (phone != null || currency != null) ? new StringBuilder((phone != null) ? phone : "")
				.append((currency != null) ? currency : "").toString().hashCode() : 0;
	}

	public AccountID(String phone, String currency) {
		super();
		this.phone = phone;
		this.currency = currency;
	}

}
