package com.revolut.moneytransfers.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.revolut.moneytransfers.AccountDaoImpl;
import com.revolut.moneytransfers.model.Account;

@ApplicationScoped
public class AccountService {
	@Inject
	AccountDaoImpl accountDao;
	
	public Account save(Account account) {
		return accountDao.save(account);
	}
	
	public List<Account> selectAll(){
		return accountDao.selectAll();
	}
	public List<Account> selectByBeneficiaryPhone(String phone){
		System.out.println("layer2");
		return accountDao.selectByBeneficiaryPhone(phone);
	}
	public Optional<Account> selectByBeneficiaryPhoneAndCurrency(String phone,String currency){
		 return accountDao.selectByBeneficiaryPhoneAndCurrency(phone, currency);
	 }


}
