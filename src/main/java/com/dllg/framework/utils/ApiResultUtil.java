package com.dllg.framework.utils;

import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.model.ApiResult;

/**
 * Created by hugo on 2017/10/15.
 */
public class ApiResultUtil {

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<T>(ApiCodeEnum.SUCCESS.getCode(), ApiCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ApiResult<T> error(String msg) {
        return new ApiResult<T>(ApiCodeEnum.FAILURE.getCode(), msg);
    }
}
