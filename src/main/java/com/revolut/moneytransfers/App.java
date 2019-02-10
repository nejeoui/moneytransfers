package com.revolut.moneytransfers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

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
	 * JNDI server.
	 */
	private static final NamingBeanImpl JNDI_NAMING_SERVER = new NamingBeanImpl();

	/**
	 * Bootstrap the CDI Container, JNDI Server and JTA Manager
	 */
	public static void init() throws Exception {
		/**
		 * Bootstrap the CDI Container
		 */
		weld.initialize();

		/**
		 * Start JNDI Naming Server
		 */
		try {
			JNDI_NAMING_SERVER.start();
		} catch (Throwable t) {
			logger.error(t.getMessage());
		}
		// Bind JTA implementation with default names
		try {
			JNDIManager.bindJTAImplementation();
		} catch (NamingException e) {
			logger.error(e.getMessage());
		}

		// Bind datasource
		try {
			new InitialContext().bind(TransactionalProvider.DS_JNDI_NAME, getJdbcDataSource());
		} catch (NamingException e) {
			logger.error(e.getMessage());
		}

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

	private static JdbcDataSource getJdbcDataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		// switch to memory database
		dataSource.setURL("jdbc:h2:./target/revolut.db");
		dataSource.setUser(TransactionalProvider.USER);
		dataSource.setPassword(TransactionalProvider.PASSWORD);

		return dataSource;
	}

	static void setObjectStoreFolder() {
		BeanPopulator.getDefaultInstance(ObjectStoreEnvironmentBean.class)
				.setObjectStoreDir("target/xaTransactionFolder");
		BeanPopulator.getNamedInstance(ObjectStoreEnvironmentBean.class, "communicationStore")
				.setObjectStoreDir("target/xaTransactionFolder");
		BeanPopulator.getNamedInstance(ObjectStoreEnvironmentBean.class, "stateStore")
				.setObjectStoreDir("target/xaTransactionFolder");
	}

	private static List<XAResourceRecovery> getXaResourceRecoveries() throws SQLException {
		XAResourceRecovery jdbcXaRecovery = new JDBCXARecovery();
		jdbcXaRecovery.initialise("jdbc-recovery.xml");

		return Collections.singletonList(jdbcXaRecovery);
	}

	static List<String> getRecoveryModuleClassNames() {
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
