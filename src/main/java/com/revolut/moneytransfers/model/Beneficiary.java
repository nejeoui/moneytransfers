package com.revolut.moneytransfers.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Beneficiary {
	@Id
	private String phone;
	@OneToMany(mappedBy="beneficiary")
	List<Account> accounts;
	
	@Column(nullable=false)
	private String firstName;
	private String middletName;
	@Column(nullable=false)
	private String lastName;
	@Embedded
	private Address address;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddletName() {
		return middletName;
	}
	public void setMiddletName(String middletName) {
		this.middletName = middletName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Deprecated
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
