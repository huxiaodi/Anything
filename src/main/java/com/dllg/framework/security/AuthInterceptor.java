package com.dllg.framework.security;

import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.utils.RedisUtils;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Auth auth;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        auth.setCurrentUser(null);
        String token = request.getHeader("token");
        if(Strings.isNullOrEmpty(token)){
            throw new AnyException(ApiCodeEnum.TOKEN_INVALID);
        }

        Boolean hasKey = redisUtils.hasKey(token);
        if(!hasKey){
            throw new AnyException(ApiCodeEnum.TOKEN_INVALID);
        }else{
            // token 有效期 15分钟
            redisUtils.expire(token,15L,TimeUnit.MINUTES);
        }

        AuthUser  user = Auth.parseJWToken(token);
        auth.setCurrentUser(user);
        return true;
    }
}