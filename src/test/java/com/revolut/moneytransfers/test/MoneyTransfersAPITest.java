package com.revolut.moneytransfers.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.enterprise.inject.se.SeContainer;
import javax.inject.Inject;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.jboss.weld.environment.se.Weld;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.model.Beneficiary;
import com.revolut.moneytransfers.service.AccountService;
import com.revolut.moneytransfers.service.rest.AccountRest;
import com.revolut.moneytransfers.util.HttpUtil;

/**
 * Junit4 test class
 * <p>
 * This implementation use a custom {@code BlockJUnit4ClassRunner} to initialize
 * the CDI Context {@codeSeContainer}
 * <p>
 * 
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see InjectionRunner
 * @see SeContainer
 * @since 1.0
 */
@RunWith(InjectionRunner.class)
public class MoneyTransfersAPITest extends JerseyTest {
	/**
	 * AccountService instance
	 */
	@Inject
	AccountService accountService;

	/**
	 * Self4j Logger
	 */
	@Inject
	private transient Logger logger;

	@Override
	protected Application configure() {
		Weld weld = new Weld();
		weld.initialize();
		return new ResourceConfig(AccountRest.class);
	}

	@Test
	public void create2AccountSameBeneficiary() {
		Beneficiary beneficiary1 = new Beneficiary();
		String phone = "21266012346";
		String firstName = "Abderrazzak";
		String lastName = "Nejeoui";
		beneficiary1.setPhone(phone);
		beneficiary1.setFirstName(firstName);
		beneficiary1.setLastName(lastName);
		// Account 1
		String currency1 = "GBP";
		String country1 = "United Kingdom";
		String label1 = "GBP UK";
		String iban1 = "IBAN Test 123 456";
		String bic1 = "BIC Test 789 012";

		Account account1 = new Account(beneficiary1, currency1, country1, label1, iban1, bic1);

		// Account 2 same Beneficiary as Account1

		String currency2 = "EUR";
		String country2 = "France";
		String label2 = "EUR France";
		String iban2 = "IBAN Test 222 42256";
		String bic2 = "BIC Test 222 03312";

		Account account2 = new Account(beneficiary1, currency2, country2, label2, iban2, bic2);

		try {
			accountService.save(account1);
			accountService.save(account2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}

		try {
			// reteive all accounts from DB
			List<Account> accounts = accountService.selectByBeneficiaryPhone(phone);
			assertTrue(accounts.size() >= 2);

			// retreive account1 from DB
			Account account_1 = accountService.selectByBeneficiaryPhoneAndCurrency(beneficiary1.getPhone(), currency1)
					.get();

			assertEquals(account1, account_1);
		} catch (Exception e) {
			fail(e.getMessage());
			logger.error(e.getMessage());
		}
		// Second Beneficiary
		String phone2 = "44000000";
		String firstName2 = "Xname";
		String lastName2 = "Henry";
		Beneficiary beneficiary2 = new Beneficiary(phone2, firstName2, lastName2);

		// Account3
		String currency3 = "GBP";
		String country3 = "United Kingdom";
		String label3 = "GBP UK";
		String iban3 = "IBAN Test 000 000";
		String bic3 = "BIC Test 999 999";

		Account account3 = new Account(beneficiary2, currency3, country3, label3, iban3, bic3);

		try {
			accountService.save(account3);
			List<Account> accounts2 = accountService.selectAll();
			assertTrue(accounts2.size() >= 3);
			Account account_3 = accountService.selectByBeneficiaryPhoneAndCurrency(beneficiary2.getPhone(), currency3)
					.get();
			assertEquals(account3, account_3);
		} catch (Exception e) {
			logger.error(e.getMessage());
			fail(e.getMessage());
		}

	}

//	@Test
	public void transfer() {
		fail("Not implemented");

	}

//	@Test
	public void topUpAccount() {
		fail("Not implemented");

	}

	@Test
	public void testCDI() throws Exception {

		logger.info("CDI Test Start");
		Account account = new Account(new Beneficiary("000000", "Fname", "Lname"), "GBP", "UK", "label acc",
				"IBAN XXXXX", "BIC XXXX");
		Account accountFromDB = null;
		try {
			accountService.save(account);
			accountFromDB = accountService.selectByBeneficiaryPhoneAndCurrency(account.getAccountID().getPhone(),
					account.getAccountID().getCurrency()).get();

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		assertNotNull(accountFromDB);
		assertEquals(account, accountFromDB);
		logger.info("CDI Test End");

	}

	@Test
	public void testJaxRs() {
		String hello;
		logger.info("start testJaxRs");
		// Second Beneficiary
		String phone4 = "0101010101";
		String firstName4 = "BinaryNAme";
		String lastName4 = "Octal";
		Beneficiary beneficiary4 = new Beneficiary(phone4, firstName4, lastName4);
		// Account4
		String currency4 = "MAD";
		String country4 = "MOROCCO";
		String label4 = "MAD MOROCCO";
		String iban4 = "IBAN Test 980 000";
		String bic4 = "BIC Test 786 999";

		Account account4 = new Account(beneficiary4, currency4, country4, label4, iban4, bic4);

		try {
			accountService.save(account4);
			List<Account> accounts4 = accountService.selectAll();
			assertTrue(accounts4.size() >= 1);
			Account account_4 = accountService.selectByBeneficiaryPhoneAndCurrency(beneficiary4.getPhone(), currency4)
					.get();
			assertEquals(account4, account_4);
			String response = target("Account/" + phone4).request().get(String.class);
			logger.info("response : " + response);
			Assert.assertTrue(response.contains(iban4));
		} catch (Exception e) {
			logger.error(e.getMessage());
			fail(e.getMessage());
		}

	}

}
