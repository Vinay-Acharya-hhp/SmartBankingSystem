package com.smartBanking.bank.User.Mapper;

import java.time.LocalDateTime;

import com.smartBanking.bank.User.Dto.UserRequestDTO;
import com.smartBanking.bank.User.Dto.UserResponseDTO;
import com.smartBanking.bank.User.Entity.User;

public class UserConverter {
	
	//DTO->Entity
	public static User toEntity(UserRequestDTO dto) {
		User user=new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setPhone(dto.getPhone());
		user.setCreatedAt(LocalDateTime.now());
		
		return user;
	}
	
	//Entity->DTO
	public static UserResponseDTO todto(User user) {
		
		UserResponseDTO dto=new UserResponseDTO();
		
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPhone(user.getPhone());
		dto.setCreatedAt(user.getCreatedAt());
		
		return dto;
	}

}
