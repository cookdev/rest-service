package org.anyframe.cloud.restservice.controller.exception;

/**
 * Created by Hahn on 2016-02-03.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
