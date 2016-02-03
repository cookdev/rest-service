package org.anyframe.cloud.web.filter;

import org.springframework.util.StringUtils;

import org.springframework.core.Ordered;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Hahn on 2016-02-03.
 */
public class OverrideHttpMethodHeaderFilter extends HiddenHttpMethodFilter implements Ordered {

    public static final int DEFAULT_ORDER = -10000;
    private int order = -10000;

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String methodOverrideValue = request.getHeader("x-http-method-override");
        if("POST".equals(request.getMethod()) && StringUtils.hasLength(methodOverrideValue)) {
            String method = methodOverrideValue.toUpperCase(Locale.ENGLISH);
            OverrideHttpMethodHeaderFilter.HttpMethodRequestWrapper wrapper = new OverrideHttpMethodHeaderFilter.HttpMethodRequestWrapper(request, method);
            filterChain.doFilter(wrapper, response);

        } else {
            filterChain.doFilter(request, response);
        }
    }

    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        public String getMethod() {
            return this.method;
        }
    }
}
