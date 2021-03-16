/*
package com.modernbank.ibpts.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.modernbank.ibpts.Model.Account;
import com.modernbank.ibpts.Model.BalanceSnapshot;
import com.modernbank.ibpts.Model.Transaction;
import com.modernbank.ibpts.Model.TransactionsResponse;
import com.modernbank.ibpts.service.PaymentTrasferService;
import com.modernbank.ibpts.util.DateCompartor;


@Service
public class PaymentTrasferServiceImpl2 implements PaymentTrasferService{
	
	List<Transaction> transactions = new ArrayList<>();
	
	List<Account> accounts = Arrays.asList(
			new Account("111",1000.00,"GBP"),
			new Account("222",2000.00,"GBP"),
			new Account("333",3000.00,"GBP"));
	

	
	@Override
	public BalanceSnapshot getAccountBalance(String accountId) {
		BalanceSnapshot balanceSnapshot = new BalanceSnapshot();
		try {
			Account account =  accounts.stream().filter(a -> a.getId().equals(accountId)).findFirst().get();
			balanceSnapshot = new BalanceSnapshot(account.getId(),account.getBalance(),account.getCurrency());
		}catch(Exception NoSuchElementException) {
			balanceSnapshot.setError("Invalid account details");
		}
		return balanceSnapshot;
	}

	/*
	@Override
	public List<Transaction> getMiniStatement(String accountId) {

		System.out.println("Service - getMiniStatement()");
		
		transactions =  accounts.stream().filter(a -> a.getId().equals(accountId)).findFirst().get().getTransactions();
		Collections.sort(transactions, new DateCompartor());
		return transactions.subList(0, 20);
	}
	*/
	/*
	@Override
	public TransactionsResponse getMiniStatement(String accountId) {
		TransactionsResponse transactionResponse = new TransactionsResponse();
		ArrayList<Transaction> miniStmtTransactions = new ArrayList<>();
		System.out.println("Service - getMiniStatement()");
		//try
		//{
			Account account = accounts.stream().filter(a -> a.getId().equals(accountId)).findFirst().get();
			System.out.println(account.toString());
			transactions =  account.getTransactions();
			if(transactions.isEmpty()){
				transactionResponse.setError("No Recent Transactions");
			}else {
				Collections.sort(transactions, new DateCompartor());
				miniStmtTransactions = (ArrayList<Transaction>) transactions.subList(0, 20);
				System.out.println(miniStmtTransactions.toString());
				transactionResponse.setTransactions(miniStmtTransactions);
			}
		//}catch(Exception NoSuchElementException){
		//	transactionResponse.setError("Invalid account number");
		//}
		return transactionResponse;
	}
	
	@Override
	public String transferAmount(String fromAccountId, String toAccountId, double amount) {
		
		System.out.println("Service - transferAmount()");
		Account fromAccount = new Account();
		Account toAccount = new Account();
		try {
			 fromAccount = accounts.stream().filter(a -> a.getId().equals(fromAccountId)).findFirst().get();
			 toAccount =  accounts.stream().filter(a -> a.getId().equals(toAccountId)).findFirst().get();
		}catch(NoSuchElementException e) {
			return "Invalid account number";
		}
		//debitAmount(fromAccount.getBalance(),amount);
		//Debit amount
		if(fromAccount.getBalance()>amount) {
			fromAccount.setBalance(fromAccount.getBalance()-amount);
		}else {
			System.out.println("Insufficient funds available");
			return "Insufficient funds available";
		}
		
		//Make record of Debit transaction
		Transaction debitTransaction = new Transaction(fromAccount.getId(),amount,"GBP","DEBIT",LocalDateTime.now());
		ArrayList<Transaction> fromAccountList = fromAccount.getTransactions();
		fromAccountList.add(debitTransaction);
		fromAccount.setTransactions(fromAccountList);
		System.out.println("Debited from fromAccount ="+fromAccount);
		
		//Credit amount
		toAccount.setBalance(toAccount.getBalance()+amount);
		
		//Make record of Debit transaction
		Transaction creditTransaction = new Transaction(toAccount.getId(),amount,"GBP","CREDIT",LocalDateTime.now());
		ArrayList<Transaction> toAccountList = toAccount.getTransactions();
		toAccountList.add(creditTransaction);
		toAccount.setTransactions(toAccountList);
		System.out.println("Credited to toAccount = "+toAccount);
		
		return "Transfer Successful";

	}


}
*/
