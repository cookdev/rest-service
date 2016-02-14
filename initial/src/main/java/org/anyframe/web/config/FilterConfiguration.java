package org.anyframe.web.config;

import org.anyframe.web.filter.OverrideHttpMethodHeaderFilter;
import org.anyframe.web.filter.SimpleCORSFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Created by Hahn on 2016-01-27.
 */
public class FilterConfiguration {

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
