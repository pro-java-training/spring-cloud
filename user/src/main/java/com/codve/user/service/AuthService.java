package com.codve.user.service;

import com.codve.user.model.auth.UserType;
import com.codve.user.model.data.object.TokenDO;
import com.codve.user.model.query.UserLoginQuery;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @date 2019/12/10 13:56
 */
public interface AuthService {

    /**
     * 使用用户密码授权
     * @param request request
     * @param query UserLoginQuery
     * @return token
     */
    String passwordAuth(HttpServletRequest request, UserLoginQuery query);

    /**
     * 验证 token
     * @param tokenDO tokenDO
     * @return boolean
     */
    boolean verify(TokenDO tokenDO);

    /**
     * 验证 token
     * @param tokenDO tokenDO
    * @param userType userType
     * @return boolean
     */
    boolean verify(TokenDO tokenDO, UserType userType);

}
