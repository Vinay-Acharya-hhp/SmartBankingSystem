package com.smartBanking.bank.Transaction.Service;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartBanking.bank.Account.Entity.Account;
import com.smartBanking.bank.Account.Repository.AccountRepo;
import com.smartBanking.bank.Transaction.Dto.TransactionResponseDTO;
import com.smartBanking.bank.Transaction.Entity.Transaction;
import com.smartBanking.bank.Transaction.Entity.TransactionType;
import com.smartBanking.bank.Transaction.Mapper.TransactionConverter;
import com.smartBanking.bank.Transaction.Repo.TransactionRepo;
import com.smartBanking.bank.User.exception.AccountNotFoundException;
import com.smartBanking.bank.User.exception.InsufficiantBalanceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionServiceImp implements TransactionService {
	
	
	private static final Logger logger=LoggerFactory.getLogger(TransactionServiceImp.class);
	
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

		if(amount.compareTo(BigDecimal.ZERO)<=0) {
			log.warn("Invalid deposit amount for account number :{}",accountNumber);
			throw new InsufficiantBalanceException("Amount must be greater than zero");	
		}
		
		
		Account acc=accountRepo.findByAccountNumber(accountNumber);
		
		if(acc==null) {
			log.error("Account not fount for account number : {}",accountNumber);
			throw new AccountNotFoundException("Account Not Found");
		}
		
		acc.setBalance(acc.getBalance().add(amount));
		accountRepo.save(acc);
		
		Transaction transaction =new Transaction();
		transaction.setAccount(acc);
	    transaction.setId(transaction.getTransactionId());
		transaction.setAmount(amount);
		transaction.setType(TransactionType.CREDIT);
		
		transaction.setTimestamp(LocalDateTime.now());
		
		Transaction t=transactionRepo.save(transaction);
		logger.info("Amount {} successfully deposited from account number {} ",amount,accountNumber);
		return TransactionConverter.toDto(t);
		
		
	}

	@Override
	public TransactionResponseDTO Withdraw(String accountNumber, BigDecimal amount) {
		
		if(amount.compareTo(BigDecimal.ZERO)<=0) {
			log.warn("Invalid deposit amount for account number :{}",accountNumber);
			throw new InsufficiantBalanceException("Amount must be greater than zero");	
		}
	
		Account acc=accountRepo.findByAccountNumber(accountNumber);
		
		if(acc==null) {
			log.error("Account not found with account number {}",accountNumber);
			throw new AccountNotFoundException("Account Not Found");
		}
		if(acc.getBalance().compareTo(amount)<0) {
			log.warn("Withdraw failed due to Insufficient balance for account number {} ",accountNumber);
			throw new InsufficiantBalanceException("Insufficient Balance ,Your balance less than "+amount);	
		}
		acc.setBalance(acc.getBalance().subtract(amount));
		accountRepo.save(acc);
		
		Transaction transaction =new Transaction();
		transaction.setAccount(acc);
	    transaction.setId(transaction.getTransactionId());
		transaction.setAmount(amount);
		transaction.setType(TransactionType.DEBIT);
		transaction.setTimestamp(LocalDateTime.now());
		
		Transaction t=transactionRepo.save(transaction);
		logger.info("Amount {} successfully deposited from account number {} ",amount,accountNumber);
		return TransactionConverter.toDto(t);
		
	} 

	@Override
	@Transactional
	public  TransactionResponseDTO Transfer(String senderAccount,String recieverAccount, BigDecimal amount) {

		if(amount.compareTo(BigDecimal.ZERO)<=0) {
			log.warn("Invalid Transaction amount for account number :{}",senderAccount);
			throw new InsufficiantBalanceException("Amount must be greater than zero");	
		}
		
       Account sender=accountRepo.findByAccountNumber(senderAccount);
       Account reciever=accountRepo.findByAccountNumber(recieverAccount);
		
		if(sender==null) {
			log.error("Sender Account not found for account number {}",senderAccount);
			throw new AccountNotFoundException("Account Not Found");
		}
		
		if(reciever==null) {
			log.error("Receiver Account not found for account number {}",recieverAccount);
			throw new AccountNotFoundException("Account Not Found");
		}
		if(sender.getBalance().compareTo(amount)<0) {
			log.warn("Transaction failed due to Insufficient balance for account number {}  ",sender.getAccountNumber());
			throw new InsufficiantBalanceException("Insufficient Balance ");	
		}
		
		log.info("Transaction Started from {} to {} amount :{} "
										,sender.getAccountNumber()
										,reciever.getAccountNumber()
										,amount);
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
		Transaction t1=transactionRepo.save(send);
		 log.info("Amount {} send from AccountNumber{}",amount,senderAccount);

		Transaction recive =new Transaction();
		 recive.setAccount( reciever);
		 recive.setId( transid);
		 recive.setAmount(amount);
		 recive.setType(TransactionType.CREDIT);
		 recive.setAccount(sender);
		 recive.setTimestamp(LocalDateTime.now());
		
		
		  transactionRepo.save(recive);
	log.info("Transaction Successfull from {} to {} amount :{} "
									,sender.getAccountNumber()
									,reciever.getAccountNumber()
									,amount);

		return TransactionConverter.tosenderDto(t1);
	}

	@Override
	public List<TransactionResponseDTO> getTransaction(String accountNumber) {
		Account account=accountRepo.findByAccountNumber(accountNumber);
		if(account==null) {
			log.error(" Account not found for account number {}",accountNumber);
			throw new AccountNotFoundException("Account Not Found");
		}
		
		List<Transaction> transactions=transactionRepo.findByAccount(account);
		
		List<TransactionResponseDTO>response=new ArrayList<>();
		for(Transaction t:transactions) {
			response.add(TransactionConverter.tosenderDto(t));
		}
		log.info("Fetched Transaction details for account number :{}",accountNumber);
		return response;
	}
	
	

}
