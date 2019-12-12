package com.codve.user.service.impl;

import com.codve.user.dao.TokenMapper;
import com.codve.user.exception.EX;
import com.codve.user.model.data.object.TokenDO;
import com.codve.user.service.TokenService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codve.user.util.ExceptionUtil.exception;

/**
 * @author admin
 * @date 2019/12/11 14:32
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public int save(TokenDO tokenDO) {
        int result = tokenMapper.save(tokenDO);
        if (result != 1) {
            exception(EX.E_1101);
        }
        return result;
    }

    @Override
    public int update(TokenDO tokenDO) {
        int result = tokenMapper.update(tokenDO);
        if (result != 1) {
            exception(EX.E_1103);
        }
        return result;
    }

    @Override
    public List<TokenDO> find(TokenDO tokenDO) {
        PageHelper.startPage(1, 1);
        return tokenMapper.find(tokenDO);
    }

    @Override
    public TokenDO findByToken(String token) {
        PageHelper.startPage(1, 1);
        TokenDO tokenDO = tokenMapper.findByToken(token);
        if (tokenDO == null) {
            exception(EX.E_1206);
        }
        return tokenDO;
    }
}
