package org.anyframe.cloud.restservice.controller;

import org.anyframe.cloud.restservice.service.UserService;
import org.anyframe.cloud.restservice.domain.User;
import org.anyframe.cloud.restservice.controller.dto.RegisteredUser;
import org.anyframe.cloud.restservice.controller.dto.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/user", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> getUserList() {

        List<User> userList = userService.getUserList();

        List<Map<String, Object>> registeredUsers = listDomainToDto(userList);

        return registeredUsers;

    }

    @RequestMapping(value = "/user/login-name", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public RegisteredUser getUserByLoginName(@RequestParam(name = "loginName") String loginName) {

        User user = userService.getUserByLoginName(loginName);

        RegisteredUser registeredUser = domainToDto(user);

        return registeredUser;

    }

    @RequestMapping(value = "/user/{userId}", method = {RequestMethod.PUT})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyUser(@RequestBody RegisteredUser registerUser, @PathVariable(value = "userId") String userId) {

        User modifyUser = dtoToDomain(registerUser);
        modifyUser.setId(userId);

        userService.modifyUser(modifyUser);

    }

    @RequestMapping(value = "/user/{userId}", method = {RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteUser(@PathVariable(value = "userId") String userId) {

        User deleteUser = new User();
        deleteUser.setId(userId);

        userService.deleteUser(deleteUser);
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

    private List<Map<String, Object>> listDomainToDto(List<User> userList){
        List<Map<String, Object>> registeredUsers = new ArrayList();
        for(User user : userList){
            Map<String, Object> registeredUser = new HashMap();
            registeredUser.put("loginName", user.getLoginName());
            registeredUser.put("userId", user.getId());
            registeredUser.put("emailAddress", user.getEmailAddress());
            registeredUser.put("firstName", user.getFirstName());
            registeredUser.put("lastName", user.getLastName());
            registeredUsers.add(registeredUser);
        }
        return registeredUsers;
    }

}
