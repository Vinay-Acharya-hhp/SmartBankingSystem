package com.smartBanking.bank.Transaction.Controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartBanking.bank.Transaction.Dto.TransactionResponseDTO;
import com.smartBanking.bank.Transaction.Service.TransactionService;
import com.smartBanking.bank.User.Dto.UserResponseDTO;
import com.smartBanking.bank.apiResponse.ApiResponse;

@RestController
@RequestMapping("transaction")
public class TransactionController {
	
	private TransactionService transactionservice;

	public TransactionController(TransactionService transactionservice) {
		super();
		this.transactionservice = transactionservice;
	}
	
	
	
 @PostMapping("/deposit")
 public ResponseEntity<ApiResponse<TransactionResponseDTO>> deposit(@RequestParam String accountNumber,
		 			   @RequestParam BigDecimal amount) {
	 
	 TransactionResponseDTO saved = transactionservice.Deposit(accountNumber, amount);
		
	   ApiResponse<TransactionResponseDTO> response = new ApiResponse<> ("Deposited Succesfull", saved ,true , 200);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	  
 }
 

}
