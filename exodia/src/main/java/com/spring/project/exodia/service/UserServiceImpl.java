package com.spring.project.exodia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.exodia.entity.User;
import com.spring.project.exodia.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Override
	public boolean isUserAuthenticated(String username, String password) {
		
		return userRepository.getUserByNameAndPass(username, password)!=null;
	}

	@Override
	public User storeUser(User user) {
		return userRepository.saveUser(user);
	}

}
