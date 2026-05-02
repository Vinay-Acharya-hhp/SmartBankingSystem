package com.smartBanking.bank.User.Service;

import java.util.Optional;

//import org.apache.el.stream.Optional;

import com.smartBanking.bank.User.Dto.LoginRequestDTO;
import com.smartBanking.bank.User.Dto.LoginResponsDTO;
import com.smartBanking.bank.User.Dto.UserRequestDTO;
import com.smartBanking.bank.User.Dto.UserResponseDTO;

public interface UserService {
	
	UserResponseDTO register(UserRequestDTO requestDTO);
	
	LoginResponsDTO login(LoginRequestDTO loginrequestdto);
	
	UserResponseDTO update(String email,UserRequestDTO requestDTO);
	
	String DeleteUser (Long id);

}
