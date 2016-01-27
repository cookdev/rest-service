package org.anyframe.cloud.rest.controller.rest;

import org.anyframe.cloud.rest.service.UserService;
import org.anyframe.cloud.rest.domain.User;
import org.anyframe.cloud.rest.controller.dto.RegisteredUser;
import org.anyframe.cloud.rest.controller.dto.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hahn on 2016-01-18.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public UserAccount registerUser(@RequestBody RegisteredUser registerUser) {

        User newUser = dtoToDomain(registerUser);

        User user = userService.registerUser(newUser);

        UserAccount userAccount = new UserAccount(user.getId()
                , user.getLoginName()
                , user.getEmailAddress());

        return userAccount;
    }

    @RequestMapping(value = "/user/{userId}", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public RegisteredUser getUserById(@PathVariable(value = "userId") String userId) {

        User user = userService.getUserById(userId);

        RegisteredUser registeredUser = domainToDto(user);

        return registeredUser;

    }

    @RequestMapping(value = "/user/login-name", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public RegisteredUser getUserByLoginName(@RequestParam(name = "loginName") String loginName) {

        User user = userService.getUserByLoginName(loginName);

        RegisteredUser registeredUser = domainToDto(user);

        return registeredUser;

    }

    private RegisteredUser domainToDto(User domain){
        RegisteredUser dto = new RegisteredUser(domain.getLoginName()
                , domain.getEmailAddress()
                , domain.getFirstName()
                , domain.getLastName());
        return dto;
    }

    private User dtoToDomain(RegisteredUser dto){
        User newUser = new User(null
                , dto.getLoginName()
                , dto.getEmailAddress()
                , dto.getFirstName()
                , dto.getLastName());
        return newUser;
    }

}