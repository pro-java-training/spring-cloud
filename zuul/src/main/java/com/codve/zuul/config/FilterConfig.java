package com.codve.zuul.config;

import com.codve.zuul.filter.AuthFilter;
import com.netflix.zuul.ZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/12/16 11:21
 */
@Configuration
public class FilterConfig {
    @Bean
    public ZuulFilter filter() {
        return new AuthFilter();
    }
}
