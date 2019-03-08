package com.test.framework.model;

import com.test.framework.enums.ApiCodeEnum;
import com.test.framework.exception.AnyException;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by hugo on 2017/10/15.
 */
public class ApiResult<T> {

    @ApiModelProperty(value = "标识码，1：成功，其他：错误",required = true)
    private Integer code;

    @ApiModelProperty(value = "描述说明",required = true)
    private String msg;

    @ApiModelProperty(value = "返回数据",required = false)
    private T data;

    @Override
    public String toString() {
        return "ApiResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
    public ApiResult(){

    }
    public ApiResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(AnyException anyException) {
        this.code = anyException.getCode();
        this.msg = anyException.getMessage();
    }

    public ApiResult(ApiCodeEnum apiCodeEnum) {
        this.code = apiCodeEnum.getCode();
        this.msg = apiCodeEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
