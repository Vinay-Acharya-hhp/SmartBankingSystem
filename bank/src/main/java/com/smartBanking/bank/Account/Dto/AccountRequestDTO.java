package com.smartBanking.bank.Account.Dto;

import java.math.BigDecimal;

import com.smartBanking.bank.Account.Entity.AccountType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {
	
	
	
	
	
	

	private AccountType accountType;
	
	private String email;
	
	private BigDecimal initialBalance;
	
	

	public AccountRequestDTO() {
		
	}

	public AccountRequestDTO(AccountType accountType, String email, BigDecimal initialBalance) {
		super();
		this.accountType = accountType;
		this.email = email;
		this.initialBalance = initialBalance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}
	
	

}
