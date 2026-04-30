package com.smartBanking.bank.User.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.smartBanking.bank.User.Dto.LoginRequestDTO;
import com.smartBanking.bank.User.Dto.LoginResponsDTO;
import com.smartBanking.bank.User.Dto.UserRequestDTO;
import com.smartBanking.bank.User.Dto.UserResponseDTO;
import com.smartBanking.bank.User.Entity.User;
import com.smartBanking.bank.User.Mapper.LoginConvertor;
import com.smartBanking.bank.User.Mapper.UserConverter;
import com.smartBanking.bank.User.Repository.UserRepo;
import com.smartBanking.bank.User.exception.*;
@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepo userrepo;
	
	UserServiceImp (UserRepo userrepo){
		this.userrepo=userrepo;
	}

	public UserResponseDTO register(UserRequestDTO requestDTO) {
		if(userrepo.existsByEmail(requestDTO.getEmail())) {
			throw new ResourceAlreadyExistsException("Email Already Registerd");
		}
		User user=UserConverter.toEntity(requestDTO);
		User saveuser=userrepo.save(user);
		
		return UserConverter.todto(saveuser);
	}

	 @Override
		public LoginResponsDTO login(LoginRequestDTO loginrequestdto) {
			User user=userrepo.findByEmail(loginrequestdto.getEmail());
			if(user==null || !user.getPassword().equals(loginrequestdto.getPassword()))
			{
				throw new UserNotFoundException("User Not Found");
			}
			
			return LoginConvertor.toLoginResponsDTO(user);
			
	 }
}






/*
 * @Override
	public LoginResponsDTO login(LoginRequestDTO loginrequestdto) {
		User user=userrepo.findByEmail(loginrequestdto.getEmail());
		if(user==null || !user.getPassword().equals(loginrequestdto.getPassword()))
		{
			throw new UserNotFoundException("User Not Found");
		}
		
		return LoginConvertor.toLoginResponsDTO(user);
	}
 */
