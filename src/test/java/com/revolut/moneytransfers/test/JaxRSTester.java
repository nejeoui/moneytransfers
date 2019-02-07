package com.revolut.moneytransfers.test;

import javax.inject.Inject;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.revolut.moneytransfers.service.AccountService;

@RunWith(InjectionRunner.class)
public class JaxRSTester extends JerseyTest {
	
	/**
	 * AccountService instance
	 */
	@Inject
	AccountService accountService;

	@Inject
	private Logger logger ;

	
	
	

	

}
