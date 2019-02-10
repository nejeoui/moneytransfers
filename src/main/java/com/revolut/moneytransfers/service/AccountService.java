package com.revolut.moneytransfers.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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
		return accountDao.selectByBeneficiaryPhone(phone);
	}

	public Optional<Account> findAccountByID(String phone, String currency) throws Exception {
		return accountDao.findAccountByID(phone, currency);
	}

	public Optional<Account> topUp(Account account) {
		return accountDao.topUp(account);
	}

}
