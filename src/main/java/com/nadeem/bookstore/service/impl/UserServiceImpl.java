package com.nadeem.bookstore.service.impl;

import java.util.Set;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nadeem.bookstore.domain.User;
import com.nadeem.bookstore.domain.security.UserRole;
import com.nadeem.bookstore.repository.RoleRepository;
import com.nadeem.bookstore.repository.UserRepository;
import com.nadeem.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) {
		LOG.info("------------------------------------in UserService creaeUsaerMethod-------------------------------");
		User localUser = userRepository.findByUsername(user.getUsername());
		if (localUser != null) {
			LOG.info("User with username { } alresy exist Nothing will be Done ", user.getUsername());
		} else {
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			localUser = userRepository.save(user);
		}
		LOG.info("------------------------------------Before return localuser UserService creaeUsaerMethod-------------------------------" +localUser);
		return localUser;
	}

}
