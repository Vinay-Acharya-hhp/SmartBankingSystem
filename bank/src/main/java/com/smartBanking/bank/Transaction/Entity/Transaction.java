package com.smartBanking.bank.Transaction.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.smartBanking.bank.Account.Entity.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private String transactionId;
	
	
	
	private String id;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	
	
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	private TransactionType type;


	private LocalDateTime timestamp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account fromAccount) {
		this.account = fromAccount;
	}


	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
