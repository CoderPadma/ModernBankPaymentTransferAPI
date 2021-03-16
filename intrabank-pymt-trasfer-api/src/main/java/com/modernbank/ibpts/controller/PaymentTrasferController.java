package com.modernbank.ibpts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.modernbank.ibpts.Model.BalanceSnapshot;
import com.modernbank.ibpts.Model.TransactionsResponse;
import com.modernbank.ibpts.service.PaymentTrasferService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/accounts")
public class PaymentTrasferController {

	@Autowired
	PaymentTrasferService paymentTransferService;


	@RequestMapping("/{accountId}/balance")
	@ApiOperation(value = PaymentTrasferCntlrConstants.ACCOUNT_BALANCE_API_VAL,
				notes=PaymentTrasferCntlrConstants.ACCOUNT_BALANCE_API_NOTES,
				response= BalanceSnapshot.class)
	public BalanceSnapshot getAccountBalance(@PathVariable("accountId") String accountId) {

		return paymentTransferService.getAccountBalance(accountId);

	}
	
	@RequestMapping("/{accountId}/statements/mini")
	@ApiOperation(value = PaymentTrasferCntlrConstants.MINI_STMT_API_VAL,
	notes=PaymentTrasferCntlrConstants.MINI_STMT_API_NOTES,
	response= BalanceSnapshot.class)
	public TransactionsResponse getMiniStatement(@PathVariable("accountId") String accountId) {

		return paymentTransferService.getMiniStatement(accountId);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/transfer/{fromAccountId}/{toAccountId}/{amount}")
	@ApiOperation(value = PaymentTrasferCntlrConstants.TRANSFER_API_VAL,
	notes=PaymentTrasferCntlrConstants.TRANSFER_API_NOTES,
	response= BalanceSnapshot.class)
	public String transferAmount(@PathVariable("fromAccountId") String fromAccountId, @PathVariable("toAccountId") String toAccountId,
			@PathVariable("amount") String amount) {
		
		Double amt= Double.valueOf(amount);  
		return paymentTransferService.transferAmount(fromAccountId, toAccountId, amt);

	}

}
