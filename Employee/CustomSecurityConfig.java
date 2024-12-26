package com.example.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Disable CSRF protection for testing
            .authorizeHttpRequests()
            .requestMatchers("/api/**").permitAll()  // Allow all API endpoints without authentication
            .anyRequest().permitAll();  // Allow all other requests too
        return http.build();
    }
}


