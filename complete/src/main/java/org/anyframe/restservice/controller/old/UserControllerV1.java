package org.anyframe.restservice.controller.old;

import org.anyframe.restservice.controller.dto.RegisteredUser;
import org.anyframe.restservice.controller.dto.UserAccount;
import org.anyframe.restservice.domain.User;
import org.anyframe.restservice.service.UserService;
import org.anyframe.web.annotation.PageableRequest;
import org.anyframe.web.servlet.mvc.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hahn on 2016-01-18.
 */
@RestController
@RequestMapping(path = "/v1")
public class UserControllerV1 extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerV1.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public UserAccount registerUser(@RequestBody RegisteredUser registerUser) {

        User newUser = dtoToDomain(registerUser);

        User user = userService.registerUser(newUser);

        UserAccount userAccount = toUserAccountDto(user);

        return userAccount;
    }

    private UserAccount toUserAccountDto(User user) {
        return new UserAccount(user.getId()
                    , user.getLoginName()
                    , user.getEmailAddress());
    }

    @RequestMapping(value = "/users/accounts", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public List<UserAccount> getAllUsers() {

        List<User> users = userService.getAllUsers();

        List<UserAccount> userAccount = new ArrayList<UserAccount>();
        for(User user: users){
            userAccount.add(toUserAccountDto(user));
        }

        return userAccount;

    }

    @PageableRequest
    @RequestMapping(value = "/users", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public Page<User> getUsers(
            @RequestParam(name = "offset", defaultValue = "1", required = false) int offset,
            @RequestParam(name = "limit", defaultValue ="10", required = false) int limit) {

        Page<User> users = userService.getUsers(new PageRequest(offset/limit, limit));

        return users;

    }

    @RequestMapping(value = "/users/{userId}", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public RegisteredUser getUserById(@PathVariable(value = "userId") String userId) {

        User user = userService.getUserById(userId);

        RegisteredUser registeredUser = domainToDto(user);

        return registeredUser;

    }

    @RequestMapping(value = "/users/login-name", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    public RegisteredUser getUserByLoginName(@RequestParam(name = "loginName") String loginName) {

        User user = userService.getUserByLoginName(loginName);

        RegisteredUser registeredUser = domainToDto(user);

        return registeredUser;

    }

    @RequestMapping(value = "/users/{userId}", method = {RequestMethod.PUT})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RegisteredUser modifyUser(@RequestBody RegisteredUser registerUser, @PathVariable(value = "userId") String userId) {

        User modifyUser = dtoToDomain(registerUser);
        modifyUser.setId(userId);

        User user = userService.modifyUser(modifyUser);

        RegisteredUser registeredUser = domainToDto(user);

        return registeredUser;
    }

    @RequestMapping(value = "/users/{userId}", method = {RequestMethod.DELETE})
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

}
