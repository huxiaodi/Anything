package com.dllg.framework.enums;

/**
 * Created by hugo on 2017/10/15.
 */
public enum ApiCodeEnum {
    // 自定义
    SUCCESS(1, "成功"),
    FAILURE(-1, "失败"),
    UNKONW_ERROR(101, "服务器内部错误"),
    UNAUTHORITY(102, "未授权"),
    TOKEN_INVALID(103, "token无效"),
    PWD_MISTAKE(104, "账号密码不正确"),
    // spring mvc
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_REQUEST(400, "Bad Request"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    // ebpay
    OUT_ACCOUNT_NOT_FOUND(1001,"转出账户不存在"),
    IN_ACCOUNT_NOT_FOUND(1002,"转入账户不存在"),
    ACCOUNT_NOT_FOUND(1002,"总账户不存在"),
    ;

    private Integer code;
    private String message;

    ApiCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
