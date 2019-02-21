package com.dllg.pabank.model.response;

/**
 * 申请修改手机号码
 */
public class ApplyForChangeOfCellPhoneNumResponse extends PABankResponse {
    /**
     * 接收手机号码
     * 短信码发到该手机上，银行只返回后四位
     */
    private String ReceiveMobile;
    /**
     * 短信指令号
     */
    private String MessageOrderNo;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getReceiveMobile() {
        return ReceiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        ReceiveMobile = receiveMobile;
    }

    public String getMessageOrderNo() {
        return MessageOrderNo;
    }

    public void setMessageOrderNo(String messageOrderNo) {
        MessageOrderNo = messageOrderNo;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
