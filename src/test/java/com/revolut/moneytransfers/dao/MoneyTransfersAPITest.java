package com.revolut.moneytransfers.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.ws.rs.ext.RuntimeDelegate;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Before;
import org.junit.Test;

import com.revolut.moneytransfers.AccountDaoImpl;
import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.model.Beneficiary;
import com.revolut.moneytransfers.service.AccountService;
import com.revolut.moneytransfers.service.rest.AccountRest;
import com.revolut.moneytransfers.service.rest.HelloRest;
import com.revolut.moneytransfers.test.RestServer;
import com.revolut.moneytransfers.util.HttpUtil;

public class MoneyTransfersAPITest {
	@Test
	public void create2AccountSameBeneficiary() {
		AccountDaoImpl accountDao = new AccountDaoImpl();
		// Generate database Schema
//		accountDao.initDB();
		// First Beneficiary
		String phone = "21266012346";
		String firstName = "Abderrazzak";
		String lastName = "Nejeoui";
		Beneficiary beneficiary = new Beneficiary(phone, firstName, lastName);
		// Account 1
		String currency = "GBP";
		String country = "United Kingdom";
		String label = "GBP UK";
		String iban = "IBAN Test 123 456";
		String bic = "BIC Test 789 012";
		Account account1 = new Account(beneficiary, currency, country, label, iban, bic);
		// Account 2 same Beneficiary as Account1
		Account account2 = new Account(beneficiary, "EUR", country, "EUR France", iban + 2, bic + 2);
		accountDao.save(account1);
		accountDao.save(account2);

		// reteive all accounts from DB
		List<Account> accounts = accountDao.selectByBeneficiaryPhone(phone);
		assertEquals(accounts.size(), 2);

		// retreive account1 from DB
		Account account_1 = accountDao.selectByBeneficiaryPhoneAndCurrency(beneficiary.getPhone(), currency).get();

		assertEquals(account1, account_1);

		// Second Beneficiary
		String phone2 = "44000000";
		String firstName2 = "Xname";
		String lastName2 = "Henry";
		Beneficiary beneficiary2 = new Beneficiary(phone2, firstName2, lastName2);

		// Account3
		String currency2 = "GBP";
		String country2 = "United Kingdom";
		String label2 = "GBP UK";
		String iban2 = "IBAN Test 000 000";
		String bic2 = "BIC Test 999 999";
		Account account3 = new Account(beneficiary2, currency2, country2, label2, iban2, bic2);
		accountDao.save(account3);
		List<Account> accounts2 = accountDao.selectAll();
		assertEquals(accounts2.size(), 3);
		Account account_3 = accountDao.selectByBeneficiaryPhoneAndCurrency(beneficiary2.getPhone(), currency2).get();
		assertEquals(account3, account_3);

	}

	@Test
	public void transfer() {
		fail("Not implemented");

	}

	@Test
	public void topUpAccount() {
		fail("Not implemented");

	}

	@Test
	public void testCDI() {
		System.out.println("CDI Test Start");
		Account account = new Account(new Beneficiary("000000", "Fname", "Lname"), "GBP", "UK", "label acc",
				"IBAN XXXXX", "BIC XXXX");
		Account accountFromDB = null;
		SeContainerInitializer initializer = SeContainerInitializer.newInstance();
		try (SeContainer seContainer = initializer.initialize()) {
			Instance<AccountService> LazyAccountService = seContainer.select(AccountService.class);
			AccountService accountService = LazyAccountService.get();
			accountService.save(account);
			accountFromDB = accountService.selectByBeneficiaryPhoneAndCurrency(account.getAccountID().getPhone(),
					account.getAccountID().getCurrency()).get();
		seContainer.close();
		}
		assertNotNull(accountFromDB);
		System.out.println("CDI Test End");

	}
	@Test
	public void testJaxRs() {
		String hello;
		try {
			hello = HttpUtil.get("http://localhost:8080/hello");
			assertEquals("Hello Jax-RS", hello);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	

}
