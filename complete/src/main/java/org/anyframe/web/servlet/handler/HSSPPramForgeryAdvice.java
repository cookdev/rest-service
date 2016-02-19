package org.anyframe.web.servlet.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.anyframe.restservice.util.Encryption;
import org.anyframe.web.annotation.PageableRequest;
import org.anyframe.web.servlet.mvc.AbstractController;
import org.anyframe.web.servlet.mvc.PageableController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by Hahn on 2016-01-21.
 */
@ControllerAdvice(assignableTypes = AbstractController.class)
public class HSSPPramForgeryAdvice implements RequestBodyAdvice {

    private static final Logger logger = LoggerFactory.getLogger(HSSPPramForgeryAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        String clientHash = httpInputMessage.getHeaders().get("x-hash").get(0);
        String createdAt = httpInputMessage.getHeaders().get("x-created-at").get(0);
        String body = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            body = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//
        String verifyString = createdAt;
//        if(param != null){
//            verifyString = verifyString.concat(param);
//        }
        if(body != null){
            verifyString = verifyString.concat(body);
        }

        String serverHash = Encryption.encryptSHA512(verifyString);

        if(!clientHash.equals(serverHash)){
            throw new RequestForgeryException("HTTP Request was forgery");
        }

        return o;
    }
    //f9f811cf4cff6657d96dff27bc94d13046ae0865557e4ec3f07d1bb15cc6b658fe6c07241b420e2165d64cbc29f92329dfce04150ca93cde92b0fd50c3c9f87f
    //aa78409d49f7346d2b14d5867bcfe55e05d4e172dcb5dc6ffec59a19d943113f7ce4f1a5990b840bbb3d87b57e973aeb29468aac87ff63aea770ee72ac4d901c

    private String getBody(InputStream inputStream) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        }
//        finally {
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException ex) {
//                    throw ex;
//                }
//            }
//        }

        body = stringBuilder.toString();
        return body;
    }

    private class CanNotReadRequestBodyException extends RuntimeException {
        public CanNotReadRequestBodyException(String message) {
            super(message);
        }
    }

    private class RequestForgeryException extends RuntimeException {
        public RequestForgeryException(String message) {
            super(message);
        }
    }
}
