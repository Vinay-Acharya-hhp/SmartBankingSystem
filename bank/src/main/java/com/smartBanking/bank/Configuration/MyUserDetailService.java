package com.smartBanking.bank.Configuration;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.smartBanking.bank.User.Entity.UsersPrincipal;

import com.smartBanking.bank.User.Entity.User;
import com.smartBanking.bank.User.Repository.UserRepo;


@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=repo.findByName(username);
		if(user==null) {
			System.out.print("User not fount");
			throw new UsernameNotFoundException("user Not Found");
		}
		
		return UsersPrincipal(user);
	}

	
}
	
	*/
