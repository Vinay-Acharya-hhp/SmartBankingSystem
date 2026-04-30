package com.smartBanking.bank.User.Mapper;

import com.smartBanking.bank.User.Dto.LoginRequestDTO;

import com.smartBanking.bank.User.Dto.LoginResponsDTO;
import com.smartBanking.bank.User.Entity.User;

public class LoginConvertor {
	
	
	//RequestDto->Entity
	public static User toLoginEntity(LoginRequestDTO dto)
	{
		User user=new User();
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		
		return user;
	}
	
	//Entity->ResponsDTO
	public static LoginResponsDTO  toLoginResponsDTO (User user)
	{
		LoginResponsDTO dto =new LoginResponsDTO();
		dto.setToken("stoken created");
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setMessage("Login succefull");
		return dto;
		
	}

}
