package com.smartBanking.bank.Account.Mapper;

import com.smartBanking.bank.Account.Dto.AccountRequestDTO;
import com.smartBanking.bank.Account.Dto.AccountResponseDTO;
import com.smartBanking.bank.Account.Entity.Account;

public class AccountConverter {

	public static Account toEntity(AccountRequestDTO accountRequestdto) {
		if(accountRequestdto==null) {
			return null;
		}
		Account acc=new Account();
		acc.setAccountType(accountRequestdto.getAccountType());
		acc.setBalance(accountRequestdto.getInitialBalance());
		
		return acc;
	}
	
	
	public static AccountResponseDTO toDto(Account account) {
		if(account==null) {
			return null;
		}
		AccountResponseDTO accountResponsedto=new AccountResponseDTO();
		 accountResponsedto.setAccountNumber(account.getAccountNumber());
		 accountResponsedto.setAccountType(account.getAccountType());
		 accountResponsedto.setBalance(account.getBalance());
		 
		 if(account.getUsers()!=null) {
			 accountResponsedto.setName(account.getUsers().getName());
			 accountResponsedto.setEmail(account.getUsers().getEmail());
		 }
		 return accountResponsedto;
	}
}
