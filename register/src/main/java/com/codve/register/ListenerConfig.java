package com.codve.register;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/12/15 21:33
 */
@Configuration
public class ListenerConfig {

    @Bean
    public StateChangeListener listener() {
        return new StateChangeListener();
    }
}
