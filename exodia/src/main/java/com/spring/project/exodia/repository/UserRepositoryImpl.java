package com.spring.project.exodia.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.spring.project.exodia.entity.User;
import com.spring.project.exodia.utils.ProjectUtils;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{
	
	  @PersistenceContext
	   EntityManager entityManager;
	  
	  

	@Override
	public User getUserByNameAndPass(String username, String password) {
		User user =null;
		try {
		TypedQuery<User> query = entityManager.createQuery(ProjectUtils.GET_USER_QUERY, User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		user= query.getSingleResult();
		}catch(NoResultException e) {
			// do not set user
		}
		return user;
		
	}

	@Override
	public User saveUser(User user) {
		return entityManager.merge(user);
	}


}
