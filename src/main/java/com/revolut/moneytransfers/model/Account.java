package com.revolut.moneytransfers.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/**
 * A Revolut {@code Account}
 * <p>
 * Each {@code Account} is bound to a single currency and a unique
 * {@code Beneficiary} identified by phone
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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "iban", "bic" }),
		@UniqueConstraint(columnNames = { "phone", "currency" }) })
public class Account implements Serializable {
	/**
	 * serialVersionUID
	 */
	@Transient
	private transient static final long serialVersionUID = 1L;
	/**
	 * the account ID as embedded object which embed the beneficiary phone and
	 * currency
	 */
	@EmbeddedId
	private AccountID accountID;

	/**
	 * the account beneficiary. the @MapsId annotation handle the phone property in
	 * the embeddedId accountID.
	 * <p>
	 * the phone property is not set on the constructor
	 * {@link com.revolut.moneytransfers.model.Account#Account(Beneficiary, String, String, String, String, String)}
	 * line 78
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
		// used just for testing purpose, @MapsID handle the phone property injection
		// AccountID accountID = new AccountID(beneficiary.getPhone(), currency);
		AccountID accountID = new AccountID(currency);
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
	// used just for testing purpose, @MapsID handle the phone property injection
//	public void setBeneficiary(Beneficiary beneficiary) {
//		this.beneficiary=beneficiary;
//		this.getAccountID().setPhone(beneficiary.getPhone());
//	}
}
