package com.dllg.framework.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void expire(String key, Long timeout, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, timeout, timeUnit);
    }

    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    public void hset(String h, Object hk, Object hv) {
        stringRedisTemplate.opsForHash().put(h, hk, hv);
    }

    public Object hget(String h, Object hk) {
        return stringRedisTemplate.opsForHash().get(h, hk);
    }

    public Object hdel(String h, Object hk) {
        return stringRedisTemplate.opsForHash().delete(h, hk);
    }

    public void hmset(String h, Map map) {
        stringRedisTemplate.opsForHash().putAll(h, map);
    }

    public Set keys(String key) {
        return stringRedisTemplate.keys(key);
    }

    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }



}