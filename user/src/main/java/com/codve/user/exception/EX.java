package com.codve.user.exception;

/**
 * @author admin
 * @date 2019/11/21 16:47
 */
public enum EX {

    // 成功
    E_0(0, "success"),

    E_999(999, "未知错误"),
    E_1000(1000, "请求错误"),

    E_1001(1001, "请求方式错误"),
    E_1002(1002, "请求参数错误"),
    E_1003(1003, "参数验证失败"),
    E_1004(1004, "参数类型错误"),

    E_1101(1101, "添加失败"),

    E_1102(1102, "删除失败"),

    E_1103(1103, "修改失败"),

    E_1104(1104, "无数据"),

    E_1201(1201, "查询不到该用户"),
    E_1202(1202, "用户名或密码错误"),
    E_1203(1203, "认证失败"),
    E_1204(1204, "权限不足"),
    E_1205(1205, "认证过期"),
    E_1206(1206, "无效认证"),

 ;

    private int code;

    private String message;

    EX(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
