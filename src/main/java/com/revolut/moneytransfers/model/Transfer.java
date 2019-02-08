package com.revolut.moneytransfers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
		this.exchangeRate=exchangeRate;
	}

}
