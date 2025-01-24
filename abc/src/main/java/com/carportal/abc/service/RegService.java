package com.carportal.abc.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carportal.abc.dao.User;
import com.carportal.abc.repository.BidRepository;
import com.carportal.abc.repository.CarRepository;
import com.carportal.abc.repository.RoleRepository;
import com.carportal.abc.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegService {
	@Autowired
	CarRepository carRepo;
	
	@Autowired
	BidRepository bidRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	
	public RegService(UserRepository userRepo) {
		this.userRepo=userRepo;
	}
	
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepo.findBySpecificRoles("user")));
		userRepo.save(user);
	}
	
	public List<User> listAll(){
		return (List<User>) userRepo.findAll();
	}
	
	public User get(Long id) {
		return userRepo.findById(id).get();
	}
	
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
		if (authentication != null && authentication.isAuthenticated()) {
			String email = authentication.getName();
			
			User user = userRepo.findByEmail(email);
			if(user != null) {
				return user;
			}
		}
		return null;
	}
	
//	public boolean changeUserRole(Long userId, String roleName) {
//		Optional<User> optionalUser = userRepo.findById(userId);
//		
//		if(optionalUser.isPresent()) {
//			User user = optionalUser.get();
//			
//			Role role = roleRepo.findByRole(roleName);
//			if(role != null) {
//				user.setRoles(Collections.singleton(role));
//				userRepo.save(user);
//				return true;
//			}
//		}
//		return false;
//	}
	
	public void delete(Long id) {
//		bidRepo.deleteAllByUserId(id);
		userRepo.deleteById(id);
	}
	
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
}
