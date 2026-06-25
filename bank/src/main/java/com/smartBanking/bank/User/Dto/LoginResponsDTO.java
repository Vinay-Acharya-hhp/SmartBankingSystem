package com.smartBanking.bank.User.Dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponsDTO {
      
	private String token;
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String message;

	
	
}
