package com.smartBanking.bank.User.Mapper;

import java.time.LocalDateTime;

import com.smartBanking.bank.User.Dto.UserRequestDTO;
import com.smartBanking.bank.User.Dto.UserResponseDTO;
import com.smartBanking.bank.User.Entity.Users;
import com.smartBanking.bank.User.exception.ResourceAlreadyExistsException;

public class UserConverter {
	
	//DTO->Entity
	public static Users toEntity(UserRequestDTO dto) {
		Users users=new Users();
		users.setName(dto.getName());
		users.setEmail(dto.getEmail());
		users.setPassword(dto.getPassword());
		users.setPhone(dto.getPhone());
		users.setCreatedAt(LocalDateTime.now());
		
		return users;
	}
	
	//Entity->DTO
	public static UserResponseDTO todto(Users users) {
		
		UserResponseDTO dto=new UserResponseDTO();
		
		dto.setId(users.getId());
		dto.setName(users.getName());
		dto.setEmail(users.getEmail());
		dto.setPhone(users.getPhone());
		dto.setCreatedAt(users.getCreatedAt());
		
		return dto;
	}
	
	public static void updateUser(Users users,UserRequestDTO dto)
	{
	 
	 if(dto.getName()!=null) {
		users.setName(dto.getName());
	 }
	 if(dto.getPhone()!=null) {
		users.setPhone(dto.getPhone());
	 }
	  if(dto.getPassword()!=null) {
	  
		users.setPassword(dto.getPassword());
		
	  }
	  
	  
		
	}

}
