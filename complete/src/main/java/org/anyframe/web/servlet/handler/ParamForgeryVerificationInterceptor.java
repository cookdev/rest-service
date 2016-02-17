package org.anyframe.web.servlet.handler;

import org.anyframe.web.annotation.PageableRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Hahn on 2016-02-11.
 * interceptor는 Response 객체를 참조하지 못하므로, @ControllerAdvice로 대체함.
 * 즉, 미사용 Interceptor이며, Interceptor 개발시 Java Configuration 참조를 위해 빈 내용으로 놔둡니다.
 */
public class ParamForgeryVerificationInterceptor extends WebContentInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        //@PageableRequest 어노테이션이 컨트롤러에 사용되었는지 체크함
        PageableRequest pageableRequest = ((HandlerMethod) handler).getMethodAnnotation(PageableRequest.class);

        if(pageableRequest != null) {
            //TODO 추가작업

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        PageableRequest pageableRequest = ((HandlerMethod) handler).getMethodAnnotation(PageableRequest.class);

        if(pageableRequest != null) {
            //TODO 추가작업

        }
    }
}
