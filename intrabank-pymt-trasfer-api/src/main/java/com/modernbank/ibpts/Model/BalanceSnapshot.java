package com.modernbank.ibpts.Model;

public class BalanceSnapshot {
	
	private String id;
	private double balance;
	private String currency;
	private String error;
	
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
	
	public BalanceSnapshot(String id, double balance, String currency) {
		super();
		this.id = id;
		this.balance = balance;
		this.currency = currency;
	}
	public BalanceSnapshot() {
	}
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
