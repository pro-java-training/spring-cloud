package com.codve.user.config;

import com.codve.user.mq.Receiver;
import com.codve.user.mq.SendMessageService;
import com.codve.user.mq.Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @date 2019/12/17 16:57
 */
@Configuration
public class MQConfig {

    @Bean
    public Queue testQueue() {
        return new Queue("test");
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }

    @Bean
    public SendMessageService messageService() {
        return new SendMessageService();
    }
}
