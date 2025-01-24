package com.carportal.abc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		return new UserServiceImpl();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf( csrf -> csrf.disable()
					)
			.authorizeHttpRequests(auth->{
				auth.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll();
				auth.requestMatchers("/","/css/**","/js/**","/images/**").permitAll();
				auth.requestMatchers("/login").permitAll();
				auth.requestMatchers("/register").permitAll();
				auth.requestMatchers("/registered").permitAll();
				auth.anyRequest().authenticated();
			})
			.formLogin(
					form -> form
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.failureUrl("/login_error")
					.defaultSuccessUrl("/")
					.permitAll()
				)
			.logout(
					logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.permitAll()
					);
		return http.build();
	}
}
