package com.smartBanking.bank.User.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
	
	
	private String email;
	
	private String password;


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		
		return email;
	}

	public String getPassword() {
		
		return password;
	}
	

}
