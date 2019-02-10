package com.revolut.moneytransfers.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.revolut.moneytransfers.model.Beneficiary;
import com.revolut.moneytransfers.model.dao.BeneficiaryDao;

@ApplicationScoped
public class BeneficiaryServiceImpl implements  BeneficiaryService{
	@Inject
	BeneficiaryDao beneficiaryDao;

	@Override
	public Beneficiary save(Beneficiary  beneficiary ) throws Exception {
		return beneficiaryDao.save(beneficiary );
	}
	@Override
	public Optional<Beneficiary> find(String phone) throws Exception {
		return beneficiaryDao.find(phone);
	}

}
