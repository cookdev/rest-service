package org.anyframe.cloud.restservice.controller.exception;

/**
 * Created by Hahn on 2016-01-29.
 */
public class UnavailableLoginNameException extends RuntimeException {
    public UnavailableLoginNameException(String msg) {
        super(msg);
    }
}
