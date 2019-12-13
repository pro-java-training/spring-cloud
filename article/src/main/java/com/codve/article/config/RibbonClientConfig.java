package com.codve.article.config;

import com.codve.article.rule.First;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/12/13 09:52
 */
@Configuration
@RibbonClient(name = "user-server", configuration = First.class)
public class RibbonClientConfig {

}
