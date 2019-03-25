package com.test.framework.controller;

import com.test.framework.enums.ApiCodeEnum;
import com.test.framework.model.ApiResult;

public class BaseController {

    protected final <T> ApiResult<T> success() {
        return new ApiResult<T>(ApiCodeEnum.SUCCESS.getCode(), ApiCodeEnum.SUCCESS.getMessage());
    }

    protected final <T> ApiResult<T> success(T data) {
        return new ApiResult<T>(ApiCodeEnum.SUCCESS.getCode(), ApiCodeEnum.SUCCESS.getMessage(), data);
    }

    protected final <T> ApiResult<T> error(ApiCodeEnum apiCodeEnum, T data) {
        return new ApiResult<T>(apiCodeEnum.getCode(), apiCodeEnum.getMessage(), data);
    }

    protected final ApiResult error(String msg) {
        return new ApiResult(ApiCodeEnum.FAILURE.getCode(), msg, msg);
    }
}
