package com.revolut.moneytransfers.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;

import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.model.AccountID;
import com.revolut.moneytransfers.model.Beneficiary;
import com.revolut.moneytransfers.model.dao.AccountDao;

/**
 * An implementation of {@code AccountDao} {@code Interface} to handle CRUDE for
 * the {@code Account} {@code Entity}
 * <p>
 * This implementation use JPA 2.2 with Resource Local Transactions
 * <p>
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see AccountDao
 * @since 1.0
 */
@ApplicationScoped
public class AccountDaoImpl implements AccountDao {
	@Inject
	EntityManager em = null;
	/**
	 * Self4j Logger
	 */
	@Inject
	private transient Logger logger;

	@Override
	public Account save(Account account) throws Exception {
		try {
			System.out.println("phone hhhhh="+account.getAccountID().getPhone());
			if(!em.getTransaction().isActive())
			em.getTransaction().begin();
			Beneficiary beneficiary=em.find(Beneficiary.class, account.getAccountID().getPhone());
			if(null!=beneficiary)
			{	beneficiary.add(account);
			em.merge(beneficiary);}
			else {
				em.persist(account.getBeneficiary());
				account.setBeneficiary(account.getBeneficiary());
				em.persist(account);
			}
			if(em.getTransaction().isActive())
			em.getTransaction().commit();
			logger.info(account.getAccountID().toString() + " persisted");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return account;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> selectByBeneficiaryPhone(String phone) throws Exception {
		try {
			
			Query query = em.createNamedQuery("Account.selectByBeneficiaryPhone");
			query.setParameter("phone1", phone);
			List<Account> accounts=query.getResultList();
			return accounts;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("selectByBeneficiaryPhone fail " + e.getMessage());
			return Collections.EMPTY_LIST;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> selectAll() throws Exception {
		try {
		Query query = em.createNamedQuery("Account.selectAll");
		List<Account> accounts=query.getResultList();
		return accounts;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("selectAll fail " + e.getMessage());
			return Collections.EMPTY_LIST;
		}
	}

	@Override
	public Optional<Account> findAccountByID(String phone, String currency) throws Exception {
		Query query = em.createNamedQuery("Account.selectByBeneficiaryPhoneAndCurrency");
		query.setParameter("phone1", phone);
		query.setParameter("currency1", currency);
		Account account = null;
		try {
			account = (Account) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(account);
	}

	@Override
	public Optional<Account> topUp(Account account) {
		Account account_DB = null;
		try {
			em.getTransaction().begin();
			account_DB = em.find(Account.class, account.getAccountID(), LockModeType.PESSIMISTIC_WRITE);
			if (account_DB != null) {
				account_DB.setBalance(account.getBalance());
				em.merge(account);
			}
			em.getTransaction().commit();
			logger.info(account.getAccountID().toString() + " persisted");
		} catch (Exception e) {
			logger.error("topUp"+e.getMessage());
			em.getTransaction().rollback();
		}
		return Optional.ofNullable(account);
	}
}
