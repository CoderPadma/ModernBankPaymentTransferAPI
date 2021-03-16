package com.modernbank.ibpts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.modernbank.ibpts.Model.BalanceSnapshot;
import com.modernbank.ibpts.Model.Transaction;
import com.modernbank.ibpts.Model.TransactionsResponse;

@Service
public interface PaymentTrasferService {


	public BalanceSnapshot getAccountBalance(String accountId);

	//public List<Transaction> getMiniStatement(String accountId);
	public TransactionsResponse getMiniStatement(String accountId);

	public String transferAmount(String fromAccountId, String toAccountId, double amount);
	
	
}
