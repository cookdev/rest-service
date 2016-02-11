package org.anyframe.cloud.web.servlet.handler;

import org.anyframe.cloud.web.annotation.PageableRequest;
import org.anyframe.cloud.web.servlet.mvc.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * Created by Hahn on 2016-01-21.
 */
@ControllerAdvice(assignableTypes = {AbstractController.class}, basePackages = {"org.anyframe.cloud.web"})
public class PageableRequestAdvice implements ResponseBodyAdvice<Page<?>> {

    private static final Logger logger = LoggerFactory.getLogger(PageableRequestAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        for(Annotation anno: methodParameter.getMethodAnnotations()){
            if(anno.annotationType().equals(PageableRequest.class)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<?> beforeBodyWrite(Page<?> pageResponse
            , MethodParameter methodParameter
            , MediaType mediaType
            , Class<? extends HttpMessageConverter<?>> aClass
            , ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if(pageResponse.hasNext()){
            serverHttpResponse.getHeaders().set("x-next-page-offset", String.valueOf(pageResponse.nextPageable().getOffset()));
            serverHttpResponse.getHeaders().set("x-next-page-limit", String.valueOf(pageResponse.nextPageable().getPageSize()));
        }
        if(pageResponse.hasPrevious()) {
            serverHttpResponse.getHeaders().set("x-previous-page-offset", String.valueOf(pageResponse.previousPageable().getOffset()));
            serverHttpResponse.getHeaders().set("x-previous-page-limit", String.valueOf(pageResponse.previousPageable().getPageSize()));
        }
        return pageResponse;
    }
}
