package com.smartBanking.bank.Account.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartBanking.bank.Account.Dto.AccountRequestDTO;
import com.smartBanking.bank.Account.Dto.AccountResponseDTO;
import com.smartBanking.bank.Account.Entity.Account;
import com.smartBanking.bank.Account.Mapper.AccountConverter;
import com.smartBanking.bank.Account.Service.AccountService;
import com.smartBanking.bank.User.Dto.UserResponseDTO;
import com.smartBanking.bank.apiResponse.ApiResponse;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountservice;

	public AccountController(AccountService accountservice) {
		super();
		this.accountservice = accountservice;
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<AccountResponseDTO>> creatAccount (@RequestBody AccountRequestDTO requestdto)
	{
		AccountResponseDTO saved=accountservice.createAccount(requestdto);
		 ApiResponse<AccountResponseDTO> response = new ApiResponse<> ("Account Created Succesfull", saved ,true , 201);
			
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}   
	
	@GetMapping("/get_ac/{accountNumber}")
	public ResponseEntity<ApiResponse<AccountResponseDTO>> getAccountByAccountNumber(@PathVariable String accountNumber) {
		AccountResponseDTO saved=accountservice.getAccountByAccountNumber(accountNumber);
		 ApiResponse<AccountResponseDTO> response = new ApiResponse<> ("Welcome "+accountNumber, saved ,true , 200);
			
			return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/get_e/{email}")
	public ResponseEntity<ApiResponse<List<AccountResponseDTO>>> getAccountByEmail(@PathVariable String email) {
		List<AccountResponseDTO> saved=accountservice.getAccountByEmail(email);
		 ApiResponse<List<AccountResponseDTO>> response = new ApiResponse<> ("Welcome "+email, saved ,true , 200);
			
			return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	
	@PutMapping("{accountNumber}/deposit")
	public ResponseEntity<ApiResponse<AccountResponseDTO>> Deposit(@PathVariable String accountNumber,
			@RequestParam BigDecimal amount) {
		AccountResponseDTO saved=accountservice.deposit(accountNumber, amount);
		 ApiResponse<AccountResponseDTO> response = new ApiResponse<> ("Amount deposited "+amount, saved ,true , 200);
			
			return new ResponseEntity<>(response,HttpStatus.OK);
	
	}
	

	@PutMapping("{accountNumber}/withdrow")
	public ResponseEntity<ApiResponse<AccountResponseDTO>> Withdrow(@PathVariable String accountNumber,
			@RequestParam BigDecimal amount) {
		AccountResponseDTO saved=accountservice.withdrow(accountNumber, amount);
		 ApiResponse<AccountResponseDTO> response = new ApiResponse<> ("Amount Withdrowd "+amount, saved ,true , 200);
			
			return new ResponseEntity<>(response,HttpStatus.OK);
	
	}


}
