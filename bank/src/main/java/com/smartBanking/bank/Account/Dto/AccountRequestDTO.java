package com.smartBanking.bank.Account.Dto;

import java.math.BigDecimal;

import com.smartBanking.bank.Account.Entity.AccountType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {
	
    @NotNull
	private AccountType accountType;
	@Email
	@NotNull
	private String email;
	
	private BigDecimal initialBalance;
	
	

	

}
