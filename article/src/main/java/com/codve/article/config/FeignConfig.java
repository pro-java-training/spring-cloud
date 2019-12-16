package com.codve.article.config;

import com.codve.article.interceptor.FeignInterceptor;
import feign.Logger.Level;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/12/16 09:45
 */
@Configuration
public class FeignConfig {

    @Bean
    Level level() {
        return Level.FULL;
    }

    @Bean
    FeignInterceptor interceptor() {
        return new FeignInterceptor();
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }
}
