package com.codve.user.util;

import com.codve.user.model.data.object.TokenDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @date 2019/12/12 09:09
 */
@Slf4j
public class TokenUtil {

    public static final String PREFIX = "Bearer ";

    public static TokenDO getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        TokenDO tokenDO = new TokenDO();
        log.warn(token);
        if (StringUtils.hasText(token) && token.startsWith(PREFIX)) {
            token = token.substring(PREFIX.length());
            tokenDO.setToken(token);
        }
        return tokenDO;
    }
}
