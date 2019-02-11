package com.revolut.moneytransfers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.ext.RuntimeDelegate;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.h2.jdbcx.JdbcDataSource;
import org.jboss.weld.environment.se.Weld;
import org.jnp.server.NamingBeanImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arjuna.ats.arjuna.common.ObjectStoreEnvironmentBean;
import com.arjuna.ats.arjuna.recovery.RecoveryManager;
import com.arjuna.ats.internal.arjuna.recovery.AtomicActionRecoveryModule;
import com.arjuna.ats.internal.jdbc.recovery.JDBCXARecovery;
import com.arjuna.ats.internal.jta.recovery.arjunacore.XARecoveryModule;
import com.arjuna.ats.jta.recovery.XAResourceRecovery;
import com.arjuna.ats.jta.utils.JNDIManager;
import com.arjuna.common.internal.util.propertyservice.BeanPopulator;
import com.revolut.moneytransfer.dao.jta.JtaPropertyManager;
import com.revolut.moneytransfer.dao.jta.RecoveryPropertyManager;
import com.revolut.moneytransfer.dao.jta.RevolutXAResourceRecoveryHelper;
import com.revolut.moneytransfer.dao.jta.TransactionalProvider;
import com.revolut.moneytransfers.service.rest.AccountRest;
import com.revolut.moneytransfers.service.rest.BeneficiaryRest;
import com.revolut.moneytransfers.service.rest.HelloRest;
import com.revolut.moneytransfers.service.rest.TransferRest;

public class App {
	/**
	 * Self4j Logger
	 */
	private static transient Logger logger = LoggerFactory.getLogger(App.class);
	/**
	 * JBoss CDI 2.0 Container
	 */
	private static final Weld weld = new Weld();

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
	 * Bootstrap the CDI Container, JAX-RS Server, JNDI Server and JTA Manager
	 */
	public static void init() throws Exception {
		/**
		 * Bootstrap the CDI Container
		 */
		weld.initialize();

		// start Jersey Server
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.register(AccountRest.class);
		resourceConfig.register(HelloRest.class);
		resourceConfig.register(TransferRest.class);
		resourceConfig.register(BeneficiaryRest.class);
		HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(resourceConfig, HttpHandler.class);
		server.getServerConfiguration().addHttpHandler(handler);
		new Thread(() -> {
			try {
				logger.info("Starting...!");
				server.start();
				logger.info("http Server started!");
				System.in.read();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}).start();
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
			new InitialContext().bind(TransactionalProvider.DS_JNDI_NAME, getJdbcDataSource());
		} catch (NamingException e) {
			logger.error(e.getMessage());
		}
		/**
		 * Configure recovery Environment
		 */
		// Set required recovery modules
		RecoveryPropertyManager.getRecoveryEnvironmentBean().setRecoveryModuleClassNames(getRecoveryModuleClassNames());
		// Set recovery manager to scan every 2 seconds
		RecoveryPropertyManager.getRecoveryEnvironmentBean().setPeriodicRecoveryPeriod(2);
		// Set recovery backoff between scan phases to 1 second
		RecoveryPropertyManager.getRecoveryEnvironmentBean().setRecoveryBackoffPeriod(1);
		// Set XA resource recoveries required for JDBC recovery
		JtaPropertyManager.getJTAEnvironmentBean().setXaResourceRecoveries(getXaResourceRecoveries());
		// Set transaction log location
		setObjectStoreFolder();
		// Delay recovery manager start until recovery demonstration
		RecoveryManager.delayRecoveryManagerThread();
		// Set recovery helper required to recover dummy XA resource
		RecoveryManager.manager().getModules().stream().filter(m -> m instanceof XARecoveryModule).forEach(
				m -> ((XARecoveryModule) m).addXAResourceRecoveryHelper(new RevolutXAResourceRecoveryHelper()));

	}

	public static void main(String[] args) {

	}

	public static JdbcDataSource getJdbcDataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		// switch to file database
		dataSource.setURL("jdbc:h2:./target/revolut.db");
		
		// switch to memory database
//		dataSource.setURL("jdbc:h2:mem:revolut");
		dataSource.setUser(TransactionalProvider.USER);
		dataSource.setPassword(TransactionalProvider.PASSWORD);

		return dataSource;
	}

	public static void setObjectStoreFolder() {
		BeanPopulator.getDefaultInstance(ObjectStoreEnvironmentBean.class)
				.setObjectStoreDir("target/xaTransactionFolder");
		BeanPopulator.getNamedInstance(ObjectStoreEnvironmentBean.class, "communicationStore")
				.setObjectStoreDir("target/xaTransactionFolder");
		BeanPopulator.getNamedInstance(ObjectStoreEnvironmentBean.class, "stateStore")
				.setObjectStoreDir("target/xaTransactionFolder");
	}

	public static List<XAResourceRecovery> getXaResourceRecoveries() throws SQLException {
		XAResourceRecovery jdbcXaRecovery = new JDBCXARecovery();
		jdbcXaRecovery.initialise("database-recovery.xml");

		return Collections.singletonList(jdbcXaRecovery);
	}

	public static List<String> getRecoveryModuleClassNames() {
		List<String> recoveryModuleClassNames = new ArrayList<>();
		recoveryModuleClassNames.add(AtomicActionRecoveryModule.class.getName());
		recoveryModuleClassNames.add(XARecoveryModule.class.getName());

		return recoveryModuleClassNames;
	}

	static void shutdown() {
		try {
			// Stop recovery manager
			RecoveryManager.manager().terminate();
		} catch (Throwable t) {
			logger.error(t.getMessage());
		}

		try {
			// Shutdown the weld container properly
			weld.shutdown();
		} catch (Throwable t) {
			logger.error(t.getMessage());
		}

		try {
			// Stop JNDI saming server
			JNDI_NAMING_SERVER.stop();
		} catch (Throwable t) {
			logger.error(t.getMessage());
		}
	}

}
