package com.revolut.moneytransfers.model.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.revolut.moneytransfer.dao.jta.JTAQualifier;
import com.revolut.moneytransfer.dao.jta.ResourceLocal;
/**
 * 
 * Produces an {@code EntityManager} with Resource Local managed transactions
 * <p>
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see EntityManager
 * @since 1.0
 */
public class EMProducer {

    @Inject
    @ResourceLocal
    transient EntityManagerFactory emf;
    
   

    @Produces
    @ApplicationScoped
    @ResourceLocal
    public EntityManager createRL() {
        return emf.createEntityManager();
    }
    
    
    public void destroy(@Disposes @ResourceLocal  EntityManager em) {
        em.close();
    }
   
}