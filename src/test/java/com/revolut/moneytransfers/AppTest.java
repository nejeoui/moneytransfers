package com.revolut.moneytransfers;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * test java 8 lambda expression
	 */
	public void testApp() {
		List<Integer> ints = Arrays.asList(5, 6, 9, 89, 2);
		ints.sort((a1, a2) -> {
			return (a1 > a2) ? -1 : (a1 == a2) ? 0 : 1;
		});
		assertEquals(89, ints.get(0).intValue());
	}
}
