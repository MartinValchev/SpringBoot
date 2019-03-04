package com.spring.project.exodia.service;

import com.spring.project.exodia.entity.User;

public interface UserService {
	boolean isUserAuthenticated(String username, String password);
	
	User storeUser(User user);
	
}
