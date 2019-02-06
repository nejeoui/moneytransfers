package com.revolut.moneytransfers.model.dao;

import java.util.List;
import java.util.Optional;

import com.revolut.moneytransfers.model.Account;

public interface AccountDao {
	Account save(Account account);
	List<Account> selectAll();
	List<Account> selectByBeneficiaryPhone(String phone);
	 Optional<Account> selectByBeneficiaryPhoneAndCurrency(String phone,String currency);

}
