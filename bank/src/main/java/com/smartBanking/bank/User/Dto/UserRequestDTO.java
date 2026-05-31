package com.smartBanking.bank.User.Dto;

import com.smartBanking.bank.User.Entity.UserType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {
	
	private Long id;
    
	@NotBlank(message="Name is required")
	private String name;
	@Email(message="Invalid Emali")
	private String email;
	@Size(min=10,max=10,message="Phone number must be 10 digits")
	private String phone;
	
	private UserType type;
	@Size(min=6,message="password At leas 6 Chareacters")
	private String password;
	
	
	
	
}
