package com.modernbank.ibpts.controller;

public class PaymentTrasferCntlrConstants {
	
	public static final String ACCOUNT_BALANCE_API_VAL = "Balance Snapshot by Account Id.";
	public static final String ACCOUNT_BALANCE_API_NOTES = "Fetch current balance of the account using account Id.";
	
	public static final String MINI_STMT_API_VAL = "Generate Mini Statement for Account Id";
	public static final String MINI_STMT_API_NOTES = "For given account id, generate Mini Statement for with 20 most recent transactions.";
	
	public static final String TRANSFER_API_VAL = "Transfer amount between the accounts.";
	public static final String TRANSFER_API_NOTES = "Transfer the amount and record the debit/credit transactions in the respective accounts";
	
}
