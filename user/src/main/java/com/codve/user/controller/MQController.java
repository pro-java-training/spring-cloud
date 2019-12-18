package com.codve.user.controller;

import com.codve.user.model.data.object.UserDO;
import com.codve.user.mq.Receiver;
import com.codve.user.mq.Sender;
import com.codve.user.util.CommonResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author admin
 * @date 2019/12/18 09:17
 */
@RestController
public class MQController {

    @Autowired
    private Sender sender;

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @GetMapping("/send")
    public CommonResult<String> send() throws JsonProcessingException {
        UserDO userDO = new UserDO();
        userDO.setId(new Random().nextLong());
        String msg = objectMapper.writeValueAsString(userDO);
        sender.send(msg);
        return CommonResult.success(msg);
    }

    @GetMapping("/send/msg")
    public CommonResult<String> sendMsg(@RequestParam("msg") String msg){
        sender.send(msg);
        return CommonResult.success(msg);
    }
}
