package org.anyframe.restservice.repository;

import org.anyframe.restservice.domain.User;

public interface RegisteredUserRepository {

	User findByLoginName(String loginName);

	User findByEmailAddress(String emailAddress);

}
