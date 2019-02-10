package com.revolut.moneytransfers.model.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.revolut.moneytransfer.dao.jta.JTAQualifier;
import com.revolut.moneytransfer.dao.jta.ResourceLocal;
/**
 * 
 * Produces an {@code EntityManagerFactory} with Resource Local managed transactions
 * <p>
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see EntityManagerFactory
 * @since 1.0
 */
public class EMFProducer {

    @Produces
    @ApplicationScoped
    @ResourceLocal
    public EntityManagerFactory createRL() {
        return Persistence.createEntityManagerFactory("RevolutUnit1");
    }
    
    public void destroy(@Disposes @ResourceLocal  EntityManagerFactory factory) {
        factory.close();
    }
    
   

}