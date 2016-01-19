package org.anyframe.cloud.rest.interfaces.dto;

/**
 * Created by Hahn on 2016-01-18.
 */
public class UserAccount {

    private String userId;

    private String loginName;

    private String emailAddress;

    public UserAccount() {
    }

    public UserAccount(String userId, String loginName, String emailAddress) {
        this.userId = userId;
        this.loginName = loginName;
        this.emailAddress = emailAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId='" + userId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
