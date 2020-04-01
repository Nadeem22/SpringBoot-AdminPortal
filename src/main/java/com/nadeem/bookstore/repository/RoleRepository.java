package com.nadeem.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.nadeem.bookstore.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

}
