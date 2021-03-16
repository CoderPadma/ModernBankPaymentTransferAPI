package com.modernbank.ibpts.Model;

import java.time.LocalDateTime;

public class Transaction {
	private String accountId;
	private double amount ;
	private String currency;
	private String type;
	private LocalDateTime transactionDate;
	
	
	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}


	public Transaction() {	
	}


	public Transaction(String accountId, double amount, String currency, String type, LocalDateTime transactionDate) {
		super();
		this.accountId = accountId;
		this.amount = amount;
		this.currency = currency;
		this.type = type;
		this.transactionDate = transactionDate;
	}
	
}
