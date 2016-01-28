package org.anyframe.cloud.rest.service.implement;

import org.anyframe.cloud.rest.controller.dto.RegisteredUser;
import org.anyframe.cloud.rest.domain.User;
import org.anyframe.cloud.rest.repository.jpa.RegisteredUserJpaRepository;
import org.anyframe.cloud.rest.service.UserService;
import org.anyframe.cloud.rest.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Hahn on 2016-01-18.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    protected RegisteredUserJpaRepository registeredUserRepository;

    @Override
    public User registerUser(User newUser) {

        logger.info("$$$ registerUser - new user : ".concat(newUser.toString()));

        newUser.setId(IdGenerator.generateId());

        User registeredUser = registeredUserRepository.save(newUser);

        logger.info("$$$ registerUser - registered user : ".concat(registeredUser.toString()));

        return registeredUser;
    }

    @Override
    public User getUserById(String userId) {

        logger.info("$$$ getUserById - userId : ".concat(userId));

        User registeredUser = registeredUserRepository.findOne(userId);

        logger.info("$$$ getUserById - user : ".concat(registeredUser.toString()));

        return registeredUser;
    }

    @Override
    public User getUserByLoginName(String loginName) {

        logger.info("$$$ getUserById - loginName : ".concat(loginName));

        User registeredUser = registeredUserRepository.findByLoginName(loginName);

        logger.info("$$$ getUserById - user : ".concat(registeredUser.toString()));

        return registeredUser;
    }

    @Override
    public User modifyUser(User modifyUser) {

        logger.info("$$$ modifyUser - userId : ".concat(modifyUser.getId()));

        User modifiedUser = registeredUserRepository.save(modifyUser);

        logger.info("$$$ modifyUser - modified user : ".concat(modifiedUser.toString()));

        return modifiedUser;
    }

    @Override
    public void deleteUser(User deleteUser) {

        logger.info("$$$ deleteUser - userId : ".concat(deleteUser.getId()));

        registeredUserRepository.delete(deleteUser.getId());

        logger.info("$$$ deleteUser - deleted userId : ".concat(deleteUser.getId()));
    }
}
