package org.anyframe.cloud.rest.repository;


import org.anyframe.cloud.rest.domain.User;

public interface RegisteredUserRepository {
	
	User findByLoginName(String loginName);

	User findByEmailAddress(String emailAddress);
	
}
