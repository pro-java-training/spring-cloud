package com.codve.article.util;

import com.codve.article.exception.CommonException;
import com.codve.article.exception.EX;

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
