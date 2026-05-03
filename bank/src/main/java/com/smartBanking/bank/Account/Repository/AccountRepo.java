package com.smartBanking.bank.Account.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartBanking.bank.Account.Entity.Account;

public interface AccountRepo extends JpaRepository<Account,Long>{

	Account findByAccountNumber(String accountNumber);
	
	List<Account> findByUsersEmail(String email);
	
	boolean existsByAccountNumber(String accountNumber);
}
