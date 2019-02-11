package com.revolut.moneytransfers.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.enterprise.inject.se.SeContainer;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.TransactionManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.jboss.weld.environment.se.Weld;
import org.jnp.server.NamingBeanImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arjuna.ats.arjuna.recovery.RecoveryManager;
import com.arjuna.ats.internal.jta.recovery.arjunacore.XARecoveryModule;
import com.arjuna.ats.jta.utils.JNDIManager;
import com.revolut.moneytransfer.dao.jta.BeneficiaryDaoJTAImpl;
import com.revolut.moneytransfer.dao.jta.JtaPropertyManager;
import com.revolut.moneytransfer.dao.jta.RecoveryPropertyManager;
import com.revolut.moneytransfer.dao.jta.RevolutXAResource;
import com.revolut.moneytransfer.dao.jta.RevolutXAResourceRecoveryHelper;
import com.revolut.moneytransfer.dao.jta.TransactionalProvider;
import com.revolut.moneytransfers.App;
import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.model.AccountID;
import com.revolut.moneytransfers.model.Beneficiary;
import com.revolut.moneytransfers.service.AccountService;
import com.revolut.moneytransfers.service.TransferService;
import com.revolut.moneytransfers.service.rest.AccountRest;
import com.revolut.moneytransfers.service.rest.BeneficiaryRest;
import com.revolut.moneytransfers.service.rest.TopUpParams;
import com.revolut.moneytransfers.service.rest.TransferDTO;
import com.revolut.moneytransfers.service.rest.TransferRest;

/**
 * Junit4 test class
 * <p>
 * This implementation use a custom {@code BlockJUnit4ClassRunner} to initialize
 * the CDI Context {@codeSeContainer} and the {@code JerseyTest} Framework for
 * Testing JaxRS
 * <p>
 * 
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see InjectionRunner
 * @see SeContainer
 * @see JerseyTest
 * @see InjectionRunner
 * @since 1.0
 */
@RunWith(InjectionRunner.class)
public class MoneyTransfersAPITest extends JerseyTest {
	/**
	 * Delta value for comparing doubles with assertEquals
	 */
	private static final double DELTA = 1e-15;

	private static final GenericType<List<Account>> ListAccountsGeneriqType = new GenericType<List<Account>>() {
	};

	/**
	 * AccountService instance for the sake of CDI Test
	 */
	@Inject
	AccountService accountService;
	/**
	 * BeneficiaryDaoJTAImpl instance for Testing JTA
	 */
	@Inject
	BeneficiaryDaoJTAImpl beneficiaryDaoJTAImpl;

	/**
	 * Self4j Logger
	 */
	private static transient Logger logger = LoggerFactory.getLogger(App.class);
	/**
	 * JBoss CDI 2.0 Container
	 */
	private static Weld weld;

	/**
	 * Jersey Server supporting
	 * <a href="https://jcp.org/en/jsr/detail?id=370">JAX-RS 2.1 (JSR 370) </a>
	 */
	private static final HttpServer server = HttpServer.createSimpleServer(null, 8080);

	/**
	 * JNDI server.
	 */
	private static final NamingBeanImpl JNDI_NAMING_SERVER = new NamingBeanImpl();

	/**
	 * TransactionManager
	 */
	private static TransactionManager transactionManager;

