package org.anyframe.cloud.restservice.repository;

import org.anyframe.cloud.restservice.domain.User;

public interface RegisteredUserRepository {

	User findByLoginName(String loginName);

	User findByEmailAddress(String emailAddress);

}
