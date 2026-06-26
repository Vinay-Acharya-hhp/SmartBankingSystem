package com.smartBanking.bank.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorusConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry)
			{
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:5173")
				.allowedMethods("*");
			}
		};
	}

}
