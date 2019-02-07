package com.revolut.moneytransfers.model;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "iban", "bic" }) })
public class Account {
	@EmbeddedId
	private AccountID accountID;
	
	/**
	 *  the account beneficiary.
	 *
	 */
	@MapsId("phone")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountHolder_phone", referencedColumnName = "phone", insertable = false, updatable = false, nullable = false)
	private Beneficiary beneficiary;
	private String country;
	private String label;
	private String iban;
	private String bic;
	private double balance;
	@OneToMany(mappedBy = "creditedAccount")
	private List<Transfer> credits;

	@OneToMany(mappedBy = "debitedAccount")
	private List<Transfer> debits;

	
	
	public Account() {
		super();
	}

	public Account(Beneficiary beneficiary, String currency, String country, String label, String iban, String bic)
			throws IllegalArgumentException {
		super();
		if (beneficiary == null || country == null || iban == null || bic == null)
			throw new IllegalArgumentException("null values ");
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
		if (!(obj instanceof Account))
			return false;
		Account account = (Account) obj;
		return beneficiary.getPhone().equals(account.getBeneficiary().getPhone())
				&& this.getAccountID().getCurrency().equals(account.getAccountID().getCurrency());
	}
	@Override
	public int hashCode() {
		return this.accountID.hashCode();
	}
}
