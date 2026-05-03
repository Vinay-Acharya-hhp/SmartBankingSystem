package com.smartBanking.bank.Transaction.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartBanking.bank.Account.Entity.Account;
import com.smartBanking.bank.Account.Repository.AccountRepo;
import com.smartBanking.bank.Transaction.Dto.TransactionResponseDTO;
import com.smartBanking.bank.Transaction.Entity.Transaction;
import com.smartBanking.bank.Transaction.Entity.TransactionType;
import com.smartBanking.bank.Transaction.Mapper.TransactionConverter;
import com.smartBanking.bank.Transaction.Repo.TransactionRepo;
import com.smartBanking.bank.User.exception.AccountNotFoundException;

@Service
public class TransactionServiceImp implements TransactionService {
	
	@Autowired
	private AccountRepo accountRepo;
	private TransactionRepo transactionRepo;
	
	
	public TransactionServiceImp(AccountRepo accountRepo, TransactionRepo transactionRepo) {
		super();
		this.accountRepo = accountRepo;
		this.transactionRepo = transactionRepo;
	}

	@Override
	public TransactionResponseDTO Deposit(String accountNumber, BigDecimal amount) {
		
		Account acc=accountRepo.findByAccountNumber(accountNumber);
		
		if(acc==null) {
			throw new AccountNotFoundException("Account Not Found");
		}
		acc.setBalance(acc.getBalance().add(amount));
		accountRepo.save(acc);
		
		Transaction transaction =new Transaction();
		transaction.setToAccount(acc);
	    transaction.setId(transaction.getId());
		transaction.setAmount(amount);
		transaction.setType(TransactionType.DEPOSIT);
		transaction.setTimestamp(LocalDateTime.now());
		
		Transaction t=transactionRepo.save(transaction);
		t.getId();
		return TransactionConverter.toDto(t);
		
		
	}

	@Override
	public String Withdraw(String accountNumber, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Transfer(String fromAccount, String toaccount, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionResponseDTO> getTransaction(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
