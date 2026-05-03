package com.smartBanking.bank.Account.Entity;

import java.math.BigDecimal;
import java.util.List;

import com.smartBanking.bank.Transaction.Entity.Transaction;
import com.smartBanking.bank.User.Entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_account")
public class Account {
	
	
	
	

	public Account() {
		
	}

	public Account(Long id, String accountNumber, AccountType accountType, BigDecimal balance, Users users) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,unique=true)
	private String accountNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private AccountType accountType;
	
	@Column(nullable=false)
	private BigDecimal balance;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private Users users;

	
	@OneToMany(mappedBy="fromAccount")
	private List<Transaction> sentTransaction;
	@OneToMany(mappedBy="toAccount")
	private List<Transaction> receivedTransaction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	public List<Transaction> getSentTransaction() {
		return sentTransaction;
	}

	public void setSentTransaction(List<Transaction> sentTransaction) {
		this.sentTransaction = sentTransaction;
	}

	public List<Transaction> getReceivedTransaction() {
		return receivedTransaction;
	}

	public void setReceivedTransaction(List<Transaction> receivedTransaction) {
		this.receivedTransaction = receivedTransaction;
	}

	
	
}
