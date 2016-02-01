package org.anyframe.cloud.restservice.controller.rest;

import org.anyframe.cloud.restservice.controller.dto.SampleError;
import org.anyframe.cloud.restservice.controller.exception.DuplicatLoginNameException;
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
@ControllerAdvice(basePackages = "org.anyframe.cloud.restservice.controller")
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerAdvice.class);

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
                        , "TEST00001"
                        , ex.getMessage()
                        , ex.getClass().getSimpleName())
                , status);
    }

//    @ExceptionHandler(DuplicatLoginNameException.class)
//    @ResponseBody
//    ResponseEntity<?> duplicatLoginNameException(HttpServletRequest request, DuplicatLoginNameException ex) {
//
//        logger.error("[DataIntegrityViolationACException] - {}", ex.getMessage());
//        HttpStatus status = null;
//
////        if("".equals(ex.getConstraintName())){
////            status = HttpStatus.valueOf(409);
////        }
//
//        return new ResponseEntity<>(
//                new SampleError(new Date()
//                        , status.value()
//                        , "TEST00001"
//                        , ex.getMessage()
//                        , ex.getClass().getSimpleName())
//                , status);
//    }
}
