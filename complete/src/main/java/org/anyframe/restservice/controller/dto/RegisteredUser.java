package org.anyframe.restservice.controller.dto;

/**
 * Created by Hahn on 2016-01-18.
 */
public class RegisteredUser {

    private String loginName;

    private String emailAddress;

    private String firstName;

    private String lastName;

    public RegisteredUser() {
    }

    public RegisteredUser(String loginName, String emailAddress, String firstName, String lastName) {
        this.loginName = loginName;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "loginName='" + loginName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
