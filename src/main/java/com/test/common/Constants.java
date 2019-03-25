package com.test.common;

public class Constants {

    public static final int COOKIE_TIME = 60 * 60 * 3;//cookie超时时间



    
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
