package com.dllg.pabank.model.response;

/**
 * 查询小额鉴权转账结果
 */
public class SmallAmountTransferQueryResponse extends PABankResponse {
    /**
     * 返回状态
     */
    private String ReturnStatu;
    /**
     * 返回信息
     */
    private String ReturnMsg;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getReturnStatu() {
        return ReturnStatu;
    }

    public void setReturnStatu(String returnStatu) {
        ReturnStatu = returnStatu;
    }

    public String getReturnMsg() {
        return ReturnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        ReturnMsg = returnMsg;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
