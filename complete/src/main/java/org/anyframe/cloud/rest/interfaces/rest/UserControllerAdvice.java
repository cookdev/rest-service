package org.anyframe.cloud.rest.interfaces.rest;

import org.anyframe.cloud.rest.application.exception.DuplicateLoginNameException;
import org.anyframe.cloud.rest.interfaces.dto.SampleError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Hahn on 2016-01-21.
 */
@ControllerAdvice(basePackageClasses  = UserController.class)
public class UserControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerAdvice.class);


    @ExceptionHandler(DuplicateLoginNameException.class)
//    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicate loginName value")
    @ResponseBody
    public ResponseEntity<?> duplicateLoginName(HttpServletRequest request, Throwable ex) {

        logger.error("[DuplicateLoginNameException] - LoginName is already in use.");

        HttpStatus status = HttpStatus.valueOf(409);

        return new ResponseEntity<>(
                new SampleError(new Date()
                    , status.value()
                    , "TEST00001"
                    , ex.getMessage()
                    , ex.getClass().getSimpleName())
                , HttpStatus.valueOf(409));
    }
}
