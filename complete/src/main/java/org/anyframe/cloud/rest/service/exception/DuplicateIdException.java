package org.anyframe.cloud.rest.service.exception;

import org.springframework.dao.DuplicateKeyException;

/**
 * Created by Hahn on 2016-01-21.
 */
public class DuplicateIdException extends DuplicateKeyException {
    public DuplicateIdException(String msg) {
        super(msg);
    }

    public DuplicateIdException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
