package com.dllg.pabank.model.response;

/**
 * 申请提现或支付短信动态码
 */
public class ApplicationTextMsgDynamicCodeResponse extends PABankResponse {
    /**
     * 接收手机号码
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
