package com.revolut.moneytransfers.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.revolut.moneytransfers.model.Beneficiary;
import com.revolut.moneytransfers.model.dao.BeneficiaryDao;

@ApplicationScoped
public class BeneficiaryService {
	@Inject
	BeneficiaryDao beneficiaryDao;

	public Beneficiary save(Beneficiary  beneficiary ) throws Exception {
		return beneficiaryDao.save(beneficiary );
	}
	public Optional<Beneficiary> find(String phone) throws Exception {
		return beneficiaryDao.find(phone);
	}

}
