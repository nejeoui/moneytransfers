package com.revolut.moneytransfers.model.dao;

import com.revolut.moneytransfers.model.AccountID;

public interface TransferDao {
int transfer(AccountID sender,AccountID receiver, double amount,String reference, String comment, double exchangeRate) throws Exception;
}
