package com.test.framework.exception;

import com.test.framework.enums.ApiCodeEnum;

public class AnyException extends RuntimeException {
    private Integer code;

    public AnyException(ApiCodeEnum apiCodeEnum) {
        super(apiCodeEnum.getMessage());
        this.code = apiCodeEnum.getCode();
    }
    public AnyException(String message) {
        super(message);
        this.code = ApiCodeEnum.FAILURE.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
