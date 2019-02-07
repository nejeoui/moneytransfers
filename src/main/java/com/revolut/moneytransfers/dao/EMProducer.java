package com.revolut.moneytransfers.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EMProducer {

    @Inject
    transient EntityManagerFactory emf;

    @Produces
    @ApplicationScoped
    public EntityManager create() {
        return emf.createEntityManager();
    }

    public void destroy(@Disposes EntityManager em) {
        em.close();
    }
}