package com.codve.user.service;

import com.codve.user.model.data.object.LogDO;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/17 15:47
 */
public interface LogService {

    void save(LogDO logDO);

    void delete(LogDO logDO);

    void update(LogDO logDO);

    List<LogDO> find(LogDO logDO, long pageNum, long pageSize);

    Long count(LogDO logDO);

}
