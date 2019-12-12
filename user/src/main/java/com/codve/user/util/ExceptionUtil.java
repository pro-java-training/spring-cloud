package com.codve.user.util;

import com.codve.user.exception.CommonException;
import com.codve.user.exception.EX;

/**
 * @author admin
 * @date 2019/11/29 10:30
 */
public class ExceptionUtil {

    public static void exception(EX ex) {
        throw new CommonException(ex.getCode(), ex.getMessage());
    }

    public static void exception(int code, String msg) {
        throw new CommonException(code, msg);
    }
}
