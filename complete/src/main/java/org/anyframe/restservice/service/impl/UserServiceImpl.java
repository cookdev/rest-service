package org.anyframe.restservice.service.impl;

import org.anyframe.restservice.controller.exception.UnavailableLoginNameException;
import org.anyframe.restservice.controller.exception.UserNotFoundException;
import org.anyframe.restservice.domain.User;
import org.anyframe.restservice.repository.jpa.RegisteredUserJpaRepository;
import org.anyframe.restservice.service.UserService;
import org.anyframe.restservice.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

        if("admin".equals(newUser.getLoginName())){
            throw new UnavailableLoginNameException("Unabailable loginName value");
        }

        newUser.setId(IdGenerator.generateId());

        User registeredUser = registeredUserRepository.save(newUser);

        logger.info("$$$ registerUser - registered user : ".concat(registeredUser.toString()));

        return registeredUser;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> registeredUsers = registeredUserRepository.findAll();

        if(registeredUsers.size() == 0){
            throw new UserNotFoundException("Any User are not found");
        }

        return registeredUsers;
    }

    @Override
    public Page<User> getUsers(Pageable pageRequest) {
        Page<User> registeredUsers = registeredUserRepository.findAll(pageRequest);

        if(registeredUsers.getSize() == 0){
            throw new UserNotFoundException("Any User are not found");
        }

        return registeredUsers;
    }

    @Override
    public User getUserById(String userId) {

        logger.info("$$$ getUserById - userId : ".concat(userId));

        User registeredUser = registeredUserRepository.findOne(userId);

        if(registeredUser == null){
            throw new UserNotFoundException("User[".concat(userId).concat("] is not existed"));
        }

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

        logger.info("$$$ modifyUser - modified user completed: ".concat(modifyUser.getId()));

        return modifiedUser;
    }

    @Override
    public void deleteUser(User deleteUser) {

        logger.info("$$$ deleteUser - userId : ".concat(deleteUser.getId()));

        registeredUserRepository.delete(deleteUser.getId());

        logger.info("$$$ deleteUser - deleted userId : ".concat(deleteUser.getId()));
    }
}
