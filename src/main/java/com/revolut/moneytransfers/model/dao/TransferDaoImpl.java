package com.revolut.moneytransfers.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.slf4j.Logger;

import com.revolut.moneytransfer.dao.jta.ResourceLocal;
import com.revolut.moneytransfers.exception.IllegalRevolutTransactionException;
import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.model.AccountID;
import com.revolut.moneytransfers.model.Transfer;

/**
 * An implementation of {@code TransferDao} {@code Interface} to handle CRUDE
 * for the {@code Transfer} {@code Entity}
 * <p>
 * This implementation use JPA 2.2 with Resource Local Transactions
 * <p>
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see TransferDao
 * @since 1.0
 */
public class TransferDaoImpl implements TransferDao {
	@Inject
	@ResourceLocal
	EntityManager em = null;
	/**
	 * Self4j Logger
	 */
	@Inject
	private transient Logger logger;

	/**
	 *  Create a new {@code Transfer}
	 *  Both Sender Account and Receiver Account are locked with {@code LockModeType.PESSIMISTIC_WRITE} to prevent dirty read or write
	 *  sender.getCurrency() = exchangeRate  receiver.getCurrency()
	 *  
	 */
	@Override
	public int transfer(AccountID sender, AccountID receiver, double amount,String reference, String comment, double exchangeRate) throws Exception {
       
		try {
			
			em.getTransaction().begin();
			/**
			 * Debited account and Credited account are acquired with PESSIMISTIC_WRITE Lock
			 */
			Account toBeCredeited = em.find(Account.class, receiver,LockModeType.PESSIMISTIC_WRITE);
			Account toBeDebited = em.find(Account.class, sender,LockModeType.PESSIMISTIC_WRITE);
			// debtor balance not authorized by Revolut
			if((toBeDebited.getBalance()-amount)>=0)
			{toBeDebited.setBalance(toBeDebited.getBalance()-amount);
			toBeCredeited.setBalance(toBeCredeited.getBalance()+amount*exchangeRate);
			}
			else throw new IllegalRevolutTransactionException("debtor balance not authorized by Revolut");
			
			Transfer transfer=new Transfer(toBeDebited, toBeCredeited, amount, reference, comment, new Date().getTime(),exchangeRate);
			em.persist(transfer);
			em.merge(toBeCredeited);
			em.merge(toBeDebited);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			logger.error(e.getMessage());
			return 0;
		}
		return 1;
	}

	@Override
	public boolean isPersistent(Transfer transfer) throws Exception {
		if(transfer==null)
		return false;
		return transfer.getId()!=0;
	}

}
