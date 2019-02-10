package com.revolut.moneytransfers.service;

import java.util.Optional;

import com.revolut.moneytransfers.model.Beneficiary;

public interface BeneficiaryService {
	Optional<Beneficiary> find(String phone) throws Exception;

	Beneficiary save(Beneficiary beneficiary) throws Exception;

}
