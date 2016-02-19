package org.anyframe.web.servlet.handler;

import org.anyframe.restservice.util.Encryption;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Hahn on 2016-02-11.
 * 파라미터 위변조 검증을 위한 HSSP용 Interceptor입니다.
 */
public class ParamForgeryVerificationInterceptor extends WebContentInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {

        String clientHash = request.getHeader("x-hash");
        String createdAt = request.getHeader("x-created-at");
//        Map<String, String[]> param = request.getParameterMap();
        String param = request.getQueryString();
        String body = null;
        try {
            body = this.getBody(request);
        }catch(IOException ioe){
            throw new CanNotReadRequestBodyException(ioe.getMessage());
        }

        String verifyString = createdAt;
        if(param != null){
            verifyString = verifyString.concat(param);
        }
        if(body != null){
            verifyString = verifyString.concat(body);
        }

        String serverHash = Encryption.encryptSHA512(verifyString);

        if(clientHash.equals(serverHash)){
            return true;
        }else{
            throw new RequestForgeryException("HTTP Request was forgery");
        }
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//    }

    private String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

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
