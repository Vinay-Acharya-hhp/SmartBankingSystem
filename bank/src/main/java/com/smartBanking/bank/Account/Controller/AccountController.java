package com.smartBanking.bank.Account.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartBanking.bank.Account.Dto.AccountRequestDTO;
import com.smartBanking.bank.Account.Dto.AccountResponseDTO;
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

}
