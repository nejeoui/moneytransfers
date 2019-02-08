package com.revolut.moneytransfers.model.dao;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Produces;

import com.revolut.moneytransfers.dao.AccountDaoImpl;
import com.revolut.moneytransfers.model.Account;

/**
 * An {@code Interface} to handle CRUDE for the {@code Account} {@code Entity}
 * <p>
 * According to the Data Access Object Design Pattern, the Money Transfers API
 * can offer several concrete implementations of this {@code Interface}
 * <p>
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see AccountDaoImpl
 * @since 1.0
 */
@Produces()
public interface AccountDao {
	/**
	 * Adds new Account to the database.
	 *
	 * Only accounts with the unique (phone,currency) are allowed.
	 *
	 * @param account Account.
	 * @return The persisted account .
	 * @throws Exception
	 */
	Account save(Account account) throws Exception;

	/**
	 * Retrieve all accounts in the database.
	 *
	 * @return The list of all existing accounts .
	 * @throws Exception
	 */
	List<Account> selectAll() throws Exception;

	/**
	 * Retrieve all accounts of a specific Beneficiary .
	 *
	 * @param phone String.
	 * @return The list of all accounts of the Beneficiary identified by the phone .
	 * @throws Exception
	 */
	List<Account> selectByBeneficiaryPhone(String phone) throws Exception;

	/**
	 * Retrieve the Beneficiary' Account with a specified currency .
	 *
	 * @param phone    String the beneficiary phone number.
	 * @param currency String.
	 * @return An Optional<Account> with a specified currency and phone (The
	 *         Optional encloses the Account if one exits Otherwise, encloses
	 *         {@code null} )
	 * @throws Exception
	 */
	Optional<Account> selectByBeneficiaryPhoneAndCurrency(String phone, String currency) throws Exception;

	Optional<Account> topUp(Account account);

}
