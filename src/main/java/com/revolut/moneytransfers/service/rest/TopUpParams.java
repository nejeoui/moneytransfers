package com.revolut.moneytransfers.service.rest;

import lombok.Data;

@Data
public class TopUpParams {
private String phone;
private String currency;
private double amount;
public TopUpParams() {
	super();
}
public TopUpParams(String phone, String currency, double amount) {
	super();
	this.phone = phone;
	this.currency = currency;
	this.amount = amount;
}

}
