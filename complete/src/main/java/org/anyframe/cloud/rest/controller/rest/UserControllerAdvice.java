package org.anyframe.cloud.rest.controller.rest;

import org.anyframe.cloud.rest.service.exception.DuplicateLoginNameException;
import org.anyframe.cloud.rest.controller.dto.SampleError;
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
@ControllerAdvice(basePackageClasses  = UserController.class)
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerAdvice.class);


    @ExceptionHandler(DuplicateLoginNameException.class)
    @ResponseBody
    ResponseEntity<?> duplicateId(HttpServletRequest request, Throwable ex) {

        logger.error("[DuplicateIdException] - LoginName is already in use.");

        HttpStatus status = HttpStatus.valueOf(409);

        return new ResponseEntity<>(
                new SampleError(new Date()
                    , status.value()
                    , "TEST00001"
                    , ex.getMessage()
                    , ex.getClass().getSimpleName())
                , HttpStatus.valueOf(409));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    ResponseEntity<?> violatationConstraint(HttpServletRequest request, DataIntegrityViolationException ex) {

//        logger.error("[ConstraintViolationException] - {}", ex.getConstraintName());
        HttpStatus status = null;
//
//        if("".equals(ex.getConstraintName())){
//            status = HttpStatus.valueOf(409);
//        }

        return new ResponseEntity<>(
                new SampleError(new Date()
                    , status.value()
                    , "TEST00001"
                    , ex.getMessage()
                    , ex.getClass().getSimpleName())
                , status);
    }
}
