package org.anyframe.cloud.rest.service.exception;

import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;

/**
 * Created by Hahn on 2016-01-21.
 */
public class DuplicateLoginNameException extends DataIntegrityViolationException {

    private String constraintName;
    private String sql;

    public DuplicateLoginNameException(String message, SQLException root, String constraintName) {
        super(message);
    }

    public DuplicateLoginNameException(String message, SQLException root, String sql, String constraintName) {
        super(message, root);
        this.sql = sql;
        this.constraintName = constraintName;
    }

    public String getConstraintName() {
        return this.constraintName;
    }

    public DuplicateLoginNameException(String msg) {
        super(msg);
    }

    public DuplicateLoginNameException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