	@Override
	protected Application configure() {
		weld = new Weld();
		weld.initialize();
		/**
		 * Start JNDI Naming Server
		 */
		try {
			JNDI_NAMING_SERVER.start();
		} catch (Throwable t) {
			logger.error(t.getMessage());
		}
		/**
		 * Bind JTA implementation with default names
		 */
		try {
			JNDIManager.bindJTAImplementation();
		} catch (NamingException e) {
			logger.error(e.getMessage());
		}
		/**
		 * Bind datasource
		 */
		try {
			new InitialContext().bind(TransactionalProvider.DS_JNDI_NAME, App.getJdbcDataSource());
		} catch (NamingException e) {
			logger.error(e.getMessage());
		}
		/**
		 * Configure recovery Environment
		 */
		// Set required recovery modules
		RecoveryPropertyManager.getRecoveryEnvironmentBean()
				.setRecoveryModuleClassNames(App.getRecoveryModuleClassNames());
		// Set recovery manager to scan every 2 seconds
		RecoveryPropertyManager.getRecoveryEnvironmentBean().setPeriodicRecoveryPeriod(2);
		// Set recovery backoff between scan phases to 1 second
		RecoveryPropertyManager.getRecoveryEnvironmentBean().setRecoveryBackoffPeriod(1);
		// Set XA resource recoveries required for JDBC recovery
		try {
			JtaPropertyManager.getJTAEnvironmentBean().setXaResourceRecoveries(App.getXaResourceRecoveries());
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		// Set transaction log location
		App.setObjectStoreFolder();
		// Delay recovery manager start until recovery demonstration
		RecoveryManager.delayRecoveryManagerThread();
		// Set recovery helper required to recover dummy XA resource
		RecoveryManager.manager().getModules().stream().filter(m -> m instanceof XARecoveryModule).forEach(
				m -> ((XARecoveryModule) m).addXAResourceRecoveryHelper(new RevolutXAResourceRecoveryHelper()));

		try {
			transactionManager = InitialContext.doLookup("java:/TransactionManager");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResourceConfig(AccountRest.class, TransferRest.class, BeneficiaryRest.class);
	}

	private Response createNewBeneficiary(Beneficiary beneficiary) {
		logger.info("Generated json " + Entity.json(beneficiary));
		return target("Beneficiary/newBeneficiary").request().put(Entity.json(beneficiary));
	}

	private Response createNewAccount(Account account) {
		return target("Account/newAccount").request().put(Entity.json(account));
	}

	private Account getAccountByPhoneCurrency(AccountID accountID) {
		return target("Account/id").request().post(Entity.json(accountID)).readEntity(Account.class);
	}

	private List<Account> getAllAccounts() {
		return target("Account/AllAccounts/").request().get().readEntity(ListAccountsGeneriqType);
	}

	private List<Account> getAllAccounts(String phone) {
		return target("Account/" + phone).request().get().readEntity(ListAccountsGeneriqType);
	}

	private Response getBeneficiaryByPone(String phone) {
		return target("Beneficiary/" + phone).request().get();
	}

	/**
	 * Test JTA and Recovery Mode this method will run randomly one of 3 methods : 1
	 * addNewBeneficiaryJTA 2 simulateCrash 3 testRecoveryAfterCrash
	 */
	@Test
	public void testJTA() {
		logger.info(" testJTA starting");
		switch (new Random().nextInt(4)) {
		case 1:
			addNewBeneficiaryJTA();
			break;
		case 2:
			simulateCrash();
			break;
		case 3:
			try {
				testRecoveryAfterCrash();
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info(" testJTA End");
	}

	/**
	 * Create a new Beneficiary Using JTA API
	 */
	public void addNewBeneficiaryJTA() {
		logger.error(" addNewBeneficiaryJTA starting");
		Beneficiary beneficiary = new Beneficiary();
		String phone = "760978097809";
		String firstName = "James";
		String lastName = "Gosling";
		beneficiary.setPhone(phone);
		beneficiary.setFirstName(firstName);
		beneficiary.setLastName(lastName);
		try {
			transactionManager.begin();
			beneficiaryDaoJTAImpl.save(beneficiary);
			transactionManager.commit();
			Beneficiary fromDB = beneficiaryDaoJTAImpl.find(phone).get();
			logger.info("Retrieved from DB using JTA " + fromDB);
			assertNotNull(fromDB);
		} catch (Exception e) {
			logger.error(e.getMessage());
			fail(e.getMessage());
		}
	}

	/**
	 * Simulate System Crash to test recovery mode
	 */
	public void simulateCrash() {
		logger.error(" simulateCrash starting");
		Beneficiary beneficiary = new Beneficiary();
		String phone = "4444477777";
		String firstName = "Haar";
		String lastName = "Norving";
		beneficiary.setPhone(phone);
		beneficiary.setFirstName(firstName);
		beneficiary.setLastName(lastName);
		try {
			transactionManager.begin();
			beneficiaryDaoJTAImpl.save(beneficiary);
			logger.info("Beneficiaries in DB at start : " + beneficiaryDaoJTAImpl.findAll());

			Beneficiary beneficiary2 = new Beneficiary();
			String phone2 = "88886666";
			String firstName2 = "Bool";
			String lastName2 = "James";
			beneficiary2.setPhone(phone2);
			beneficiary2.setFirstName(firstName2);
			beneficiary2.setLastName(lastName2);
			RevolutXAResource xaResource = new RevolutXAResource(true);
			transactionManager.getTransaction().enlistResource(xaResource);
			beneficiaryDaoJTAImpl.save(beneficiary2);
			logger.info("Beneficiaries in DB at end : " + beneficiaryDaoJTAImpl.findAll());
			transactionManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			fail(e.getMessage());
		}
	}

	public void testRecoveryAfterCrash() throws Exception {
		List<Beneficiary> entriesBefore = beneficiaryDaoJTAImpl.findAll();
		logger.info("Beneficiaries before recovery: " + entriesBefore);
		RecoveryManager.manager().startRecoveryManagerThread();
		waitForRecovery(entriesBefore);
		logger.info("Beneficiaries after recovery: " + beneficiaryDaoJTAImpl.findAll());
	}

	private void waitForRecovery(List<Beneficiary> entriesBefore) throws Exception {
		boolean isComplete = false;

		for (int i = 0; i < 4 && !isComplete; i++) {
			try {
				Thread.sleep(8500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			isComplete = entriesBefore.size() < beneficiaryDaoJTAImpl.findAll().size();
		}
	}

	/**
	 * Create a new Beneficiary Test
	 */
	@Test
	public void addNewBeneficiary() {
		Beneficiary beneficiary = new Beneficiary();
		String phone = "9999999999";
		String firstName = "Lara";
		String lastName = "Craft";
		beneficiary.setPhone(phone);
		beneficiary.setFirstName(firstName);
		beneficiary.setLastName(lastName);
		try {
			Response createBenificiaryResponse = createNewBeneficiary(beneficiary);
			assertEquals(createBenificiaryResponse.getStatus(), Status.CREATED.getStatusCode());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void findBeneficiary() {
		Beneficiary beneficiary = new Beneficiary();
		String phone = "333333999999";
		String firstName = "Albert";
		String lastName = "Einstein";
		beneficiary.setPhone(phone);
		beneficiary.setFirstName(firstName);
		beneficiary.setLastName(lastName);
		try {
			Response createBenificiaryResponse = createNewBeneficiary(beneficiary);
			assertEquals(createBenificiaryResponse.getStatus(), Status.CREATED.getStatusCode());
			Response findBeneficiaryResponse = getBeneficiaryByPone(phone);
			assertEquals(findBeneficiaryResponse.getStatus(), Status.OK.getStatusCode());
			assertEquals(findBeneficiaryResponse.readEntity(Beneficiary.class), beneficiary);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void create2Account() {
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

		try {
			Response createAccountResponse = createNewAccount(account1);
			assertEquals(createAccountResponse.getStatus(), Status.CREATED.getStatusCode());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		try {
			// reteive all accounts
			List<Account> accounts = getAllAccounts();
			// for test purpose we will set phone for account1
			account1.getAccountID().setPhone(phone);
			assertTrue(accounts.contains(account1));

			// retreive account1
			Account account_1 = getAccountByPhoneCurrency(account1.getAccountID());

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
			Response createAccountResponse = createNewAccount(account3);
			assertEquals(createAccountResponse.getStatus(), Status.CREATED.getStatusCode());
			List<Account> accounts2 = getAllAccounts();
			// for test purpose we will set phone for account1 and account3
			account1.getAccountID().setPhone(phone);
			account3.getAccountID().setPhone(phone2);
			assertTrue(accounts2.containsAll(Arrays.asList(account1, account3)));
			Account account_3 = getAccountByPhoneCurrency(account3.getAccountID());
			assertEquals(account3, account_3);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			fail(e.getMessage());
		}

	}

	@Test
	public void testCDI() throws Exception {

		logger.info("CDI Test Start");
		Account account = new Account(new Beneficiary("000000", "Fname", "Lname"), "GBP", "UK", "label acc",
				"IBAN XXXXX", "BIC XXXX");
		Account accountFromDB = null;
		try {
			accountService.save(account);
			accountFromDB = accountService.findAccountByID("000000", "GBP").get();

		} catch (Exception e) {
			e.printStackTrace();
			fail("testCDI fail " + e.getMessage());
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
			Response createAccount4Response = createNewAccount(account4);
			assertEquals(createAccount4Response.getStatus(), Status.CREATED.getStatusCode());
			// for test purpose we will manually set phone for account4
			account4.getAccountID().setPhone(phone4);
			Assert.assertTrue(getAllAccounts(phone4).contains(account4));
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
		Response createAccountResponse = createNewAccount(account5);
		assertEquals(createAccountResponse.getStatus(), Status.CREATED.getStatusCode());
		Response response = target("Account/id").request().post(Entity.json(new AccountID(phone5, currency5)));
		logger.info("response Entity : " + response);
		Assert.assertEquals(response.readEntity(Account.class).getAccountID(), new AccountID(phone5, currency5));

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
		Response createAccount6Response = createNewAccount(account6);
		assertEquals(createAccount6Response.getStatus(), Status.CREATED.getStatusCode());
		double newAmount = 7690.96;
		TopUpParams topUpParams = new TopUpParams(phone6, currency6, newAmount);
		// invoke Account/topUpAccount endPoint twice to topUp the sixth account by
		// 7690.96*2
		Response topUpResponse = target("Account/topUpAccount").request().post(Entity.json(topUpParams));
		logger.info("topUpResponse.getStatus() = " + topUpResponse.getStatus());
		assertEquals(topUpResponse.getStatus(), Status.CREATED.getStatusCode());
		topUpResponse = target("Account/topUpAccount").request().post(Entity.json(topUpParams));

		assertEquals(topUpResponse.getStatus(), Status.CREATED.getStatusCode());
		// invoke Account/ endPoint to retrieve the modified sixth account
		// for test purpose we will set phone for account6
		account6.getAccountID().setPhone(phone6);
		Response getAccountResponse = target("Account/id").request().post(Entity.json(account6.getAccountID()));
		assertEquals(getAccountResponse.getStatus(), Status.OK.getStatusCode());
		logger.info("getAccountsResponse response Message : " + getAccountResponse.getEntity().toString());
		// test the new balance
		Assert.assertEquals(getAccountResponse.readEntity(Account.class).getBalance(), newAmount * 2, DELTA);
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
		// invoke Account/topUpAccount endPoint twice to topUp the sixth account by
		// 7690.96*2
		try {
			target("Account/topUpAccount").request().post(Entity.json(topUpParams));

			TransferDTO transferDTO = new TransferDTO(new AccountID(phone7, currency7),
					new AccountID(phone8, currency8), 450.0, "TR18906RevoT666", "Test Transfer");
			logger.info("Test instance TransferDTO : " + transferDTO.toString());
			Response rsp = target("Transfers/transfer/").request().put(Entity.json(transferDTO));
			logger.info("rsp ::: " + rsp.toString());
			Account account_7 = getAccountByPhoneCurrency(new AccountID(phone7, currency7));
			Account account_8 = getAccountByPhoneCurrency(new AccountID(phone8, currency8));
			logger.info("account_7 = " + account_7);
			logger.info("account_8 = " + account_8);
			assertEquals(account_7.getBalance(), 67859.86 - 450.0, DELTA);
			assertEquals(account_8.getBalance(), 0 + 450.0 * TransferService.DUMMYTRANSFER_RATE, DELTA);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			fail(e.getMessage());
		}

	}

}
