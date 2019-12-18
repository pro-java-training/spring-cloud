package com.codve.user.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author admin
 * @date 2019/12/17 16:50
 */
public class Sender {

    @Autowired
    private AmqpTemplate template;

    @Autowired
    Queue queue;

    public void send(String msg) {
        template.convertAndSend(queue.getName(), msg);
    }
}
