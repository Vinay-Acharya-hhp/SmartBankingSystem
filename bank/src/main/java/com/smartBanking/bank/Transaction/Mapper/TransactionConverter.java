package com.smartBanking.bank.Transaction.Mapper;

import com.smartBanking.bank.Transaction.Dto.TransactionResponseDTO;
import com.smartBanking.bank.Transaction.Entity.Transaction;

public class TransactionConverter {
	
	public static TransactionResponseDTO toDto(Transaction transaction) {
		TransactionResponseDTO dto =new TransactionResponseDTO();
		dto.setId(transaction.getId());
		dto.setAmount(transaction.getAmount());
		dto.setType(transaction.getType().name());
		dto.setTimestamp(transaction.getTimestamp());
		
		if(transaction.getFromAccount()!=null) {
			dto.setFromAccount(transaction.getFromAccount().getAccountNumber());
		}
		if(transaction.getToAccount()!=null) {
			dto.setToAccount(transaction.getToAccount().getAccountNumber());
		}
		
		
		return dto;
		
	}

}
