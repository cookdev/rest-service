package org.anyframe.web.filter;

import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Hahn on 2016-02-03.
 */
public class OverrideHttpMethodRequestParamFilter extends HiddenHttpMethodFilter implements Ordered {

    public static final int DEFAULT_ORDER = -10000;
    private int order = -10000;

    public static final String DEFAULT_METHOD_PARAM = "_method";
    private String methodParam = "_method";

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String paramValue = request.getParameter(this.methodParam);
        if("POST".equals(request.getMethod()) && StringUtils.hasLength(paramValue)) {
            String method = paramValue.toUpperCase(Locale.ENGLISH);
            HttpMethodRequestWrapper wrapper = new HttpMethodRequestWrapper(request, method);
            filterChain.doFilter(wrapper, response);

        } else{
            filterChain.doFilter(request, response);
        }
    }
//access-control-request-method
    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            String header = request.getHeader("access-control-request-method");
            this.method = method;
        }

        public String getMethod() {
            return this.method;
        }
    }
}
