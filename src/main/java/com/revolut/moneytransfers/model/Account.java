package com.revolut.moneytransfers.model;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/**
 * A Revolut {@code Account}
 * <p>
 * Each {@code Account} is bound to a single currency and a unique
 * {@code Beneficiary}
 * <p>
 * Each {@code Account} has a unique combination of (IBAN,BIC)
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see Beneficiary
 * @see Transfer
 * @since 1.0
 */
@NamedQuery(name = "Account.selectAll", query = "SELECT a FROM Account a ")
@NamedQuery(name = "Account.selectByBeneficiaryPhone", query = "SELECT a FROM Account a WHERE a.beneficiary.phone = :phone1")
@NamedQuery(name = "Account.selectByBeneficiaryPhoneAndCurrency", query = "SELECT a FROM Account a WHERE a.beneficiary.phone = :phone1 AND a.accountID.currency= :currency1")
@Data
@Entity
/*
 * 
 * */
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "iban", "bic" }),@UniqueConstraint(columnNames = { "phone", "currency" }) })
public class Account {
	@EmbeddedId
	private AccountID accountID;

	/**
	 * the account beneficiary.
	 *
	 */
	@MapsId("phone")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "phone")
	private Beneficiary beneficiary;
	private String country;
	private String label;
	private String iban;
	private String bic;
	private double balance;

	public Account() {
		super();
	}

	public Account(Beneficiary beneficiary, String currency, String country, String label, String iban, String bic)
			throws IllegalArgumentException {
		super();
		if (beneficiary == null || country == null || iban == null || bic == null)
			throw new IllegalArgumentException("null values ");
		AccountID accountID = new AccountID(beneficiary.getPhone(), currency);
		this.accountID = accountID;
		this.beneficiary = beneficiary;
		this.country = country;
		this.label = label;
		this.iban = iban;
		this.bic = bic;
		this.balance = 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || !(obj instanceof Account)) {
			return false;
		}
		Account account = (Account) obj;
		if (this.accountID == null || account.getAccountID() == null) {
			return false;
		}
		return this.getAccountID().equals(account.getAccountID());
	}

	@Override
	public int hashCode() {
		return this.accountID != null ? this.accountID.hashCode() : 0;
	}

	public void topUp(double amount) {
		if (amount > 0)
			this.balance = this.balance + amount;
	}
	public void setBeneficiary(Beneficiary beneficiary) {
		this.beneficiary=beneficiary;
		this.getAccountID().setPhone(beneficiary.getPhone());
	}
}
