package com.revolut.moneytransfers.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.revolut.moneytransfers.dao.AccountDaoImpl;
import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.model.dao.AccountDao;

@ApplicationScoped
public class AccountService {
	
	@Inject
	AccountDao accountDao;

	public Account save(Account account) throws Exception {
		return accountDao.save(account);
	}

	public List<Account> selectAll() throws Exception {
		return accountDao.selectAll();
	}

	public List<Account> selectByBeneficiaryPhone(String phone) throws Exception {
		System.out.println("layer2");
		return accountDao.selectByBeneficiaryPhone(phone);
	}

	public Optional<Account> selectByBeneficiaryPhoneAndCurrency(String phone, String currency) throws Exception {
		return accountDao.selectByBeneficiaryPhoneAndCurrency(phone, currency);
	}

}
