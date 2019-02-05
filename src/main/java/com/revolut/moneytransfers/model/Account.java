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
@NamedQuery(name="Account.selectAll",query="SELECT a FROM Account a ")	
@NamedQuery(name="Account.selectByBeneficiaryPhone",query="SELECT a FROM Account a WHERE a.beneficiary.phone = :phone1")	
@NamedQuery(name="Account.selectByBeneficiaryPhoneAndCurrency",query="SELECT a FROM Account a WHERE a.beneficiary.phone = :phone1 AND a.accountID.currency= :currency1")	
@Entity
/*
 * 
 * */
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"iban", "bic"})
	}) 
public class Account {
	@EmbeddedId
	private AccountID accountID;
	@MapsId("phone")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="accountHolder_phone", referencedColumnName="phone",insertable=false, updatable=false,nullable=false)
	private Beneficiary beneficiary; 
	private String country;
	private String label;
	private String iban;
	private String bic;
	@OneToMany(mappedBy="creditedAccount")
	private List<Transfer> credits;
	
	@OneToMany(mappedBy="debitedAccount")
	private List<Transfer> debits;
	
	
	private double balance;

	public Beneficiary getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(Beneficiary beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public List<Transfer> getCredits() {
		return credits;
	}

	public void setCredits(List<Transfer> credits) {
		this.credits = credits;
	}

	public List<Transfer> getDebits() {
		return debits;
	}

	public void setDebits(List<Transfer> debits) {
		this.debits = debits;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
	

	public AccountID getAccountID() {
		return accountID;
	}

	public void setAccountID(AccountID accountID) {
		this.accountID = accountID;
	}

	@Deprecated
	public Account() {
		super();
	}

	public Account(Beneficiary beneficiary, String currency, String country, String label, String iban, String bic) throws IllegalArgumentException {
		super();
		if(beneficiary==null||country==null
			||iban==null||bic==null)throw new IllegalArgumentException("null values ");
		AccountID accountID=new AccountID( currency);
		this.accountID=accountID;
		this.beneficiary = beneficiary;
		this.country = country;
		this.label = label;
		this.iban = iban;
		this.bic = bic;
		this.balance = 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Account)) return false;
		Account account=(Account)obj;
		return beneficiary.getPhone().equals(account.getBeneficiary().getPhone())&&this.getAccountID().getCurrency().equals(account.getAccountID().getCurrency());
	}
	
}
