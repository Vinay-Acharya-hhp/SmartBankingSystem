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
	private String id;
	
	@ManyToOne
	@JoinColumn(name="from_account_id")
	private Account fromAccount;
	
	@ManyToOne
	@JoinColumn(name="to_account_id")
	private Account toAccount;
	
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

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
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
