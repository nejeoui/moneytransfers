package com.revolut.moneytransfer.dao.jta;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
/**
 * 
 * Produces an {@code EntityManager} with JTA managed transactions
 * <p>
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see EntityManager
 * @since 1.0
 */
public class EMJTAProducer {
		@Inject
	    @JTAQualifier
	    transient EntityManagerFactory emfJTA;
		
		@Produces
	    @ApplicationScoped
	    @JTAQualifier
	    public EntityManager create() {
	    	
	        return emfJTA.createEntityManager();
	    }
		
		    public void destroy(@Disposes @JTAQualifier  EntityManager em) {
		        em.close();
		    }
}
