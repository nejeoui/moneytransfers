package com.revolut.moneytransfers.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * A Revolut {@code Account} holder
 * <p>
 * a {@code Beneficiary} may have several Revolut {@code Account}
 * <p>
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see Account
 * @see Transfer
 * @since 1.0
 */
@Data
@Entity
public class Beneficiary {
	@Id
	private String phone;
	@OneToMany(mappedBy = "beneficiary")
	List<Account> accounts;

	@Column(nullable = false)
	private String firstName;
	private String middletName;
	@Column(nullable = false)
	private String lastName;
	@Embedded
	private Address address;

	public Beneficiary() {
		super();
	}

	public Beneficiary(String phone, String firstName, String lastName) {
		super();
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
