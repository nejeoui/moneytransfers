package com.revolut.moneytransfer.dao.jta;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Produces;

import org.slf4j.Logger;

import com.revolut.moneytransfers.model.Beneficiary;
import com.revolut.moneytransfers.model.dao.BeneficiaryDao;
@ApplicationScoped
@Produces
public class BeneficiaryDaoJTAImpl  {

	/**
	 * JTA EntityManager
	 */
	@Inject
    @JTAQualifier
    private EntityManager em;
	
	/**
	 * Self4j Logger
	 */
	@Inject
	private transient Logger logger;
	
	@Transactional
	public Beneficiary save(Beneficiary beneficiary) throws Exception {
		logger.info("JTA Persist ");
		assert em!=null; 
		em.persist(beneficiary);
		return beneficiary;
	}

	public Optional<Beneficiary> find(String phone) throws Exception {
		if(phone==null) return Optional.ofNullable(null);
		return Optional.ofNullable(em.find(Beneficiary.class, phone));
	}
	
	public void emptyDb() throws Exception {
        assert em != null;
        findAll().forEach(em::remove);
    }

	public List<Beneficiary> findAll() throws Exception{
		 assert em != null;
		 List<Beneficiary> beneficiaries=em.createNamedQuery("Beneficiary.selectAll", Beneficiary.class).getResultList();
		return beneficiaries;
	}

	public boolean isPersistent(Beneficiary beneficiary) throws Exception {
		if(beneficiary==null)
		return false;
		return em.find(Beneficiary.class, beneficiary.getPhone())!=null;
	}

}
