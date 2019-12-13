package com.codve.article.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/13 09:34
 */
public class First extends AbstractLoadBalancerRule {

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        List<Server> servers = getLoadBalancer().getReachableServers();
        if (servers != null && servers.size() > 0) {
            return servers.get(0);
        }
        return null;
    }
}
