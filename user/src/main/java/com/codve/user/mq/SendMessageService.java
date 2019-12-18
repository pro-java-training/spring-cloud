package com.codve.user.mq;

import com.codve.user.model.data.object.UserDO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author admin
 * @date 2019/12/17 18:20
 */
@Slf4j
@Component
public class SendMessageService {

    private ObjectMapper mapper = new ObjectMapper();

    {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Autowired
    private Sender sender;

    @Scheduled(cron = "0/1 * * * * *")
    @Async
    public void send() throws JsonProcessingException {
        Random random = new Random();
        UserDO userDO = new UserDO();
        userDO.setName("test");
        userDO.setId(random.nextLong());
        String msg = mapper.writeValueAsString(userDO);
        log.warn("sending msg: " + msg);
        sender.send(msg);
        log.warn(LocalDateTime.now().toString());
    }
}
