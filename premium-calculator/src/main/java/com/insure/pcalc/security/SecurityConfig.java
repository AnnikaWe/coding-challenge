package com.insure.pcalc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for client requests.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 26.02.2025
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	                .requestMatchers(HttpMethod.POST, "/insurance/premium").authenticated() 
	            .and()
	            .httpBasic() 
	            .and()
	            .csrf().disable(); 

	        return http.build();
	    }

}
