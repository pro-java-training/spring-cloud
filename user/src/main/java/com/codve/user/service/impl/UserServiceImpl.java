package com.codve.user.service.impl;

import com.codve.user.dao.UserMapper;
import com.codve.user.exception.EX;
import com.codve.user.model.auth.AuthUser;
import com.codve.user.model.data.object.UserDO;
import com.codve.user.model.query.UserQuery;
import com.codve.user.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.codve.user.util.ExceptionUtil.exception;

/**
 * @author admin
 * @date 2019/11/19 15:48
 */
@Service
@CacheConfig(cacheNames = "UserServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(allEntries = true)
    public int save(UserDO userDO) {
        int result = userMapper.save(userDO);
        if (result != 1) {
            exception(EX.E_1101);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(cacheNames = {"ArticleServiceImpl", "UserServiceImpl"}, allEntries = true)
    public int deleteById(Long id) {
        int result = userMapper.deleteById(id);
        if (result != 1) {
            exception(EX.E_1102);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict
    public int update(UserDO userDO) {
        AuthUser user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDO.setId(user.getId());
        int result = userMapper.update(userDO);
        if (result != 1) {
            exception(EX.E_1103);
        }
        return result;
    }

    @Override
    @Cacheable
    public UserDO findById(Long id) {
        UserDO userDO = userMapper.findById(id);
        if (userDO == null) {
            exception(EX.E_1201);
        }
        return userDO;
    }

    @Override
    public UserDO findByName(String name) {
        UserDO userDO = userMapper.findByName(name);
        if (userDO == null) {
            exception(EX.E_1201);
        }
        return userDO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userMapper.findByName(username);
        if (userDO == null) {
            throw new UsernameNotFoundException(EX.E_1201.getMessage());
        }
        return AuthUser.newInstance(userDO);
    }

    @Override
    @Cacheable(unless = "#result.size() == 0")
    public List<UserDO> find(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());
        return userMapper.find(userQuery);
    }

    @Override
    public int count(UserQuery userQuery) {
        return userMapper.count(userQuery);
    }

}
