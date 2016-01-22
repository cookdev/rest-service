package org.anyframe.cloud.rest.application.internal;

import org.anyframe.cloud.rest.application.UserService;
import org.anyframe.cloud.rest.application.exception.DuplicateLoginNameException;
import org.anyframe.cloud.rest.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Hahn on 2016-01-18.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User registerUser(User newUser) {
        logger.info("$$$ registerUser - new user : ".concat(newUser.toString()));

        if("admin".equals(newUser.getLoginName())){
            throw new DuplicateLoginNameException("dup");
        }

        newUser.setId("GeneratedID");

        User registeredUser = newUser;
        logger.info("$$$ registerUser - registered user : ".concat(registeredUser.toString()));

        return registeredUser;
    }

    @Override
    public User getUserById(String userId) {
        logger.info("$$$ getUserById - userId : ".concat(userId));

        User user = new User(userId
                , "anyframecloud"
                , "anyframecloud@api.com"
                ,"cloud"
                ,"anyframe");

        logger.info("$$$ getUserById - user : ".concat(user.toString()));

        return user;
    }
}
