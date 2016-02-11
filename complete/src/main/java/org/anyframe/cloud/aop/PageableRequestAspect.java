package org.anyframe.cloud.aop;

import org.anyframe.cloud.restservice.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Created by Hahn on 2016-02-11.
 * Annotation 기반 AOP 예제.
 */
//@Aspect
//@Component
public class PageableRequestAspect {
//    @Around(value = "@annotation(org.anyframe.cloud.web.annotation.PageableRequest)", argNames = "pjp")
    public Page<User> aroundAdvice(final ProceedingJoinPoint pjp) throws Throwable {
        // this is your response body
        Page<User> responseBody = (Page<User>) pjp.proceed();
        Pageable next = responseBody.nextPageable();
        Pageable previous = responseBody.previousPageable();

        return responseBody;
    }
}
