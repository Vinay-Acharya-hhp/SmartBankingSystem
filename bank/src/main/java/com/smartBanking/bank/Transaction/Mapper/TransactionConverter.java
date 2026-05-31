package com.smartBanking.bank.Transaction.Mapper;

import com.smartBanking.bank.Transaction.Dto.TransactionResponseDTO;
import com.smartBanking.bank.Transaction.Entity.Transaction;

public class TransactionConverter {
	
	public static TransactionResponseDTO toDto(Transaction transaction) {
		TransactionResponseDTO dto =new TransactionResponseDTO();
		dto.setId(transaction.getTransactionId());
		dto.setAmount(transaction.getAmount());
		dto.setType(transaction.getType().name());
		dto.setTimestamp(transaction.getTimestamp());
        dto.setBalance(transaction.getAccount().getBalance());
		
		
		return dto;
		
	}
	
	public static TransactionResponseDTO tosenderDto(Transaction transaction) {
		TransactionResponseDTO senderdto =new TransactionResponseDTO();
		senderdto.setId(transaction.getId());
		senderdto.setAmount(transaction.getAmount());
		senderdto.setType(transaction.getType().name());
		senderdto.setTimestamp(transaction.getTimestamp());
		
		if(transaction.getAccount()!=null) {
			senderdto.setAccount(transaction.getAccount().getAccountNumber());
		}
		
		if(transaction.getAccount()!=null) {
			senderdto.setAccount(transaction.getAccount().getAccountNumber());
		}
		
		senderdto.setBalance(transaction.getAccount().getBalance());
		return senderdto;
		
	}

}
