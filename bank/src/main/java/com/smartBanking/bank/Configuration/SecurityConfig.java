package com.smartBanking.bank.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

@Bean
public PasswordEncoder passwordencoder() {
	return new BCryptPasswordEncoder();
}
	

	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) {
		
		//h2 database
		http.securityMatcher("/h2-console/**");
		http.csrf(customizer->customizer.disable());
		//h2 database
		http.headers(headers->headers.frameOptions(frame->frame.disable()));
		http.authorizeHttpRequests(req->req.anyRequest().permitAll());
		//http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();

}
	
}
	/*
	@Bean
	public AuthenticationProvider authenticationprovider() {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		//provider.setUserDetailsPasswordService();
		
		return provider;
	}
}
	/*
	
	@Bean
	public UserDetailsService userdetailservice() {
	UserDetails user1=User.withDefaultPasswordEncoder()
			.username("madhu")
			.password("123")
				.roles("USER")
				.build();
		UserDetails user2=User.withDefaultPasswordEncoder()
				.username("vikas")
				.password("123")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user1,user2);
	}
	
	
}

	/*
	
	
	
	
}
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	
	
	
	
	@Bean
	public UserDetailsService userdetailservice() {
	UserDetails user1=User.withDefaultPasswordEncoder()
			.username("madhu")
			.password("123")
				.roles("USER")
				.build();
		UserDetails user2=User.withDefaultPasswordEncoder()
				.username("vikas")
				.password("123")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user1,user2);
		
	}
	
	*/
