package com.smartBanking.bank.admin;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartBanking.bank.Account.Entity.Account;
import com.smartBanking.bank.Transaction.Entity.Transaction;
import com.smartBanking.bank.User.Entity.Users;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
  
	private final AdminService adminservice;
	
	
	 @GetMapping("/users")
      public List<Users> getAllUsers() {
		return adminservice.getAllUsers();
	}

	 @GetMapping("/transaction")
	public List<Transaction> getAllTransaction() {
		return adminservice.getAllTransaction();
	}

	 @GetMapping("/account")
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return adminservice.getAllAccounts();
	}
}
