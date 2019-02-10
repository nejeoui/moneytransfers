package com.revolut.moneytransfers.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;

/**
 * A Money {@code Transfer} between two Revolut {@code Account}
 * <p>
 * Each {@code Account} is bound to a single currency and a unique
 * {@code Beneficiary}
 * <p>
 * Each {@code Account} has a unique combination of (IBAN,BIC)
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see Beneficiary
 * @see Account
 * @since 1.0
 */
@Data
@Entity
public class Transfer implements Serializable {
	/**
	 * serialVersionUID
	 */
	@Transient
	private transient static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long id;
	@ManyToOne
	@JoinColumns({
	@JoinColumn(name="debitedPhone",referencedColumnName="phone",nullable=false,updatable=false),@JoinColumn(name="debitedCurrency",referencedColumnName="currency",nullable=false,updatable=false)})
	private Account debitedAccount;
	@JoinColumns({
		@JoinColumn(name="creditedPhone",referencedColumnName="phone",nullable=false,updatable=false),@JoinColumn(name="creditedCurrency",referencedColumnName="currency",nullable=false,updatable=false)})
	@ManyToOne
	private Account creditedAccount;
	@Column(nullable=false,updatable=false)
	private double amount;
	private String reference;
	@Column(length = 3600)
	private String comment;
	@Column(nullable=false,updatable=false)
	private long time;
	@Column(nullable=false,updatable=false)
	private double exchangeRate;

	@Deprecated
	public Transfer() {
		super();
	}

	public Transfer(Account debitedAccount, Account creditedAccount, double amount, String reference, String comment,
			long time, double exchangeRate) {
		super();
		this.debitedAccount = debitedAccount;
		this.creditedAccount = creditedAccount;
		this.amount = amount;
		this.reference = reference;
		this.comment = comment;
		this.time = time;
		this.exchangeRate = exchangeRate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || !(obj instanceof Transfer)) {
			return false;
		}
		Transfer transfer = (Transfer) obj;
		return this.id == transfer.id;
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
	
	public boolean isPersisted() {
		return id!=0;
	}

}
