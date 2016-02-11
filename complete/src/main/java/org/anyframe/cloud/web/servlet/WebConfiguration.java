package org.anyframe.cloud.web.servlet;

import org.anyframe.cloud.web.filter.OverrideHttpMethodHeaderFilter;
import org.anyframe.cloud.web.filter.SimpleCORSFilter;
import org.h2.server.web.WebServlet;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Created by Hahn on 2016-01-27.
 */
@Configuration
@EnableAutoConfiguration
public class WebConfiguration {
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    @Bean
    public Filter simpleCORSFilter() {
        return new SimpleCORSFilter();
    }

    @Bean
    public OverrideHttpMethodHeaderFilter hiddenHttpMethodFilter() {
        return new OverrideHttpMethodHeaderFilter();
    }

//    @Bean
//    public OverrideHttpMethodRequestParamFilter HiddenHttpMethodFilter() {
//        return new OverrideHttpMethodRequestParamFilter();
//    }

}
