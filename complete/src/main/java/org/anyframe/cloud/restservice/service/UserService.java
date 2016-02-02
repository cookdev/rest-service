package org.anyframe.cloud.restservice.service;

import org.anyframe.cloud.restservice.domain.User;

/**
 * Created by Hahn on 2016-01-18.
 */
public interface UserService {

    User registerUser(User newUser);

    User getUserById(String userId);

    User getUserByLoginName(String loginName);

    void modifyUser(User modifyUser);

    void deleteUser(User deleteUser);
}
