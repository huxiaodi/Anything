package com.dllg.ebpay.service.dx.impl;

import com.dingxianginc.ctu.client.CaptchaClient;
import com.dingxianginc.ctu.client.model.CaptchaResponse;
import com.dllg.ebpay.service.dx.DXService;
import com.dllg.framework.exception.AnyException;
import org.springframework.stereotype.Service;

@Service
public class DXServiceImpl implements DXService{

    @Override
    public boolean checkToken(String token){
        try {
            /**构造入参为appId和appSecret
             * appId和前端验证码的appId保持一致，appId可公开
             * appSecret为秘钥，请勿公开
             * token在前端完成验证后可以获取到，随业务请求发送到后台，token有效期为两分钟**/
            String appId = "e9a1d305fc43f48f94ea8ae35ed93be2";
            String appSecret = "95aa710c079450d5d9bd03305ea70c30";
            CaptchaClient captchaClient = new CaptchaClient(appId,appSecret);
            //captchaClient.setCaptchaUrl(captchaUrl);
            //特殊情况需要额外指定服务器,可以在这个指定，默认情况下不需要设置
            CaptchaResponse response = captchaClient.verifyToken(token);
            System.out.println(response.getCaptchaStatus());
            //确保验证状态是SERVER_SUCCESS，SDK中有容错机制，在网络出现异常的情况会返回通过
            if (response.getResult() & "SERVER_SUCCESS".equals(response.getCaptchaStatus())) {
                /**token验证通过，继续其他流程**/
                return true;
            } else {
                /**token验证失败，业务系统可以直接阻断该次请求或者继续弹验证码**/
                return false;
            }
        }catch (Exception e){
           throw new AnyException("验证码错误");
        }

    }
}
