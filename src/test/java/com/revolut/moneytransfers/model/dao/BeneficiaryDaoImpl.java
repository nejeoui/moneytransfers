package com.revolut.moneytransfers.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.revolut.moneytransfers.model.Beneficiary;

public class BeneficiaryDaoImpl implements BeneficiaryDao {
	EntityManagerFactory emf = null;
	EntityManager em = null;

	public BeneficiaryDaoImpl() {
		emf = Persistence.createEntityManagerFactory("RevolutUnit1");
		em = emf.createEntityManager();
	}

	@Override
	public Beneficiary save(Beneficiary beneficiary) {
		try {
			em.getTransaction().begin();
			em.persist(beneficiary);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beneficiary;
	}

}
