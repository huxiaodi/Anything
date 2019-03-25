package com.test.framework.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

@Component
public class GuavaCacheUtil {

    private static final LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return s;
                }
            });

    public static String get(String key) {
        return loadingCache.getUnchecked(key);
    }

    public static void put(String key, String value) {
        loadingCache.put(key, value);
    }
}
