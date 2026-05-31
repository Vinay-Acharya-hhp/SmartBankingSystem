package com.smartBanking.bank.Transaction.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartBanking.bank.Transaction.Dto.TransactionResponseDTO;
import com.smartBanking.bank.Transaction.Service.TransactionService;
import com.smartBanking.bank.User.Dto.UserResponseDTO;
import com.smartBanking.bank.apiResponse.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("transaction")
public class TransactionController {
	
	private TransactionService transactionservice;

	public TransactionController(TransactionService transactionservice) {
		super();
		this.transactionservice = transactionservice;
	}
	
	
	
 @PostMapping("/deposit")
 public ResponseEntity<ApiResponse<TransactionResponseDTO>> deposit
 												(@RequestParam String accountNumber,
 												 @RequestParam BigDecimal amount) {
	 log.info("Amount Deposit request received for account number :{}",accountNumber);
	 TransactionResponseDTO saved = transactionservice.Deposit(accountNumber, amount);
	 ApiResponse<TransactionResponseDTO> response = new ApiResponse<> 
	  											("Deposited Succesfull", saved ,true , 200);
	 return new ResponseEntity<>(response,HttpStatus.OK);
	  
 }
 
 
 
 @PostMapping("/withdraw")
 public ResponseEntity<ApiResponse<TransactionResponseDTO>> withdraw
 												(@RequestParam String accountNumber,
 												 @RequestParam BigDecimal amount) {
	 log.info("Amount Withdraw request received for account number :{}",accountNumber);
	 TransactionResponseDTO saved = transactionservice.Withdraw(accountNumber, amount);
	 ApiResponse<TransactionResponseDTO> response = new ApiResponse<>
	 											("Withdrow Succesfull", saved ,true , 200);
	return new ResponseEntity<>(response,HttpStatus.OK);
	  
 }
 

 @PostMapping("/transfer")
 public ResponseEntity<ApiResponse<TransactionResponseDTO>> withdraw
 													(@RequestParam String sender,
		 											 @RequestParam String reciever,
		 											 @RequestParam BigDecimal amount) {
	 log.info("Transfer Request received from {} to {} for Amount {}",sender,reciever,amount);
	 TransactionResponseDTO saved = transactionservice.Transfer(sender,reciever, amount);
	 ApiResponse<TransactionResponseDTO> response = new ApiResponse<>
													(" Succesfully Transferd", saved ,true , 200);
	 return new ResponseEntity<>(response,HttpStatus.OK);
	  
 }
 
 @GetMapping("/transactions")
 public ResponseEntity<ApiResponse<List<TransactionResponseDTO>>> transactions
 													(@RequestParam String accountnumber) {
	 log.info("Fetching transaction details for account number : {}", accountnumber);
	 List<TransactionResponseDTO> saved = transactionservice.getTransaction(accountnumber);
	 ApiResponse<List<TransactionResponseDTO>> response = new ApiResponse<> 
	 												("Succesfully fetched", saved ,true , 200);
	 return new ResponseEntity<>(response,HttpStatus.OK);

}

}
