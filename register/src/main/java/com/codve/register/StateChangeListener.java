package com.codve.register;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;

/**
 * @author admin
 * @date 2019/12/15 21:25
 */
@Slf4j
public class StateChangeListener {

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        log.warn("注册中心启动了.");
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        log.warn("注册中心可用了.");
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo info = event.getInstanceInfo();
        log.warn(info.getAppName() + " 完成注册.");
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        log.warn(event.getServerId() + " " + event.getAppName() + " 续约了.");
    }

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        log.warn(event.getServerId() + " " + event.getAppName() + " 下线了.");
    }
}
