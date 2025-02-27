package com.insure.premium.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for insure premium service.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 26.02.2025
 */
@Configuration
public class SecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  
            )
            .csrf(csrf -> csrf.disable())  
            .formLogin(login -> login.disable()) 
            .httpBasic(basic -> basic.disable());
        
        return http.build();
    }
}
