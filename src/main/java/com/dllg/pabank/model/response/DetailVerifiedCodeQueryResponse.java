package com.dllg.pabank.model.response;

/**
 * 查询明细单验证码
 */
public class DetailVerifiedCodeQueryResponse extends PABankResponse {
    /**
     * 前置流水号
     */
    private String FrontSeqNo;
    /**
     * 明细单验证码
     */
    private String DetailCheckCode;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getFrontSeqNo() {
        return FrontSeqNo;
    }

    public void setFrontSeqNo(String frontSeqNo) {
        FrontSeqNo = frontSeqNo;
    }

    public String getDetailCheckCode() {
        return DetailCheckCode;
    }

    public void setDetailCheckCode(String detailCheckCode) {
        DetailCheckCode = detailCheckCode;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
