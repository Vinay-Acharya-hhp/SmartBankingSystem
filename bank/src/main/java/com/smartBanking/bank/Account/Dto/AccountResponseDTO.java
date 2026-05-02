package com.smartBanking.bank.Account.Dto;

import java.math.BigDecimal;

import com.smartBanking.bank.Account.Entity.AccountType;

public class AccountResponseDTO {
	
	
	
	
	public AccountResponseDTO() {
		
	}

	public AccountResponseDTO(String accountNumber, AccountType accountType, BigDecimal balance, String name,
			String email) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.name = name;
		this.email = email;
	}

	private String accountNumber;
	
	private AccountType accountType;
	
	private BigDecimal balance;
	
	
	private String name;
	
	private String email;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
