package org.anyframe.cloud.restservice.service;

import org.anyframe.cloud.restservice.domain.User;

import java.util.List;

/**
 * Created by Hahn on 2016-01-18.
 */
public interface UserService {

    User registerUser(User newUser);

    List<User> getUserList();

    User getUserById(String userId);

    User getUserByLoginName(String loginName);

    User modifyUser(User modifyUser);

    void deleteUser(User deleteUser);
}
