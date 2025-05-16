package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.AdminDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	 @Autowired
	    private AdminDetailsService adminDetailsService;
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/admin/signup", "/admin/signin", "/css/**", "/js/**").permitAll()
	                .requestMatchers("/admin/**").authenticated()
	                .anyRequest().permitAll()
	            )
	            .formLogin(login -> login
	                .loginPage("/admin/signin")
	                .loginProcessingUrl("/admin/signin")
	                .defaultSuccessUrl("/admin/contacts", true)
	                .permitAll()
	            )
	            .logout(logout -> logout
	                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
	                .logoutSuccessUrl("/admin/signin")
	                .invalidateHttpSession(true)
	                .deleteCookies("JSESSIONID")
	                .permitAll()
	            );

	        return http.build();
	    }

}
