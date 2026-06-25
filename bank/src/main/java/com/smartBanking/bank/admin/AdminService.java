package com.smartBanking.bank.admin;

import java.util.List;

import com.smartBanking.bank.Account.Entity.Account;
import com.smartBanking.bank.Transaction.Entity.Transaction;
import com.smartBanking.bank.User.Entity.Users;

public interface AdminService {
	
	
	List<Users> getAllUsers();
	
	List<Transaction> getAllTransaction();
	
	List<Account> getAllAccounts();

}
