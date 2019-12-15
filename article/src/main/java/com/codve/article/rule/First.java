package com.codve.article.rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/13 09:34
 */
@Slf4j
public class First implements IRule {

    private ILoadBalancer loadBalancer;

    @Override
    public Server choose(Object o) {
        List<Server> servers = loadBalancer.getReachableServers();
        servers.forEach(e -> System.out.println(e.getPort()));
        if (servers != null && servers.size() > 0) {
            return servers.get(0);
        }
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        loadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return loadBalancer;
    }
}
