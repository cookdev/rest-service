package org.anyframe.cloud.restservice.controller.rest;

import org.anyframe.cloud.database.exception.DataIntegrityViolationACException;
import org.anyframe.cloud.restservice.controller.dto.SampleError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @ExceptionHandler(DataIntegrityViolationACException.class)
    @ResponseBody
    ResponseEntity<?> violatationConstraint(HttpServletRequest request, DataIntegrityViolationACException ex) {

        logger.error("[DataIntegrityViolationACException] - {}", ex.getMessage());
        HttpStatus status = null;

        if("".equals(ex.getConstraintName())){
            status = HttpStatus.valueOf(409);
        }

        return new ResponseEntity<>(
                new SampleError(new Date()
                    , status.value()
                    , "TEST00001"
                    , ex.getMessage()
                    , ex.getClass().getSimpleName())
                , status);
    }
}
