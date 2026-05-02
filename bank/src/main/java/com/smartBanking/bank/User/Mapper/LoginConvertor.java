package com.smartBanking.bank.User.Mapper;

import com.smartBanking.bank.User.Dto.LoginRequestDTO;

import com.smartBanking.bank.User.Dto.LoginResponsDTO;
import com.smartBanking.bank.User.Entity.Users;

public class LoginConvertor {
	
	
	//RequestDto->Entity
	public static Users toLoginEntity(LoginRequestDTO dto)
	{
		Users users=new Users();
		users.setEmail(dto.getEmail());
		users.setPassword(dto.getPassword());
		
		return users;
	}
	
	//Entity->ResponsDTO
	public static LoginResponsDTO  toLoginResponsDTO (Users users)
	{
		LoginResponsDTO dto =new LoginResponsDTO();
		dto.setToken("stoken created");
		dto.setId(users.getId());
		dto.setName(users.getName());
		dto.setEmail(users.getEmail());
		dto.setMessage("Login succefull");
		return dto;
		
	}

}
