package org.example.config;

import org.example.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @Description:org.example.config
 * @Date:2023/11/11
 * @Author:谢锦创
 */
@Configuration
public class FilterConfiguration{

//    @Bean
//    public FilterRegistrationBean<Filter> registrationBean() {
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter(new RequestFilter());
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        return filterFilterRegistrationBean;
//    }
}
