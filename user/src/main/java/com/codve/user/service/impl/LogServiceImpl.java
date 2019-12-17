package com.codve.user.service.impl;

import com.codve.user.model.data.object.LogDO;
import com.codve.user.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/17 15:30
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private MongoTemplate template;

    @Override
    public void save(LogDO logDO) {
        template.save(logDO);
    }

    @Override
    public void delete(LogDO logDO) {
        template.remove(logDO);
    }

    @Override
    public void update(LogDO logDO) {
        throw new NotImplementedException();
    }

    @Override
    public List<LogDO> find(LogDO logDO, long pageNum, long pageSize) {
        throw new NotImplementedException();
    }

    @Override
    public Long count(LogDO logDO) {
        throw new NotImplementedException();
    }
}
