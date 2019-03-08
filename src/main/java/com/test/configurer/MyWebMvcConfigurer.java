package com.test.configurer;

import com.test.framework.security.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                // 排除配置
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/callback/**")
                .excludePathPatterns("/user/login/**")
                .excludePathPatterns("/user/register/**")
                .excludePathPatterns("/home/**")
                .excludePathPatterns("/bind/**")
                //.excludePathPatterns("/pabank/**")
                .excludePathPatterns("/openapi*/**") //对于开放式API暂时放开认证，提供给TMS使用
                .excludePathPatterns("/swagger*/**")
                .excludePathPatterns("/api/pay/**")// 业务平台发起的支付相关接口
                .excludePathPatterns("/accountSub/accountSubController/returnBack")
                .excludePathPatterns("/trade/accountSubTrade/**")
                .excludePathPatterns("/pay/getPayInfoForBusiness")
                .excludePathPatterns("/pay/returnbackForBusiness")
                .excludePathPatterns("/pay/returnback")
                .excludePathPatterns("/pay/queryStatus")
                .excludePathPatterns("/refund/applicationfordrawback")


                .excludePathPatterns("/invoiceManage/generateXml")

                .excludePathPatterns("/testApi/show")


                //.excludePathPatterns("/admin/**")
//                .excludePathPatterns("/admin/dictionaryManage/**")


                // 拦截配置
                .addPathPatterns("/**");
    }
}
