package com.revolut.moneytransfer.dao.jta;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.revolut.moneytransfers.model.dao.AccountDaoImpl;
/**
 * 
 * Produces an {@code EntityManagerFactory} with JTA managed transactions
 * <p>
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see EntityManagerFactory
 * @since 1.0
 */
public class EMFJTAProducer {
	 	@Produces
	    @ApplicationScoped
	    @JTAQualifier
	    public EntityManagerFactory create() {
	        return Persistence.createEntityManagerFactory("RevolutJTAUnit");
	    }
	    public void destroy(@Disposes @JTAQualifier  EntityManagerFactory factory) {
	        factory.close();
	    }
	    
}
