package com.revolut.moneytransfer.dao.jta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.inject.Inject;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arjuna.ats.jdbc.TransactionalDriver;

/**
 * 
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a> This
 *         implementation was designed based on JBoss Quickstart Guide, credits
 *         to <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 *
 */
public class TransactionalProvider implements ConnectionProvider {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Self4j Logger
	 */
	
	private transient Logger logger=LoggerFactory.getLogger(TransactionalProvider.class);

	public static final String DS_JNDI_NAME = "java:/RevolutJTADS";

	public static final String USER = "sa";

	public static final String PASSWORD = "sa";

	private final TransactionalDriver transactionalDriver;

	public TransactionalProvider() {
		transactionalDriver = new TransactionalDriver();
	}

	@Override
	public Connection getConnection() throws SQLException {
		logger.info("connecting to :" + DS_JNDI_NAME);
		Properties properties = new Properties();
		properties.setProperty(TransactionalDriver.userName, USER);
		properties.setProperty(TransactionalDriver.password, PASSWORD);
		return transactionalDriver.connect("jdbc:arjuna:" + DS_JNDI_NAME, properties);
	}

	@Override
	public void closeConnection(Connection connection) throws SQLException {
		if (!connection.isClosed()) {
			connection.close();
			logger.info("connection to :" + DS_JNDI_NAME+" closed");
		}
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@Override
	public boolean isUnwrappableAs(Class aClass) {
		return getClass().isAssignableFrom(aClass);
	}

	@Override
	public <T> T unwrap(Class<T> aClass) {
		if (isUnwrappableAs(aClass)) {
			return (T) this;
		}

		throw new UnknownUnwrapTypeException(aClass);
	}

}
