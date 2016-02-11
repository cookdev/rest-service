package org.anyframe.cloud.restservice.controller.handler;

import org.anyframe.cloud.restservice.controller.dto.SampleError;
import org.anyframe.cloud.restservice.controller.exception.UnavailableLoginNameException;
import org.anyframe.cloud.restservice.controller.exception.UserNotFoundException;
import org.anyframe.cloud.web.servlet.mvc.AbstractController;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Hahn on 2016-01-21.
 */
@ControllerAdvice(assignableTypes = {AbstractController.class})
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(UnavailableLoginNameException.class)
    @ResponseBody
    ResponseEntity<?> unavailableLoginName(HttpServletRequest request, Throwable ex) {

        logger.error("[UnavailableLoginNameException] - the loginName is unavailable.");

        HttpStatus status = HttpStatus.valueOf(400);

        return new ResponseEntity<>(
                new SampleError(new Date()
                        , status.value()
                        , "TEST00001"
                        , ex.getMessage()
                        , ex.getClass().getSimpleName())
                , status);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    ResponseEntity<?>  violatationConstraint(HttpServletRequest request, DataIntegrityViolationException ex) {

        logger.error("[DataIntegrityViolationACException] - {}", ex.getMessage());
        HttpStatus status = null;

        if(ex.getCause() instanceof ConstraintViolationException){
            ConstraintViolationException cvex = (ConstraintViolationException) ex.getCause();
            status = HttpStatus.valueOf(409);
        }else{
            status = HttpStatus.valueOf(409);
        }

        return new ResponseEntity<>(
                new SampleError(new Date()
                        , status.value()
                        , "TEST00002"
                        , ex.getMessage()
                        , ex.getClass().getSimpleName())
                , status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    ResponseEntity<?> userNotFound(HttpServletRequest request, Throwable ex) {

        logger.error("[UserNotFoundException] - user is not exited.");

        HttpStatus status = HttpStatus.valueOf(404);

        return new ResponseEntity<>(
                new SampleError(new Date()
                        , status.value()
                        , "TEST00003"
                        , ex.getMessage()
                        , ex.getClass().getSimpleName())
                , status);
    }
}
