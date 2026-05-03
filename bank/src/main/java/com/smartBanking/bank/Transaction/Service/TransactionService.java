package com.smartBanking.bank.Transaction.Service;

import java.math.BigDecimal;
import java.util.List;

import com.smartBanking.bank.Transaction.Dto.TransactionResponseDTO;

public interface TransactionService {
	
	TransactionResponseDTO Deposit(String accountNumber,BigDecimal amount);
	
	String Withdraw(String accountNumber,BigDecimal amount);
	
	String Transfer(String fromAccount,String toaccount,BigDecimal amount);
	
	List<TransactionResponseDTO> getTransaction(String accountNumber); 

}
