package com.revolut.moneytransfers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.model.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {
	EntityManagerFactory emf=null;
	EntityManager em=null;
	public AccountDaoImpl() {
		emf=Persistence.createEntityManagerFactory("RevolutUnit1");
		em=emf.createEntityManager();
	}
	@Override
	public Account save(Account account) {
		try {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> selectByBeneficiaryPhone(String phone) {
		System.out.println(phone+" Phone traversed 3 layers here !");
		Query query=em.createNamedQuery("Account.selectByBeneficiaryPhone");
		query.setParameter("phone1", phone);
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> selectAll() {
		Query query=em.createNamedQuery("Account.selectAll");
		return query.getResultList();
	}
	@Override
	public Optional<Account> selectByBeneficiaryPhoneAndCurrency(String phone, String currency) {
		Query query=em.createNamedQuery("Account.selectByBeneficiaryPhoneAndCurrency");
		query.setParameter("phone1", phone);
		query.setParameter("currency1", currency);
		Account account=null;
		try {
		account=	(Account)query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(account);
	}
}
