package com.smartBanking.bank.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartBanking.bank.User.Entity.Users;
import com.smartBanking.bank.User.Entity.UsersPrincipal;
import com.smartBanking.bank.User.Repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	//@Autowired
	private UserRepo repo;
	

	public MyUserDetailsService(UserRepo repo) {
	
		this.repo = repo;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Users user =repo.findByEmail(email);
		
		if(user==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		return new UsersPrincipal(user);
	}

}
