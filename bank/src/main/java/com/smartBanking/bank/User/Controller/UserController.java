package com.smartBanking.bank.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartBanking.bank.User.Dto.LoginRequestDTO;
import com.smartBanking.bank.User.Dto.LoginResponsDTO;
import com.smartBanking.bank.User.Dto.UserRequestDTO;
import com.smartBanking.bank.User.Dto.UserResponseDTO;
import com.smartBanking.bank.User.Entity.User;
import com.smartBanking.bank.User.Mapper.UserConverter;
import com.smartBanking.bank.User.Service.UserServiceImp;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImp userserviceimp;
	
	@PostMapping("/register")
	public UserResponseDTO register(@RequestBody UserRequestDTO requestDTO) {
		
		return userserviceimp.register(requestDTO);
	}
	
	@PostMapping("/login")
	public LoginResponsDTO login(@RequestBody LoginRequestDTO loginrequestdto)
	{
		return userserviceimp.login(loginrequestdto);
	}
	

}
