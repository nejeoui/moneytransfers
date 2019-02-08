package com.revolut.moneytransfers.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.enterprise.inject.se.SeContainer;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.jboss.weld.environment.se.Weld;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.model.AccountID;
import com.revolut.moneytransfers.model.Beneficiary;
import com.revolut.moneytransfers.service.AccountService;
import com.revolut.moneytransfers.service.TransferService;
import com.revolut.moneytransfers.service.rest.AccountRest;
import com.revolut.moneytransfers.service.rest.TopUpParams;
import com.revolut.moneytransfers.service.rest.TransferDTO;
import com.revolut.moneytransfers.service.rest.TransferRest;

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
		return new ResourceConfig(AccountRest.class, TransferRest.class);
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
	public void testJaxRsGetAccounts() {
		logger.info("start testJaxRs");
		// Forth Beneficiary
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

	/**
	 * 
	 */
	@Test
	public void JaxResTestCreateAccount() {
		// Fifth Beneficiary
		String phone5 = "555555555";
		String firstName5 = "FifthName";
		String lastName5 = "FifthLastName";
		Beneficiary beneficiary5 = new Beneficiary(phone5, firstName5, lastName5);

		// Account5
		String currency5 = "EUR";
		String country5 = "Spain";
		String label5 = "EUR Spain";
		String iban5 = "IBAN Test 777 000";
		String bic5 = "BIC Test 777 999";
		Account account5 = new Account(beneficiary5, currency5, country5, label5, iban5, bic5);
		// invoke Account/newAccount endPoint to create a new Account from JSON
		target("Account/newAccount").request().put(Entity.json(account5));
		String response = target("Account/" + phone5).request().get(String.class);
		logger.info("response Entity : " + response);
		Assert.assertTrue(response.contains(iban5));

	}

	/**
	 * 
	 */
	@Test
	public void JaxResTestTopUpAccount() {
		// Sixth Beneficiary
		String phone6 = "66666666";
		String firstName6 = "SixthName";
		String lastName6 = "SixthLastName";
		Beneficiary beneficiary6 = new Beneficiary(phone6, firstName6, lastName6);
		// Account5
		String currency6 = "EUR";
		String country6 = "Italy";
		String label6 = "EUR Italy";
		String iban6 = "IBAN Italy 667 109";
		String bic6 = "BIC Italy 544 000";
		Account account6 = new Account(beneficiary6, currency6, country6, label6, iban6, bic6);
		// invoke Account/newAccount endPoint to create a new Account from JSON
		target("Account/newAccount").request().put(Entity.json(account6));
		double newAmount = 7690.96;
		TopUpParams topUpParams = new TopUpParams(phone6, currency6, newAmount);
		// invoke Account/topUpAccount endPoint twice to topUp the sixth account by
		// 7690.96*2
		target("Account/topUpAccount").request().post(Entity.json(topUpParams));
		target("Account/topUpAccount").request().post(Entity.json(topUpParams));
		// invoke Account/ endPoint to retrieve the modified sixth account
		String response = target("Account/" + phone6).request().get(String.class);
		logger.info("topUp response Message : " + response);
		// test the new balance
		Assert.assertTrue(response.contains(String.valueOf(newAmount * 2)));

	}

	/**
	 * 
	 */
	@Test
	public void JaxResTestMoneyTransfer() {
		// Seventh Beneficiary
		String phone7 = "777777";
		String firstName7 = "SeventhName";
		String lastName7 = "SeventhLastName";
		Beneficiary beneficiary7 = new Beneficiary(phone7, firstName7, lastName7);

// Account7
		String currency7 = "EUR";
		String country7 = "Germany";
		String label7 = "EUR Germany";
		String iban7 = "IBAN Germany 890 000";
		String bic7 = "BIC Germany 890 999";
		Account account7 = new Account(beneficiary7, currency7, country7, label7, iban7, bic7);
// invoke Account/newAccount endPoint to create a new Account from JSON
		target("Account/newAccount").request().put(Entity.json(account7));
		String response = target("Account/" + phone7).request().get(String.class);
		logger.info("response Entity : " + response);
		Assert.assertTrue(response.contains(iban7));

//Eighth Beneficiary
		String phone8 = "888888";
		String firstName8 = "EighthName";
		String lastName8 = "EighthLastName";
		Beneficiary beneficiary8 = new Beneficiary(phone8, firstName8, lastName8);

//Account8
		String currency8 = "CAD";
		String country8 = "Canada";
		String label8 = "CAD CANADA";
		String iban8 = "IBAN Canada 773 000";
		String bic8 = "BIC Canada 773 999";
		Account account8 = new Account(beneficiary8, currency8, country8, label8, iban8, bic8);
//invoke Account/newAccount endPoint to create a new Account from JSON
		target("Account/newAccount").request().put(Entity.json(account8));
		String response2 = target("Account/" + phone8).request().get(String.class);
		logger.info("response Entity : " + response2);
		Assert.assertTrue(response2.contains(iban8));

		double newAmount = 67859.86;
		TopUpParams topUpParams = new TopUpParams(phone7, currency7, newAmount);
// invoke Account/topUpAccount endPoint twice to topUp the sixth account by 7690.96*2
		try {
			target("Account/topUpAccount").request().post(Entity.json(topUpParams));

			TransferDTO transferDTO = new TransferDTO(new AccountID(phone7, currency7),
					new AccountID(phone8, currency8), 450.0, "TR18906RevoT666", "Test Transfer");
			logger.info("Test instance TransferDTO : " + transferDTO.toString());
			Response rsp = target("Transfers/transfer/").request().put(Entity.json(transferDTO));
			logger.info("rsp ::: " + rsp.toString());
			Account account_7 = accountService.selectByBeneficiaryPhoneAndCurrency(phone7, currency7).get();
			Account account_8 = accountService.selectByBeneficiaryPhoneAndCurrency(phone8, currency8).get();
			logger.info("account_7 = " + account_7);
			logger.info("account_8 = " + account_8);
			assertEquals(account_7.getBalance(), 67859.86 - 450.0, 0.00000000000001);
			assertEquals(account_8.getBalance(), 0 + 450.0 * TransferService.DUMMYTRANSFER_RATE, 0.00000000000001);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			fail(e.getMessage());
		}

	}
}
