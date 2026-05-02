package com.smartBanking.bank.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	
	//private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	UserServiceImp (UserRepo userrepo,PasswordEncoder passwordencoder){
		this.userrepo=userrepo;
		this.passwordencoder=passwordencoder;
	}
	
	

	public UserResponseDTO register(UserRequestDTO requestDTO) {
		if(userrepo.existsByEmail(requestDTO.getEmail())) {
			throw new ResourceAlreadyExistsException("Email Already Registerd");
		}
		User user=UserConverter.toEntity(requestDTO);
		user.setPassword(passwordencoder.encode(requestDTO.getPassword()));
		User saveuser=userrepo.save(user);
		
		return UserConverter.todto(saveuser);
	}

	 @Override
		public LoginResponsDTO login(LoginRequestDTO loginrequestdto) {
			User user=userrepo.findByEmail(loginrequestdto.getEmail());
			if(user==null )
			{
				throw new UserNotFoundException("User Not Found");
			}
			if(!passwordencoder.matches(loginrequestdto.getPassword(), user.getPassword())) {
				throw new RuntimeException("Invalid Password");
			}
			
			return LoginConvertor.toLoginResponsDTO(user);
			
	 }

	 @Override
	 public UserResponseDTO update(String email, UserRequestDTO requestDTO) {
		 User user=userrepo.findByEmail(email);
		 
		 if(user==null )
			{
				throw new UserNotFoundException("User Not Found");
			}
		 UserConverter.updateUser(user, requestDTO);
		 
		 User saveuser=userrepo.save(user);
		 
		return UserConverter.todto(saveuser);
	 }

	 @Override
	 public String DeleteUser(Long id) {
		// TODO Auto-generated method stub
		return null;
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
