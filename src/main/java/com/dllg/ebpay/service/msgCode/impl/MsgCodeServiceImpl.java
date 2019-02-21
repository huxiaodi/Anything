package com.dllg.ebpay.service.msgCode.impl;

import com.dllg.common.CommonUtil;
import com.dllg.common.Constants;
import com.dllg.common.HttpInteractionUtils;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.generator.MsgCodeMapper;
import com.dllg.ebpay.model.generator.MsgCode;
import com.dllg.ebpay.model.generator.MsgCodeExample;
import com.dllg.ebpay.service.msgCode.MsgCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class MsgCodeServiceImpl implements MsgCodeService{

	@Autowired
	private MsgCodeMapper msgCodeMapper;
    @Autowired
    private EbpayProperties ebpayProperties;
	@Autowired
	private HttpInteractionUtils httpInteractionUtils;
	@Override
	public String sendVerificationCode(String userName, String content) {
		int intCount = 0;
        intCount = (new Random()).nextInt(9999);//
        if (intCount < 1000) {
            intCount += 1000;
        }
        String verificationCode = intCount + "";
        long time = new Date().getTime() + Constants.VERIFICATIONCODE_EXPIRE_TIME;

        MsgCodeExample
				msgCodeExample = new MsgCodeExample();
        msgCodeExample.createCriteria().andCodeTelEqualTo(userName);
        List<MsgCode> list = msgCodeMapper.selectByExample(msgCodeExample);
        if(list.isEmpty()) {
        	MsgCode msgCode = new MsgCode();
        	msgCode.setCodeId(CommonUtil.uuid());
        	msgCode.setCodeTel(userName);
        	msgCode.setCodeValue(verificationCode);
        	msgCode.setCodeExpireTime(String.valueOf(time));
        	msgCodeMapper.insert(msgCode);
        } else {
        	MsgCode msgCode = list.get(0);
        	msgCode.setCodeValue(verificationCode);
        	msgCode.setCodeExpireTime(String.valueOf(time));
        	msgCodeMapper.updateByPrimaryKeySelective(msgCode);
        }
        //发送短信
		sendMsg(userName, content+verificationCode);
		return verificationCode;
	}


	public String sendMsg(String smsAccount, String smsPwd, String smsSignature, String mobile, String content) {


        /*
			http://120.24.77.129:8888/sms.aspx
			http://120.24.77.129:8888/smsGBK.aspx

			参数名称	含义	说明
			userid	企业id	企业ID
			account	发送用户帐号	用户帐号，由系统管理员
			password	发送帐号密码	用户账号对应的密码
			mobile	全部被叫号码	发信发送的目的号码.多个号码之间用半角逗号隔开
			content	发送内容	短信的内容，内容需要UTF-8编码
			sendTime	定时发送时间	为空表示立即发送，定时发送格式2010-10-24 09:08:10
			action	发送任务命令	设置为固定的send
			extno	扩展子号	请先询问配置的通道是否支持扩展子号，如果不支持，请填空。子号只能为数字，且最多10位数。
	    	******************************************************************************************
	    	<?xml version="1.0" encoding="utf-8" ?>
			<returnsms>
			<returnstatus>status</returnstatus> ---------- 返回状态值：成功返回Success 失败返回：Faild
			<message>message</message> ---------- 返回信息：见下表
			<remainpoint> remainpoint</remainpoint> ---------- 返回余额

			<taskID>taskID</taskID>  -----------  返回本次任务的序列ID
			<successCounts>successCounts</successCounts> --成功短信数：当成功后返回提交成功短信数
			</returnsms>

			返回信息提示	说明
			ok	提交成功
			用户名或密码不能为空	提交的用户名或密码为空
			发送内容包含sql注入字符	包含sql注入字符
			用户名或密码错误	表示用户名或密码错误
			短信号码不能为空	提交的被叫号码为空
			短信内容不能为空	发送内容为空
			包含非法字符：	表示检查到不允许发送的非法字符
			对不起，您当前要发送的量大于您当前余额	当支付方式为预付费是，检查到账户余额不足
			其他错误	其他数据库操作方面的错误

	    	id：1000。账户： qiatu ，密码：123456.
    	 */
    	String[] arr = smsAccount.split(",");
    	String account = "";
    	String userid = "";
    	if (arr.length > 1) {
			account = arr[0];
			userid = arr[1];
		}
    	String post = "";
        try {
        	MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        	map.add("account", account);//短信账号
        	map.add("userid", userid);
        	map.add("password", smsPwd);//短信密码
        	map.add("mobile", mobile);//手机号
        	map.add("action", "send");
        	map.add("content", content+smsSignature);//内容
//        	map.put("extno", "");
//        	map.put("sendTime", "");
        	//post = httpInteractionUtils.post("http://120.76.213.253:8888/sms.aspx", String.class, map);
        	post = httpInteractionUtils.post("http://dxapi.qiatuchina.com/sms.aspx", String.class, map);
            Map<String,Object> result = CommonUtil.xml(post);
            System.out.println("短信发送结果："+result.get("returnstatus").toString());
            if(!StringUtils.isBlank(result.get("message").toString())){
            	if("Success".equals(result.get("returnstatus").toString())) {
            		return "短信发送成功";
            	}
            	return result.get("message").toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return "短信发送成功";
    }

	@Override
	public String sendMsg(String phone, String content) {
		return sendMsg(ebpayProperties.getSmsAccount(),ebpayProperties.getSmsPwd(),ebpayProperties.getSmsSignature(), phone, content);
	}

	@Override
	public boolean judgeCode(String userName, String userVerificationCode) {
		MsgCodeExample msgCodeExample = new MsgCodeExample();
		msgCodeExample.createCriteria().andCodeTelEqualTo(userName)
									.andCodeValueEqualTo(userVerificationCode);
        List<MsgCode> list = msgCodeMapper.selectByExample(msgCodeExample);
        if (list != null && list.size() > 0) {
            if (!StringUtils.isBlank(list.get(0).getCodeExpireTime())) {
                if (Long.valueOf(list.get(0).getCodeExpireTime()) >= new Date().getTime()) {
                    return true;
                }
            }
        }
        return false;
	}


}
