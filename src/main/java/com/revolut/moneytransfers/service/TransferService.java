package com.revolut.moneytransfers.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.revolut.moneytransfers.model.AccountID;
import com.revolut.moneytransfers.model.dao.TransferDao;

@ApplicationScoped
public class TransferService {
	public static final double DUMMYTRANSFER_RATE = 1.57;
	@Inject
	TransferDao transferDao;
	public int transfer(AccountID sender,AccountID receiver, double amount,String reference, String comment) throws Exception{
		
		return transferDao.transfer(sender, receiver, amount, reference, comment, getCurrentExcangeRate(sender.getCurrency(), receiver.getCurrency()));
	}
	/**
	 *  a Dummy implementation of getExchangeRateService
	 * @param fromCurrency String
	 * @param toCurrency String
	 * @return the current Exchange rate 
	 */
	private double getCurrentExcangeRate(String fromCurrency, String toCurrency) {
		//a Dummy implementation of getExchangeRateService
		return DUMMYTRANSFER_RATE;
	}

}
