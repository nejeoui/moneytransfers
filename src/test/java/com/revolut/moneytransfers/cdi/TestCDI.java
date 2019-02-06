package com.revolut.moneytransfers.cdi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import org.junit.Test;

import com.revolut.moneytransfers.model.Account;
import com.revolut.moneytransfers.model.Beneficiary;
import com.revolut.moneytransfers.service.AccountService;

public class TestCDI {
	
	@Test
public	void testCDI() {
		System.out.println("CDI Test Start");
		Account account=new Account(new Beneficiary("000000", "Fname", "Lname"),"GBP","UK", "label acc", "IBAN XXXXX", "BIC XXXX");
		Account accountFromDB=null;
		SeContainerInitializer initializer=SeContainerInitializer.newInstance();
		try(SeContainer seContainer=initializer.initialize()){
			Instance<AccountService> LazyAccountService=seContainer.select(AccountService.class);
			AccountService accountService=LazyAccountService.get();
			accountService.save(account);
			accountFromDB=accountService.selectByBeneficiaryPhoneAndCurrency(account.getAccountID().getPhone(), account.getAccountID().getCurrency()).get();
		}
		assertNotNull(accountFromDB);
		System.out.println("CDI Test End");
		
	}

}
