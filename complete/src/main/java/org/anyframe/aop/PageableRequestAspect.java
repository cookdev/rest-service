package org.anyframe.aop;

import org.anyframe.restservice.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Hahn on 2016-02-11.
 * Annotation 기반 AOP 예제.
 */
//@Aspect
//@Component
public class PageableRequestAspect {
//    @Around(value = "@annotation(PageableRequest)", argNames = "pjp")
    public Page<User> aroundAdvice(final ProceedingJoinPoint pjp) throws Throwable {
        // this is your response body
        Page<User> responseBody = (Page<User>) pjp.proceed();
        Pageable next = responseBody.nextPageable();
        Pageable previous = responseBody.previousPageable();

        return responseBody;
    }
}
