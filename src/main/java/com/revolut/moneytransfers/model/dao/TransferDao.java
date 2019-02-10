package com.revolut.moneytransfers.model.dao;

import com.revolut.moneytransfers.model.AccountID;
import com.revolut.moneytransfers.model.Beneficiary;
import com.revolut.moneytransfers.model.Transfer;

public interface TransferDao {
	
	/**
	 * Create a new Transfer
	 * 
	 * @param sender       AccountID.
	 * @param receiver     AccountID.
	 * @param amount       double.
	 * @param reference    String.
	 * @param comment      String.
	 * @param exchangeRate double.
	 * @throws Exception
	 */
int transfer(AccountID sender,AccountID receiver, double amount,String reference, String comment, double exchangeRate) throws Exception;

/**
 * 
 * @param transfer {@code Transfer}
 * @return true if the transfer exist in DB. Otherwise returns false;
 * @throws Exception
 */
public boolean isPersistent(Transfer transfer ) throws Exception;

}
