package com.carportal.abc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carportal.abc.dao.Role;
import com.carportal.abc.dao.User;
import com.carportal.abc.repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepo) {
	        this.userRepo = userRepo;
	    }
	
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		System.out.println("user with this email: "+username+ "is logged in");
		
		User user = userRepo.findByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("user with email "+username+" is invalid");
		}
		
		org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User.builder();
	
		String[] roleNames = user.getRoles().stream().map(Role::getRole).toArray(String[]::new);
		
		System.out.println("The user's role is "+roleNames);
		
		return userBuilder.username(user.getEmail())
				.password(user.getPassword())
				.roles(roleNames)
//				.passwordEncoder(passwordEncoder::encode)
				.build();
	}
}
