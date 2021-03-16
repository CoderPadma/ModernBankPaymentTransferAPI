package com.modernbank.ibpts.Model;

import java.util.ArrayList;

public class Account {
	
	private String id;
	private double balance;
	private String currency;
	private ArrayList <Transaction> transactions= new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public ArrayList<Transaction> getTransactions() {
		if(transactions.isEmpty())
		{
			return new ArrayList<Transaction>();
		}
		return transactions;
	}
	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}


	public Account() {
	}
	
	public Account(String id, double balance, String currency) {
		super();
		this.id = id;
		this.balance = balance;
		this.currency = currency;
	}
	
	public Account(String id, double balance, String currency, ArrayList<Transaction> transactions) {
		super();
		this.id = id;
		this.balance = balance;
		this.currency = currency;
		this.transactions = transactions;
	}
	
	
	
}
