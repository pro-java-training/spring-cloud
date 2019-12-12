package com.codve.user.config;

import com.codve.user.aspect.UserServiceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author admin
 * @date 2019/11/21 10:12
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    public UserServiceAspect userServiceAspect() {
        return new UserServiceAspect();
    }
}
