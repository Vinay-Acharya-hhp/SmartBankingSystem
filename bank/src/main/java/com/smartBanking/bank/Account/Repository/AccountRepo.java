package com.smartBanking.bank.Account.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartBanking.bank.Account.Entity.Account;

public interface AccountRepo extends JpaRepository<Account,Long>{

	
	
}
