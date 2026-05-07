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
	
	

	

}
