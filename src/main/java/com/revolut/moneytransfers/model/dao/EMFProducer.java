package com.revolut.moneytransfers.model.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFProducer {

    @Produces
    @ApplicationScoped
    public EntityManagerFactory create() {
        return Persistence.createEntityManagerFactory("RevolutUnit1");
    }

    
    public void destroy(@Disposes EntityManagerFactory factory) {
        factory.close();
    }

}