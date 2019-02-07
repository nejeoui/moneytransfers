package com.revolut.moneytransfers.test;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class InjectionRunner extends BlockJUnit4ClassRunner {
	private SeContainer seContainer;

	/**
	 * instantiates a BlockJUnit4ClassRunner to run {@code clazz}
	 *
	 * @throws InitializationError
	 */
	public InjectionRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
		SeContainerInitializer initializer = SeContainerInitializer.newInstance();
		seContainer = initializer.initialize();
	}

	/**
	 * dependencies injection
	 */
	@Override
	protected Object createTest() throws Exception {
		Object testObject = super.createTest();
		return seContainer.select(testObject.getClass()).get();
	}
}