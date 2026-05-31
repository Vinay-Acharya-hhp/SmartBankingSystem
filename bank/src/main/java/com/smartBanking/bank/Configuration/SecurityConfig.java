package com.smartBanking.bank.Configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MyUserDetailsService userDetailService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http.csrf(customizer->customizer.disable())
				.authorizeHttpRequests(req->req
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers("/user/register",
								          "/user/login").permitAll()
						.requestMatchers("/user/**","/transaction/**")
						.hasRole("USER")
						.requestMatchers("/admin/**")
						.hasAnyRole("ADMIN")
						.anyRequest().authenticated())
				//.formLogin(Customizer.withDefaults())
				.headers(h->h.frameOptions(f->f.disable()))
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}
	
	
//	@Bean
//	public UserDetailsService userDetaailsService() {
//		UserDetails user1=User.withUsername("v")
//				.password(passwordEncoder().encode("v@123"))
//				.roles("USER")
//				.build();
//		
//		UserDetails user2=User.withUsername("a")
//				.password(passwordEncoder().encode("a@123"))
//				.roles("USER")
//				.build();
//		
//		
//		return new InMemoryUserDetailsManager(user1,user2);
//	}
//	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationprovider() {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailService);
		
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config)throws Exception{
		return config.getAuthenticationManager();
		
	}
	
	
	
	
	
	

}

	
//	@Bean
//	public AuthenticationProvider authenticationprovider() {
//		
//		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userdetailsservice);
//		
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
////		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
////		provider.setUserDetailsPasswordService((UserDetailsPasswordService) userdetailsservice);
////		
//		provider.setUserDetailsPasswordService((UserDetailsPasswordService) userdetailsservice);
//		return provider;
//	}

	/*
	
	@Bean
	public UserDetailsService userdetailservice() {
	UserDetails user1=User
	        .withDefaultPasswordEncoder()
			.username("madhu")
			.password("123")
				.roles("USER")
				.build();
		UserDetails user2=User
		        .withDefaultPasswordEncoder()
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
