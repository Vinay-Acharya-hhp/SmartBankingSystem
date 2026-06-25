package com.smartBanking.bank.Account.Entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private Users users;

	@JsonManagedReference
	@OneToMany(mappedBy="account")
	private List<Transaction> sentTransaction;


	
	
}
