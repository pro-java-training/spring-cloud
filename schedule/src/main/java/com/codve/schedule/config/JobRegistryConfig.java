package com.codve.schedule.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/12/18 18:09
 */
@Configuration
public class JobRegistryConfig {

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter registryCenter() {
        ZookeeperConfiguration config = new ZookeeperConfiguration("127.0.0.1:2181", "schedule-work");
        return new ZookeeperRegistryCenter(config);
    }
}
