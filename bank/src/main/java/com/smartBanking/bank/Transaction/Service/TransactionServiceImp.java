package com.smartBanking.bank.Transaction.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
		transaction.setAccount(acc);
	    transaction.setId(transaction.getId());
		transaction.setAmount(amount);
		transaction.setType(TransactionType.CREDIT);
		transaction.setTimestamp(LocalDateTime.now());
		
		Transaction t=transactionRepo.save(transaction);
		t.getId();
		return TransactionConverter.toDto(t);
		
		
	}

	@Override
	public TransactionResponseDTO Withdraw(String accountNumber, BigDecimal amount) {

		Account acc=accountRepo.findByAccountNumber(accountNumber);
		
		if(acc==null) {
			throw new AccountNotFoundException("Account Not Found");
		}
		acc.setBalance(acc.getBalance().subtract(amount));
		accountRepo.save(acc);
		
		Transaction transaction =new Transaction();
		transaction.setAccount(acc);
	    transaction.setId(transaction.getId());
		transaction.setAmount(amount);
		transaction.setType(TransactionType.DEBIT);
		transaction.setTimestamp(LocalDateTime.now());
		
		Transaction t=transactionRepo.save(transaction);
		t.getId();
		return TransactionConverter.toDto(t);
		
	} 

	@Override
	public  TransactionResponseDTO Transfer(String senderAccount,String recieverAccount, BigDecimal amount) {
       Account sender=accountRepo.findByAccountNumber(senderAccount);
       Account reciever=accountRepo.findByAccountNumber(recieverAccount);
		
		if(sender==null) {
			throw new AccountNotFoundException("Account Not Found");
		}
		
		if(reciever==null) {
			throw new AccountNotFoundException("Account Not Found");
		}
		sender.setBalance(sender.getBalance().subtract(amount));
		accountRepo.save(sender);
		
		reciever.setBalance(reciever.getBalance().add(amount));
		accountRepo.save(reciever);
		
		String transid=UUID.randomUUID().toString();
		Transaction send =new Transaction();
		send.setAccount(sender);
	    send.setId(transid);
		send.setAmount(amount);
		send.setType(TransactionType.DEBIT);
		send.setAccount(reciever);
		send.setTimestamp(LocalDateTime.now());

		Transaction recive =new Transaction();
		 recive.setAccount( reciever);
		 recive.setId( transid);
		 recive.setAmount(amount);
		 recive.setType(TransactionType.CREDIT);
		 recive.setAccount(sender);
		 recive.setTimestamp(LocalDateTime.now());
		
		 Transaction t1=transactionRepo.save(send);
		Transaction t2=transactionRepo.save(recive);
		
		return TransactionConverter.tosenderDto(t1);
	}

	@Override
	public List<TransactionResponseDTO> getTransaction(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
