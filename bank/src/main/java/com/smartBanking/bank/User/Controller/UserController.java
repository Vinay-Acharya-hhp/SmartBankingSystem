package com.smartBanking.bank.User.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartBanking.bank.Configuration.JWTService;
import com.smartBanking.bank.User.Dto.LoginRequestDTO;
import com.smartBanking.bank.User.Dto.LoginResponsDTO;
import com.smartBanking.bank.User.Dto.UserRequestDTO;
import com.smartBanking.bank.User.Dto.UserResponseDTO;
import com.smartBanking.bank.User.Entity.Users;
import com.smartBanking.bank.User.Mapper.UserConverter;
import com.smartBanking.bank.User.Repository.UserRepo;
//import com.smartBanking.bank.User.Entity.User;
//import com.smartBanking.bank.User.Mapper.UserConverter;
import com.smartBanking.bank.User.Service.UserServiceImp;
import com.smartBanking.bank.User.exception.UserNotFoundException;
import com.smartBanking.bank.apiResponse.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins="http://localhost:5173")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImp userserviceimp;
	
	
	
	@Autowired
	private UserRepo repo;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserResponseDTO>> register
											(@Valid @RequestBody UserRequestDTO requestDTO) {
		log.info("Registration request received for Email  {}",requestDTO.getEmail());
		UserResponseDTO saved = userserviceimp.register(requestDTO);
	    ApiResponse<UserResponseDTO> response = new ApiResponse<> 
	    										("Registration Succesfull", saved ,true , 201);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
		
	}
	
	
    
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequestDTO loginrequestdto) {
		log.info("Login request received for Email {}",loginrequestdto.getEmail());
		try {
		String token= userserviceimp.verify(loginrequestdto);
		ApiResponse<String> response=new ApiResponse<>
		  ("Login Succefully",token,true ,200);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		}catch(UserNotFoundException e) {
			ApiResponse<String> response=new ApiResponse<>
			  ("User Not Found",null,false ,404);
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		catch(BadCredentialsException e) {
			ApiResponse<String> response=new ApiResponse<>
			  ("Invalid Password",null,false ,401);
			return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
		}
	}
}
	
//	@PostMapping("/login")
//	public ResponseEntity<ApiResponse<LoginResponsDTO>> verify
//											    (@RequestBody LoginRequestDTO loginrequestdto){  
//		log.info("Login request received for Email {}",loginrequestdto.getEmail());
//		
//		
//		 userserviceimp.verify(loginrequestdto);
//		LoginResponsDTO loginDTO = userserviceimp.login(loginrequestdto);
//		ApiResponse<LoginResponsDTO> response=new ApiResponse<>
//												("Login Succefully",loginDTO,true ,200);
//		
//		return new ResponseEntity<>(response,HttpStatus.OK);
//		
//		
//	}
	
//	@PutMapping("/update/{email}")
//	 public ResponseEntity<ApiResponse<UserResponseDTO>> update
//	 							(@PathVariable String email,@RequestBody UserRequestDTO requestDTO) {
//		log.info("Update request received for Email {}",email);
//		UserResponseDTO saved = userserviceimp.update(email, requestDTO);
//	    ApiResponse<UserResponseDTO> response = new ApiResponse<>
//	    							("Update Succesfull", saved ,true , 200);
//		return new ResponseEntity<>(response,HttpStatus.OK);
//	 }
//
//
//	
//
//	@GetMapping("/getall")
//	public List<Users> getall()
//	{
//		 List<Users> users=repo.findAll();
//		 return users;
//	}
//	
//	
//	
//	
//}

	







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
		
		
		
	@GetMapping("/get")
	public String getstring(HttpServletRequest request) {
		return "hello"+request.getSession().getId();
	}
	
	@GetMapping("/getall")
	public List<User> getall()
	{
		 List<User> user=repo.findAll();
		 return user;
	}
	
	@GetMapping("/csrf")
	public CsrfToken getToken(HttpServletRequest request) {
		return (CsrfToken)request.getAttribute("_csrf");
	}
	}
 */
