package com.codve.article.config;

import com.codve.article.rule.First;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/12/13 09:24
 */
@Configuration
public class RibbonConfig {
    @Bean
    public IRule rule() {
        return new First();
    }
}
