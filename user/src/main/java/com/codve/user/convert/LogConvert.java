package com.codve.user.convert;

import com.codve.user.model.data.object.LogDO;
import com.codve.user.model.data.object.TokenDO;
import org.springframework.beans.BeanUtils;

/**
 * @author admin
 * @date 2019/12/17 16:03
 */
public class LogConvert {

    public static LogDO convert(TokenDO tokenDO) {
        LogDO logDO = new LogDO();
        BeanUtils.copyProperties(tokenDO, logDO);
        return logDO;
    }

}
