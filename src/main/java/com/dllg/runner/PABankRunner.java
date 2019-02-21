package com.dllg.runner;

import com.alibaba.fastjson.JSON;
import com.pabank.sdk.PABankSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

/***
 * 启动时初始化平安银行见证包SDK
 */
@Component
@Order(1)
public class PABankRunner implements ApplicationRunner {
    private final static Logger logger = LoggerFactory.getLogger(PABankRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("---------------"+new File("./").getAbsolutePath());  //发布到tomcat测试时 - (conf文件夹放到tomcat - bin目录下.运行的当前目录)
        //初始化配置
        boolean init = PABankSDK.init("./conf/config.properties");
        logger.info("PABankSDK.init:" + init);

        //验证开发者
        Map<String, Object> map = PABankSDK.getInstance().approveDev();
        logger.info("PABankSDK.验证开发者:");
        logger.info(JSON.toJSONString(map));
    }
}
