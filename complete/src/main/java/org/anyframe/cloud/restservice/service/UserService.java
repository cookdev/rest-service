package org.anyframe.cloud.restservice.service;

import org.anyframe.cloud.restservice.domain.User;
import org.anyframe.cloud.data.domain.AnyframePageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Hahn on 2016-01-18.
 */
public interface UserService {

    User registerUser(User newUser);

    List<User> getAllUsers();

    Page<User> getUsers(Pageable pageRequest);

    User getUserById(String userId);

    User getUserByLoginName(String loginName);

    User modifyUser(User modifyUser);

    void deleteUser(User deleteUser);
}
