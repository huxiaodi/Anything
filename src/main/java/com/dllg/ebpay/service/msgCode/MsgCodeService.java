package com.dllg.ebpay.service.msgCode;


public interface MsgCodeService {
    /***
     *
     * @author zouqian
     * @Description: 发送验证码
     * @param userName 手机号码
     * @return
     * @throws
     */
    String sendVerificationCode(String userName,String content);


    /***
     *
     * @author zouqian
     * @Description: 检查验证码是否正确
     * @param userName 手机号码
     * @param userVerificationCode 用户输入的验证码
     * @return
     * @throws
     */
    boolean judgeCode(String userName, String userVerificationCode);

    /**
     * @param @param smsaccount
     * @param @param smspwd
     * @param @param smssignature
     * @param @param userName
     * @param @param string    设定文件
     * @return void    返回类型
     * @throws
     * @Title: sendMsg
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */


    String sendMsg(String smsaccount, String smspwd, String smssignature, String userName, String string);


    String sendMsg(String phone, String content);

}
