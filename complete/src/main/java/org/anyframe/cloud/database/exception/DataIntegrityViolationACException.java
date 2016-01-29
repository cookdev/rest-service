package org.anyframe.cloud.database.exception;

/**
 * Created by Hahn on 2016-01-21.
 */
public class DataIntegrityViolationACException extends org.springframework.dao.DataIntegrityViolationException{

    private String constraintName;

    public DataIntegrityViolationACException(String msg) {
        super(msg);
    }

    public DataIntegrityViolationACException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataIntegrityViolationACException(String msg, Throwable cause, String constraintName) {
        super(msg, cause);
        this.constraintName = constraintName;
    }

    public String getConstraintName(){
        return this.constraintName;
    }
}
