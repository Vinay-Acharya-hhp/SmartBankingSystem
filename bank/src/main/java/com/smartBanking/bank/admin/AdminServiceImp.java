package com.smartBanking.bank.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartBanking.bank.Account.Entity.Account;
import com.smartBanking.bank.Account.Repository.AccountRepo;
import com.smartBanking.bank.Transaction.Entity.Transaction;
import com.smartBanking.bank.Transaction.Repo.TransactionRepo;
import com.smartBanking.bank.User.Entity.Users;
import com.smartBanking.bank.User.Repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {
	
	private final TransactionRepo transactionrepo ;
	private final UserRepo userrepo;
	private final AccountRepo accountrepo;

	@Override
	public List<Users> getAllUsers() {
		
		return userrepo.findAll();
	}

	@Override
	public List<Transaction> getAllTransaction() {
		return transactionrepo.findAll();
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountrepo.findAll();
	}
	

}
