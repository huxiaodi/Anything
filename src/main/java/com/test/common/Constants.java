package com.test.common;

/**
 * @author duyixin
 * @ClassName: Constants
 * @Description: 常量
 * @date 2017年1月9日 下午7:20:08
 */
public class Constants {
    public static String REGISTER_CONTENT = "您的注册验证码为:";    //注册短信内容
    public static String UPDATEMM_CONTENT = "您的修改密码验证码为:";    //修改密码

    public static final long VERIFICATIONCODE_EXPIRE_TIME = 1800000;//短信有效时间 30分钟

    public static final int COOKIE_TIME = 60 * 60 * 3;//cookie超时时间

    // 平安请求成功CODE
    public static final String PA_TRADE_SUCCESS_CODE = "000000";


    
    /***
     * 
    * @Description: 日志操作类型 
    * @author zybiao
    * @date 2017年6月23日 上午8:37:18
     */
    public enum OperateType{
    	ADD("1","新增"),DELETE("2","删除"),UPDATE("3","修改"),QUERY("4","查询");
    	private String  stateCode;
    	private String stateName;
    	private OperateType(String stateCode, String stateName) {
             this.stateCode = stateCode;
             this.stateName = stateName;
        }
		public String getStateCode() {
			return stateCode;
		}
		public String getStateName() {
			return stateName;
		}
		
		public static OperateType getScheduleOrderState(String stateCode){
			for (OperateType operateType : values()) {
                if (operateType.getStateCode() == stateCode) {
                    return operateType;
                }
            }
            return null;
		}
		
    }

}
