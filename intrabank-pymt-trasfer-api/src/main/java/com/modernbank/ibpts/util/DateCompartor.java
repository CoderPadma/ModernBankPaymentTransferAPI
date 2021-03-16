package com.modernbank.ibpts.util;

import java.util.Comparator;

import com.modernbank.ibpts.Model.Transaction;


public class DateCompartor implements Comparator<Transaction>{

	@Override
	public int compare(Transaction o1, Transaction o2) {
		// TODO Auto-generated method stub
		return o2.getTransactionDate().compareTo(o1.getTransactionDate());
	}
	
}