package com.test.framework.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.framework.configurer.JwtProperties;
import com.test.framework.enums.ApiCodeEnum;
import com.test.framework.exception.AnyException;
import com.test.framework.model.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Map;


@Component
public class Auth {
    private final static ThreadLocal<AuthUser> theadUser = new ThreadLocal<AuthUser>();

    @Autowired
    public void setJwtProperties(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    private static JwtProperties jwtProperties;

    public AuthUser getCurrentUser() {
        return theadUser.get();
    }

    public void setCurrentUser(AuthUser user) {
        theadUser.set(user);
    }


    /**
     * 创建TOKEN
     * @param authUser
     * @param ttlMillis
     * @return
     */
    public String createJWToken(AuthUser authUser, long ttlMillis) {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Map<String, Object> claims = JSONObject.parseObject(JSON.toJSONString(authUser), Map.class);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getBase64EncodedSecretKey());

        // 设置token的过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expirationDate = new Date(expMillis);
            builder.setExpiration(expirationDate);
        }

        return builder.compact();
    }

    public String createJWToken(AuthUser authUser) {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Map<String, Object> claims = JSONObject.parseObject(JSON.toJSONString(authUser), Map.class);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getBase64EncodedSecretKey());

        return builder.compact();
    }

    /**
     * 解析TOKEN
     * @param token
     * @return
     */
    public static AuthUser parseJWToken(String token) {

        AuthUser authUser = null;

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtProperties.getBase64EncodedSecretKey()))
                    .parseClaimsJws(token).getBody();

            authUser = JSONObject.parseObject(JSON.toJSONString(claims), AuthUser.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AnyException(ApiCodeEnum.TOKEN_INVALID);
        }

        return authUser;
    }

}
