package com.smartBanking.bank.Account.Service;

import java.math.BigDecimal;
import java.util.List;

import com.smartBanking.bank.Account.Dto.AccountRequestDTO;
import com.smartBanking.bank.Account.Dto.AccountResponseDTO;

public interface AccountService {
	
	AccountResponseDTO createAccount(AccountRequestDTO requestdto);
	
	AccountResponseDTO getAccountByAccountNumber(String accountNumber);
	
	List<AccountResponseDTO> getAccountByEmail(String email);
	
	AccountResponseDTO deposit(String accountNumber,BigDecimal amount);
	
	AccountResponseDTO withdrow(String accountNumber,BigDecimal amount);
	
	void deleteAccount(String accountNumber);
	

}
