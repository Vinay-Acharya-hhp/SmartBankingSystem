package com.smartBanking.bank.Transaction.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartBanking.bank.Account.Entity.Account;
import com.smartBanking.bank.Transaction.Entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction,String>{
	List<Transaction> findByAccount(Account account);

}
