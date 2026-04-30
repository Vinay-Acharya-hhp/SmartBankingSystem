package com.smartBanking.bank.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartBanking.bank.User.Dto.LoginRequestDTO;
import com.smartBanking.bank.User.Dto.LoginResponsDTO;
import com.smartBanking.bank.User.Dto.UserRequestDTO;
import com.smartBanking.bank.User.Dto.UserResponseDTO;
//import com.smartBanking.bank.User.Entity.User;
//import com.smartBanking.bank.User.Mapper.UserConverter;
import com.smartBanking.bank.User.Service.UserServiceImp;
import com.smartBanking.bank.User.exception.UserNotFoundException;
import com.smartBanking.bank.apiResponse.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImp userserviceimp;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserResponseDTO>> register(@Valid @RequestBody UserRequestDTO requestDTO) {
		
		UserResponseDTO saved = userserviceimp.register(requestDTO);
		
	   ApiResponse<UserResponseDTO> response = new ApiResponse<> ("Registration Succesfull", saved ,true , 201);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponsDTO>> login(@RequestBody LoginRequestDTO loginrequestdto)
	
	{  
		LoginResponsDTO loginDTO = userserviceimp.login(loginrequestdto);
		
		ApiResponse<LoginResponsDTO> response=new ApiResponse<>("Login Succefully",loginDTO,true ,200);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	
		
		
	}
}
	







/*
 * 	
		UserResponseDTO saved = userserviceimp.register(requestDTO);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
		
		
		
		@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponsDTO>> login(@RequestBody LoginRequestDTO loginrequestdto)
	
	{  try {
		LoginResponsDTO loginDTO = userserviceimp.login(loginrequestdto);
		
		ApiResponse<LoginResponsDTO> response=new ApiResponse<>("Login Succefully",loginDTO,true ,200);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}catch(UserNotFoundException e) {
		
		ApiResponse<LoginResponsDTO> response=new ApiResponse<>(e.getMessage(),null,false ,404);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponsDTO>> login(@RequestBody LoginRequestDTO loginrequestdto)
	
	{  try {
		LoginResponsDTO loginDTO = userserviceimp.login(loginrequestdto);
		
		ApiResponse<LoginResponsDTO> response=new ApiResponse<>("Login Succefully",loginDTO,true ,200);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}catch(UserNotFoundException e) {
		
		ApiResponse<LoginResponsDTO> response=new ApiResponse<>(e.getMessage(),null,false ,404);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
 */
