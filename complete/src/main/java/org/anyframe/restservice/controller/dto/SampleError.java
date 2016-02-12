package org.anyframe.restservice.controller.dto;

import java.util.Date;

/**
 * Created by Hahn on 2016-01-21.
 */
public class SampleError {

    private Date timestamp;

    private int status;

    private String errorCode;

    private String error;

    private String exception;

    public SampleError() {
    }

    public SampleError(Date timestamp, int status, String errorCode, String error, String exception) {
        this.timestamp = timestamp;
        this.status = status;
        this.errorCode = errorCode;
        this.error = error;
        this.exception = exception;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
