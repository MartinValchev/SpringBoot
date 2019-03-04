package com.spring.project.exodia.utils;

public class ProjectUtils {
	public static final String GET_DOCUMENTS_BY_ID_QUERY = "SELECT e from Document e WHERE e.id=: documentId";
	public static final String GET_ALL_DOCUMENTS_QUERY = "SELECT e from Document e";
	public static final String GET_USER_QUERY ="select e from User e where e.username =:username AND e.password =: password";
}
