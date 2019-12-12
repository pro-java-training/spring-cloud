package com.codve.user.service.impl;

import com.codve.user.exception.EX;
import com.codve.user.filter.TokenProvider;
import com.codve.user.model.auth.AuthUser;
import com.codve.user.model.auth.UserType;
import com.codve.user.model.data.object.TokenDO;
import com.codve.user.model.data.object.UserDO;
import com.codve.user.model.query.UserLoginQuery;
import com.codve.user.properties.TokenProperties;
import com.codve.user.service.AuthService;
import com.codve.user.service.TokenService;
import com.codve.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.codve.user.util.ExceptionUtil.exception;

/**
 * @author admin
 * @date 2019/12/10 13:58
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Override
    public String passwordAuth(HttpServletRequest request, UserLoginQuery query) {
        UserDO userDO = userService.findByName(query.getName());
        boolean match = passwordEncoder.matches(query.getPassword(), userDO.getPassword());
        if (!match) {
            exception(EX.E_1202);
        }

        String token = TokenProvider.generateToken(userDO.getId());

        TokenDO param = new TokenDO();
        param.setUserId(userDO.getId());

        List<TokenDO> tokenList = tokenService.find(param);

        Long currentTime = System.currentTimeMillis();

        if (tokenList.size() > 0) {
            TokenDO tokenDO = tokenList.get(0);
            tokenDO.setToken(token);
            tokenDO.setCreateTime(currentTime);
            tokenDO.setExpireTime(currentTime + tokenProperties.getExpire().toMillis());
            tokenService.update(tokenDO);
        } else {
            TokenDO tokenDO = new TokenDO();
            tokenDO.setUserId(userDO.getId());
            tokenDO.setDeviceType(1);
            tokenDO.setDeviceCode("201506");
            tokenDO.setAppType(1);
            tokenDO.setIp(request.getRemoteAddr());
            tokenDO.setToken(token);
            tokenDO.setCreateTime(System.currentTimeMillis());
            tokenDO.setExpireTime(System.currentTimeMillis() + tokenProperties.getExpire().toMillis());
            tokenService.save(tokenDO);
        }
        return token;
    }

    @Override
    public boolean verify(TokenDO tokenDO) {
        UserDO userDO = findUserByToken(tokenDO);
        authenticate(userDO);
        return true;
    }

    @Override
    public boolean verify(TokenDO tokenDO, UserType userType) {
        UserDO userDO = findUserByToken(tokenDO);
        UserType realUserType = UserType.getUserType(userDO.getType());
        if (realUserType != userType) {
            exception(EX.E_1203);
        }
        authenticate(userDO);
        return true;
    }

    private UserDO findUserByToken(TokenDO tokenDO) {
        if (!StringUtils.hasText(tokenDO.getToken())) {
            exception(EX.E_1206);
        }
        tokenDO = tokenService.findByToken(tokenDO.getToken());

        if (System.currentTimeMillis() >= tokenDO.getExpireTime()) {
            exception(EX.E_1205);
        }
        return userService.findById(tokenDO.getUserId());
    }

    private void authenticate(UserDO userDO) {
        AuthUser authUser = AuthUser.newInstance(userDO);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authUser, null, authUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
