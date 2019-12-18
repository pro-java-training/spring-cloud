package com.codve.user.service.impl;

import com.codve.user.model.data.object.LogDO;
import com.codve.user.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author admin
 * @date 2019/12/17 15:52
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class LogServiceImplTest {

    @Autowired
    private LogService logService;

    @Test
    void testSave() {
        LogDO logDO = new LogDO();
        logDO.setUserId(10L);
        logService.save(logDO);
        log.error(logDO.toString());
    }

    @Test
    void testDelete() {
        LogDO logDO = new LogDO();
        logDO.setUserId(10L);
        logService.save(logDO);

        log.warn(logDO.toString());

        logService.delete(logDO);
    }




}