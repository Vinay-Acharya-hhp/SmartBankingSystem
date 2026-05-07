package com.smartBanking.bank.User.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponseDTO {
	
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private LocalDateTime createdAt;

	

	
}
