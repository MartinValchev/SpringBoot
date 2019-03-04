package com.spring.project.exodia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.spring.project.exodia.utils.UuIdUserGenerator;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="user_seq")
	@GenericGenerator(name="user_seq",strategy="com.spring.project.exodia.utils.UuIdUserGenerator",
	parameters= {@Parameter(name=UuIdUserGenerator.INCREMENT_PARAM, value="10"),
			@Parameter(name=UuIdUserGenerator.USER_VALUE_PREFIX_PARAMETER, value="USR_"),
			@Parameter(name=UuIdUserGenerator.USER_NUMBER_FORMAT_PARAMETER, value="%05d")})
	@Column(name="uuid", updatable=false, nullable=false)
	private String uuid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
