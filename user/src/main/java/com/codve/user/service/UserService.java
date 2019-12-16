package com.codve.user.service;

import com.codve.user.model.data.object.UserDO;
import com.codve.user.model.query.UserQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/19 14:10
 */
@Service
public interface UserService {

    /**
     * 保存
     * @param userDO userDO
     * @return int
     */
    @ApiOperation("添加用户")
    int save(UserDO userDO);

    /**
     * 根据 id 删除记录
     * @param id id
     * @return int
     */
    int deleteById(Long id);

    /**
     * 更新记录
     * @param userDO userDO
     * @return int
     */
    int update(UserDO userDO);

    /**
     * 根据 id 查找 userDO
     * @param id id
     * @return userDO
     */
    UserDO findById(Long id);

    /**
     * 根据用户名查找用户
     * @param name name
     * @return UserDO
     */
    UserDO findByName(String name);

    /**
     * 根据条件查找记录
     * @param userQuery 查询条件
     * @return List<UserDO>
     */
    List<UserDO> find(UserQuery userQuery);

    /**
     * 根据条件统计条数
     * @param userQuery 查询条件
     * @return int
     */
    int count(UserQuery userQuery);
}
