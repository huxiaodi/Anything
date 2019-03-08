package com.test.configurer;

import com.test.manage.configurer.EbpayProperties;
import com.test.framework.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "awesome.ebpay")
public class RestTempletConfigurer {
    @Autowired
    private EbpayProperties ebpayProperties;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public FileUploadUtils getFileUploadUtils(){
        FileUploadUtils fileUploadUtils =  new FileUploadUtils();
        fileUploadUtils.setProjectPath(ebpayProperties.getProjectPath());
        fileUploadUtils.setStaticPath(ebpayProperties.getStaticPath());
        fileUploadUtils.setStaticUrl(ebpayProperties.getStaticUrl());
        return fileUploadUtils;
    }



}
