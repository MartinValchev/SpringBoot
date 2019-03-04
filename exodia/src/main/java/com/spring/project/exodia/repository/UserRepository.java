package com.spring.project.exodia.repository;

import com.spring.project.exodia.entity.User;

public interface UserRepository{
		User getUserByNameAndPass(String username, String password);
		User saveUser(User user);
}
