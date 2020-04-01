package com.nadeem.bookstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nadeem.bookstore.domain.User;
import com.nadeem.bookstore.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{

	private static final Logger LOG=LoggerFactory.getLogger(UserSecurityService.class);
	
	@Autowired 
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("In User Security Service :" +username);
		User user=userRepository.findByUsername(username);
		if(null==user) {
			LOG.warn("Username not Found", username);
			throw new UsernameNotFoundException("Username :" +username+"not found");
		}
		System.out.println("User is :" +user.getUsername()+"===="+user.getPassword());
		return user;
	}
	
	
	
}
