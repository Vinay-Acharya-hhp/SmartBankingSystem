package com.smartBanking.bank.Configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.smartBanking.bank.User.Entity.UserType;
import com.smartBanking.bank.User.Entity.Users;
import com.smartBanking.bank.User.Repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {
	
	private final UserRepo repo;
	private final PasswordEncoder passwordencoder;

	@Override
	public void run(String... args) throws Exception {
		
		if(repo.findByEmail("admin@bank.com")==null) {
			
			Users admin=new Users();
			admin.setName("Admin");
			admin.setEmail("admin@bank.com");
			admin.setPassword(passwordencoder.encode("admin123"));
			admin.setType(UserType.ADMIN);
			repo.save(admin);
			System.out.print("Admin Created");
		}
	}

}
