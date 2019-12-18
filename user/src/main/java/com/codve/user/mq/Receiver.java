package com.codve.user.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author admin
 * @date 2019/12/17 16:52
 */
@RabbitListener(queues = "test")
@Slf4j
public class Receiver {

    @RabbitHandler
    public void handle(String msg) {
        log.warn("receive " + msg);
    }
}
