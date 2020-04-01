package com.nadeem.bookstore.service;

import java.util.Set;

import com.nadeem.bookstore.domain.User;
import com.nadeem.bookstore.domain.security.UserRole;

public interface UserService {

	User createUser(User user, Set<UserRole> userRoles);
}
