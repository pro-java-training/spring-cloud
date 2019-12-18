package com.codve.user.mq;

import com.codve.user.model.data.object.UserDO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author admin
 * @date 2019/12/17 17:04
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class SenderTest {

    @Autowired
    private Sender sender;

//    @Autowired
//    private Receiver receiver;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void test() throws JsonProcessingException {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setPassword("password");
        userDO.setName("Alice");
        sender.send(mapper.writeValueAsString(userDO));
    }
}