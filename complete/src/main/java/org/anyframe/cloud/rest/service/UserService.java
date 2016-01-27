package org.anyframe.cloud.rest.service;

import org.anyframe.cloud.rest.domain.User;

/**
 * Created by Hahn on 2016-01-18.
 */
public interface UserService {

    User registerUser(User newUser);

    User getUserById(String userId);

    User getUserByLoginName(String loginName);
}
