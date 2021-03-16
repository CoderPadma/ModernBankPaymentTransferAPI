package com.modernbank.ibpts.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.modernbank.ibpts.Model.Account;
import com.modernbank.ibpts.Model.BalanceSnapshot;
import com.modernbank.ibpts.Model.TransactionsResponse;
import com.modernbank.ibpts.service.PaymentTrasferService;

class PaymentTrasferServiceImplTest {
	
	
	PaymentTrasferService paymentTrasferService= new PaymentTrasferServiceImpl();
	
	List<Account> accounts = Arrays.asList(
			new Account("111",1000.00,"GBP"),
			new Account("222",2000.00,"GBP"),
			new Account("333",3000.00,"GBP"),
			new Account("444",0.00,"GBP"));
	
	
	@Test
	void testGetAccountBalance() {
		BalanceSnapshot expectedBalanceSnapshot = new BalanceSnapshot("111",1000.00,"GBP");
		BalanceSnapshot actualbalanceSnapshot = paymentTrasferService.getAccountBalance("111");
		assertEquals(expectedBalanceSnapshot.getBalance(),actualbalanceSnapshot.getBalance());
		
		BalanceSnapshot expectedBalanceSnapshot2 = paymentTrasferService.getAccountBalance("999");
		assertNotNull(expectedBalanceSnapshot2.getError(),"Checks for the invalid account");
		
	}

	@Test
	void testGetMiniStatement() {
		//Invalid account number
		String expected1= "Invalid account number";
		TransactionsResponse actualTR = paymentTrasferService.getMiniStatement("999");
		String actual1= actualTR.getError();
		assertEquals(expected1,actual1,"Checks for the invalid account");
		
		//No Recent Transactions
		String expected2= "No Recent Transactions";
		TransactionsResponse actualTR2 = paymentTrasferService.getMiniStatement("333");
		String actual2= actualTR2.getError();
		assertEquals(expected2,actual2,"Checks for No Recent Transactions error");

		//Valid Trasactions
		String actual3 = paymentTrasferService.transferAmount("666", "777", 10.0);
		String actual4 = paymentTrasferService.transferAmount("777", "666", 20.0);
		int actualMiniStmtTranxArraySize666 = paymentTrasferService.getMiniStatement("666").getTransactions().size();
		int actualMiniStmtTranxArraySize777 = paymentTrasferService.getMiniStatement("777").getTransactions().size();
		
		assertEquals(2,actualMiniStmtTranxArraySize666,"Checks if trasactions entries are made");
		assertEquals(2,actualMiniStmtTranxArraySize777,"Checks if trasactions entries are made");

	}

	@Test
	void testTransferAmount() {
		
		String expectedVal = "Invalid account number";
		String actualVal = paymentTrasferService.transferAmount("111", "999", 10.0);
		assertEquals(expectedVal, actualVal, "Checks for Invalid account number");
		
		String expectedVal2 = "Insufficient funds available";
		String actualVal2 = paymentTrasferService.transferAmount("444", "222", 10.0);
		assertEquals(expectedVal2, actualVal2, "Checks for Insufficient funds available");
		
		String expectedVal3 = "Transfer Successful";
		String actualVal3 = paymentTrasferService.transferAmount("111", "222", 10.0);
		assertEquals(expectedVal3, actualVal3, "Checks for Transfer Successful");
		
	}

}
