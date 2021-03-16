package com.modernbank.ibpts.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.modernbank.ibpts.Model.Account;
import com.modernbank.ibpts.Model.BalanceSnapshot;
import com.modernbank.ibpts.Model.Transaction;
import com.modernbank.ibpts.Model.TransactionsResponse;
import com.modernbank.ibpts.service.PaymentTrasferService;
import com.modernbank.ibpts.util.DateCompartor;
import com.modernbank.ibpts.util.PaymentTransferUtil;


@Service
public class PaymentTrasferServiceImpl implements PaymentTrasferService{
	
	List<Transaction> transactions = new ArrayList<>();
	
	List<Account> accounts = Arrays.asList(
			new Account("111",1000.00,"GBP"),
			new Account("222",2000.00,"GBP"),
			new Account("333",3000.00,"GBP"),
			new Account("444",0.00,"GBP"),
			new Account("666",3000.00,"GBP"),
			new Account("777",3000.00,"GBP")
			);
	
	Logger logger = LoggerFactory.getLogger(PaymentTrasferServiceImpl.class);

	@Override
	public BalanceSnapshot getAccountBalance(String accountId) {
		BalanceSnapshot balanceSnapshot = new BalanceSnapshot();
		try {
			Account account =  accounts.stream().filter(a -> a.getId().equals(accountId)).findFirst().get();
			balanceSnapshot = new BalanceSnapshot(account.getId(),account.getBalance(),account.getCurrency());
		}catch(Exception NoSuchElementException) {
			balanceSnapshot.setError(PaymentTransferUtil.INVALID_ACCOUNT_DETAILS);
		}
		return balanceSnapshot;
	}
	
	@Override
	public TransactionsResponse getMiniStatement(String accountId) {
		TransactionsResponse transactionResponse = new TransactionsResponse();
		List<Transaction> miniStmtTransactions = new ArrayList<>();
		try
		{
			Account account = getAccountfromId(accountId);//accounts.stream().filter(a -> a.getId().equals(accountId)).findFirst().get();
			transactions =  account.getTransactions();
			
			if(transactions.isEmpty()){
				transactionResponse.setError(PaymentTransferUtil.NO_RECENT_TRANSACTIONS);
			}else {
				Collections.sort(transactions, new DateCompartor());
				//20 most recent transactions 
				miniStmtTransactions =  transactions.subList(0, transactions.size()>20?20:transactions.size());
				transactionResponse.setTransactions(miniStmtTransactions);
			}
		}catch(Exception NoSuchElementException){
			transactionResponse.setError(PaymentTransferUtil.INVALID_ACCOUNT_NUMBER);
		}
		return transactionResponse;
	}
	
	@Override
	public String transferAmount(String fromAccountId, String toAccountId, double amount) {
		
		Account fromAccount = new Account();
		Account toAccount = new Account();
		try {
			fromAccount = getAccountfromId(fromAccountId);
			toAccount = getAccountfromId(toAccountId);
		}catch(NoSuchElementException e) {
			return PaymentTransferUtil.INVALID_ACCOUNT_NUMBER;
		}
		
		//Debit amount
		if(fromAccount.getBalance()>amount) {
			fromAccount.setBalance(fromAccount.getBalance()-amount);
		}else {
			return PaymentTransferUtil.INSUFFICIENT_FUNDS_AVAILABLE;
		}
		
		//Make record of Debit transaction
		Transaction debitTransaction = new Transaction(toAccount.getId(), amount, PaymentTransferUtil.GBP, PaymentTransferUtil.DEBIT, LocalDateTime.now());
		ArrayList<Transaction> fromAccountList = fromAccount.getTransactions();
		fromAccountList.add(debitTransaction);
		fromAccount.setTransactions(fromAccountList);
		
		//Credit amount
		toAccount.setBalance(toAccount.getBalance()+amount);
		
		//Make record of Debit transaction
		Transaction creditTransaction = new Transaction(fromAccount.getId(),amount,PaymentTransferUtil.GBP, PaymentTransferUtil.CREDIT,LocalDateTime.now());
		ArrayList<Transaction> toAccountList = toAccount.getTransactions();
		toAccountList.add(creditTransaction);
		toAccount.setTransactions(toAccountList);
		
		return PaymentTransferUtil.TRANSFER_SUCCESSFUL;

	}
	
	public Account getAccountfromId(String accountId) {
		return accounts.stream().filter(a -> a.getId().equals(accountId)).findFirst().get();
	}
	
	
}
