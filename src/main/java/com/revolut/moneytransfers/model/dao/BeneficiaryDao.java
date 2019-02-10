package com.revolut.moneytransfers.model.dao;

import java.util.Optional;

import com.revolut.moneytransfers.model.Beneficiary;

/**
 * An {@code Interface} to handle CRUDE for the {@code Beneficiary}
 * {@code Entity}
 * <p>
 * According to the Data Access Object Design Pattern, the Money Transfers API
 * can offer several concrete implementations of this {@code Interface}
 * <p>
 *
 * @author <a href="mailto:a.nejeoui@gmail.com">Abderrazzak Nejeoui</a>
 * @see BeneficiaryDaoImpl
 * @since 1.0
 */
public interface BeneficiaryDao {
	/**
	 * Adds new Beneficiary to the database.
	 *
	 * Only beneficiaries with the unique phone are allowed.
	 *
	 * @param beneficiary Beneficiary.
	 * @return The persisted beneficiary .
	 * @throws Exception
	 */
	Beneficiary save(Beneficiary beneficiary) throws Exception;
	
	/**
	 * find the beneficiary  having the provided phone number.
	 *
	 *
	 * @param phone String.
	 * @return The beneficiary wraped in an {@code Optional<Beneficiary>} if exists, Otherwise returns Optional<null> .
	 * @throws Exception
	 */
	Optional<Beneficiary> find(String phone) throws Exception;
}
