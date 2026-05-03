package com.smartBanking.bank.Account.Service;

import java.util.stream.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartBanking.bank.Account.Dto.AccountRequestDTO;
import com.smartBanking.bank.Account.Dto.AccountResponseDTO;
import com.smartBanking.bank.Account.Entity.Account;
import com.smartBanking.bank.Account.Mapper.AccountConverter;
import com.smartBanking.bank.Account.Repository.AccountRepo;
import com.smartBanking.bank.User.Entity.Users;
import com.smartBanking.bank.User.Repository.UserRepo;
import com.smartBanking.bank.User.exception.AccountNotFoundException;
import com.smartBanking.bank.User.exception.InsufficiantBalanceException;
import com.smartBanking.bank.User.exception.UserNotFoundException;
@Service
public class AccountServiceImp implements AccountService {
	
	@Autowired
	private AccountRepo accountRepo;
	private UserRepo userRepo;
	

	public AccountServiceImp(AccountRepo accountRepo, UserRepo userRepo) {
		super();
		this.accountRepo =accountRepo;
		this.userRepo = userRepo;
	}

	@Override
	public AccountResponseDTO createAccount(AccountRequestDTO requestdto) {
		Account acc=AccountConverter.toEntity(requestdto);
		//acc.setAccountNumber(UUID.randomUUID().toString());
		String AccountNumber;
		do {
			AccountNumber=String.valueOf(
					ThreadLocalRandom.current().nextInt(100000000,1000000000));
					
		}while(accountRepo.existsByAccountNumber(AccountNumber));
		
		acc.setAccountNumber(AccountNumber);
		
		Users user=userRepo.findByEmail(requestdto.getEmail());
		if(user==null) {
			throw new UserNotFoundException("User Not Found");
		}
		acc.setUsers(user);
		Account saveacc=accountRepo.save(acc);
		return AccountConverter.toDto(saveacc);
	}

	@Override
	public AccountResponseDTO getAccountByAccountNumber(String accountNumber) {
		Account acc=accountRepo.findByAccountNumber(accountNumber);
		if(acc==null) {
			throw new AccountNotFoundException("Account Not Found");
		}
		
		return AccountConverter.toDto(acc);
	}

	@Override
	public AccountResponseDTO deposit(String accountNumber, BigDecimal amount) {
		if(amount.compareTo(BigDecimal.ZERO)<=0) {
			throw new InsufficiantBalanceException("Amount must be greater than zero");	
		}
		Account acc=accountRepo.findByAccountNumber(accountNumber);
		if(acc==null) {
			throw new AccountNotFoundException("Account Not Found");
		}
	   acc.setBalance(acc.getBalance().add(amount));
	   Account save=accountRepo.save(acc);
		return AccountConverter.toDto(save);
	}

	@Override
	public AccountResponseDTO withdrow(String accountNumber, BigDecimal amount) {
		if(amount.compareTo(BigDecimal.ZERO)<=0) {
			throw new InsufficiantBalanceException("Amount must be greater than zero");	
		}
		Account acc=accountRepo.findByAccountNumber(accountNumber);
		if(acc==null) {
			throw new AccountNotFoundException("Account Not Found");
		}
	   acc.setBalance(acc.getBalance().subtract(amount));
	   Account save=accountRepo.save(acc);
		return AccountConverter.toDto(save);
		
	}

	@Override
	public List<AccountResponseDTO> getAccountByEmail(String email) {
		List<Account> acc=accountRepo.findByUsersEmail(email);
		if(acc.isEmpty()) {
			throw new AccountNotFoundException("Account Not Found");
		}
		
		return acc.stream()
				.map(AccountConverter::toDto)
				.toList();
		
	}

	@Override
	public void deleteAccount(String accountNumber) {
		// TODO Auto-generated method stub
		
	}



}
