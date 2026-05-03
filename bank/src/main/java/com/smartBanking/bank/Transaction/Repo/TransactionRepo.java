package com.smartBanking.bank.Transaction.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartBanking.bank.Transaction.Entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction,String>{
	

}
