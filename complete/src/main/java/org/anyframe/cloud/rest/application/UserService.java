package org.anyframe.cloud.rest.application;

import org.anyframe.cloud.rest.domain.User;

/**
 * Created by Hahn on 2016-01-18.
 */
public interface UserService {

    User registerUser(User newUser);

    User getUserById(String userId);
}
