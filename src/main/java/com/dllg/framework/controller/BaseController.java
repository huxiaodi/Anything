package com.dllg.framework.controller;

import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.model.ApiResult;

/**
 * Created by hugo on 2017/10/15.
 */
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
