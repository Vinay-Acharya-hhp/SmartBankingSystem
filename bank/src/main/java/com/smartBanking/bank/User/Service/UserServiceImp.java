package com.smartBanking.bank.User.Service;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartBanking.bank.Configuration.JWTService;
import com.smartBanking.bank.User.Dto.LoginRequestDTO;
import com.smartBanking.bank.User.Dto.LoginResponsDTO;
import com.smartBanking.bank.User.Dto.UserRequestDTO;
import com.smartBanking.bank.User.Dto.UserResponseDTO;
import com.smartBanking.bank.User.Entity.UserType;
import com.smartBanking.bank.User.Entity.Users;
import com.smartBanking.bank.User.Mapper.LoginConvertor;
import com.smartBanking.bank.User.Mapper.UserConverter;
import com.smartBanking.bank.User.Repository.UserRepo;
import com.smartBanking.bank.User.exception.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private JWTService jwtservice;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	//private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	UserServiceImp (UserRepo userrepo,PasswordEncoder passwordencoder){
		this.userrepo=userrepo;
		this.passwordencoder=passwordencoder;
	}
	
	

	public UserResponseDTO register(UserRequestDTO requestDTO) {
		if(userrepo.existsByEmail(requestDTO.getEmail())) {
			log.warn("Registration failed. Email alreday exists: {}",requestDTO.getEmail());
			throw new ResourceAlreadyExistsException("Email Already Registerd");
		}
		Users users=UserConverter.toEntity(requestDTO);
		if(users.getType()==null) {
			users.setType(UserType.USER);
		}
		users.setPassword(passwordencoder.encode(requestDTO.getPassword()));
		Users saveuser=userrepo.save(users);
		log.info("User Sucessfully Register {}",requestDTO.getEmail());
		return UserConverter.todto(saveuser);
	}
//
//	 @Override
//		public LoginResponsDTO login(LoginRequestDTO loginrequestdto) {
//		
//			Users users=userrepo.findByEmail(loginrequestdto.getEmail());
//			if(users==null )
//			{
//				log.warn("Email not found {}",loginrequestdto.getEmail());
//				throw new UserNotFoundException("User Not Found");
//			}
//			if(!passwordencoder.matches(loginrequestdto.getPassword(), users.getPassword())) {
//				log.warn("Invalid password attempt for Email : {} ",loginrequestdto.getEmail());
//				throw new RuntimeException("Invalid Password");
//			}
//			log.info("User Login Successfully {}",loginrequestdto.getEmail());
//			return LoginConvertor.toLoginResponsDTO(users);
//			
//	 }

	 @Override
	 public UserResponseDTO update(String email, UserRequestDTO requestDTO) {
		 
		 log.info("Started searching {}",email);
		 Users users=userrepo.findByEmail(email);
		 
		 if(users==null )
			{
			 log.error("email not found{}",email);
				throw new UserNotFoundException("User Not Found");
			}
		 UserConverter.updateUser(users, requestDTO);
		 
		 Users saveuser=userrepo.save(users);
		 log.info("Updated succesfully{}",email);
		return UserConverter.todto(saveuser);
	 }

	 @Override
	 public String DeleteUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	 }


	 @Override
	 public String verify(LoginRequestDTO loginrequestdto) {
		 Authentication authenticat=
				 authmanager.authenticate(new UsernamePasswordAuthenticationToken(loginrequestdto.getEmail(),loginrequestdto.getPassword()));
	    if(authenticat.isAuthenticated()) {
	    	 String token = jwtservice.generateToken(loginrequestdto.getEmail());
	 
     System.out.println(token);
	    	System.out.print("success");
	    	return token;
	    }
		
	    return "failed";
	 }
} 
	
	 
	 
	 
	 
//	 
//	 @Override
//	 public LoginResponsDTO verify(LoginRequestDTO loginrequestdto) {
//
//	     Authentication authentication =
//	             authmanager.authenticate(
//	                     new UsernamePasswordAuthenticationToken(
//	                             loginrequestdto.getEmail(),
//	                             loginrequestdto.getPassword()));
//
//	     if (authentication.isAuthenticated()) {
//
//	         String token = jwtservice.generateToken(loginrequestdto.getEmail());
//
//	         System.out.println(token);
//
////	         LoginResponsDTO response = new LoginResponsDTO();
////	         response.setEmail(loginrequestdto.getEmail());
////	         response.setToken(token);
//
//	         return LoginConvertor.toLoginResponsDTO(response);
//	     }
//
//	     throw new RuntimeException("Login Failed");
//	 }







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
