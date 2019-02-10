package com.revolut.moneytransfers.model.dao;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.revolut.moneytransfers.model.Beneficiary;
/**
 * An implementation of {@code BeneficiaryDao} {@code Interface} to handle CRUDE for
 * the {@code Beneficiary} {@code Entity}
 * <p>
 * This implementation use JPA 2.2 with Resource Local Transactions
 * <p>
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see BenefeciaryDao
 * @since 1.0
 */
@ApplicationScoped
public class BeneficiaryDaoImpl implements BeneficiaryDao {
	
	/**
	 * Self4j Logger
	 */
	@Inject
	private transient  Logger logger;
	
	@Inject
	EntityManager em = null;

	@Override
	public Beneficiary save(Beneficiary beneficiary) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(beneficiary);
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return beneficiary;
	}

	@Override
	public Optional<Beneficiary> find(String phone) throws Exception {
		Beneficiary beneficiary=null;
		try{
			if(!em.getTransaction().isActive())
				em.getTransaction().begin();
		 beneficiary=	em.find(Beneficiary.class, phone);
		 if(em.getTransaction().isActive())
		 em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			logger.error(e.getMessage());
		}
		return Optional.ofNullable(beneficiary);
	}

}
