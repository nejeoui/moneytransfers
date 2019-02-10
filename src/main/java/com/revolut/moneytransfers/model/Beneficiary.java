package com.revolut.moneytransfers.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Beneficiary implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="phone")
	private String phone;
	@OneToMany(mappedBy = "beneficiary", cascade = CascadeType.ALL)
	List<Account> accounts=new ArrayList<Account>();
	@Column(nullable = false)
	private String firstName;
	private String middletName;
	@Column(nullable = false)
	private String lastName;
	@Embedded
	private Address address;
	public void add(Account account) {
		accounts.add(account);
		account.setBeneficiary(this);
	}
	public void remove(Account account) {
		accounts.remove(account);
		account.setBeneficiary(null);
	}
	public Beneficiary() {
		super();
	}

	public Beneficiary(String phone, String firstName, String lastName) {
		super();
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || !(obj instanceof Beneficiary)) {
			return false;
		}
		Beneficiary beneficiary = (Beneficiary) obj;
		if (this.phone==null||beneficiary.phone==null) {
			return false;
		}
		return this.phone.equals(beneficiary.phone);
	}

	@Override
	public int hashCode() {
		return   this.phone != null ? this.phone.hashCode() : 0;
	}
}
