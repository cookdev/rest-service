package org.anyframe.cloud.rest.interfaces.rest;

import org.anyframe.cloud.rest.application.UserService;
import org.anyframe.cloud.rest.domain.User;
import org.anyframe.cloud.rest.interfaces.dto.RegisteredUser;
import org.anyframe.cloud.rest.interfaces.dto.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hahn on 2016-01-18.
 */
@RestController(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public UserAccount registerUser(@RequestBody RegisteredUser registerUser) {

        User newUser = new User(null
                , registerUser.getLoginName()
                , registerUser.getEmailAddress()
                , registerUser.getFirstName()
                , registerUser.getLastName());

        User user = userService.registerUser(newUser);

        UserAccount userAccount = new UserAccount(user.getId()
                , user.getLoginName()
                , user.getEmailAddress());

        return userAccount;
    }

    @RequestMapping(value={"/{userId}"}, method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public RegisteredUser registerUser(@PathVariable(value = "userId") String userId) {

        User user = userService.getUserById(userId);


        RegisteredUser registeredUser = new RegisteredUser(user.getLoginName()
                , user.getEmailAddress()
                , user.getFirstName()
                , user.getLastName());

        return registeredUser;

    }

}
