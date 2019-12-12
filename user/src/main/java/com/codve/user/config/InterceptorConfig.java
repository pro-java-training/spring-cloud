package com.codve.user.config;

import com.codve.user.filter.AuthInterceptor;
import com.codve.user.handler.FilterExceptionHandler;
import com.codve.user.properties.TokenProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author admin
 * @date 2019/12/11 19:46
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).order(1);
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterExceptionHandler filterExceptionHandler() {
        return new FilterExceptionHandler();
    }

    @Bean
    public TokenProperties tokenProperties() {
        return new TokenProperties();
    }

}
