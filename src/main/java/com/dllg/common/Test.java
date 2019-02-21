package com.dllg.common;

import com.alibaba.druid.filter.config.ConfigTools;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws Exception {

//        for (int i = 0; i < 10000; i++) {
//            System.out.println(IdWorker.getId());
//        }
        Date date = new Date();

        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Test.datasource();
    }

    public static void datasource() throws Exception {
        ConfigTools.main(new String[]{"This.is.pwd3.14;"});
    }

    public static void guavaCache() {
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return s;
            }
        };

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(cacheLoader);

        loadingCache.put("a", "1");

    }
}
