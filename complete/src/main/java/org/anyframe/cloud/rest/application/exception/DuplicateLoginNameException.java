package org.anyframe.cloud.rest.application.exception;

/**
 * Created by Hahn on 2016-01-21.
 */
public class DuplicateLoginNameException extends RuntimeException {
    public DuplicateLoginNameException(String msg) {
        super(msg);
    }

    public DuplicateLoginNameException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
