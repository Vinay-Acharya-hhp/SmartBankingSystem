package com.smartBanking.bank.Account.Service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartBanking.bank.Account.Dto.AccountRequestDTO;
import com.smartBanking.bank.Account.Dto.AccountResponseDTO;
import com.smartBanking.bank.Account.Entity.Account;
import com.smartBanking.bank.Account.Mapper.AccountConverter;
import com.smartBanking.bank.Account.Repository.AccountRepo;
import com.smartBanking.bank.User.Entity.Users;
import com.smartBanking.bank.User.Repository.UserRepo;
@Service
public class AccountServiceImp implements AccountService {
	
	@Autowired
	private AccountRepo Aropo;
	private UserRepo userRepo;
	

	public AccountServiceImp(AccountRepo aropo, UserRepo userRepo) {
		super();
		Aropo = aropo;
		this.userRepo = userRepo;
	}

	@Override
	public AccountResponseDTO createAccount(AccountRequestDTO requestdto) {
		Account acc=AccountConverter.toEntity(requestdto);
		acc.setAccountNumber(UUID.randomUUID().toString());
		
		Users user=userRepo.findByEmail(requestdto.getEmail());
		if(user==null) {
			throw new RuntimeException("User Not Found");
		}
		acc.setUsers(user);
		Account saveacc=Aropo.save(acc);
		return AccountConverter.toDto(saveacc);
	}

	@Override
	public AccountResponseDTO getAccountByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountResponseDTO deposit(String accountNumber, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountResponseDTO withdrow(String accountNumber, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccount(String accountNumber) {
		// TODO Auto-generated method stub
		
	}

}
