package com.dllg.pabank.model.request;

/**
 * 平安支付接口请求公共类
 */
public class PABankRequest {
    /**
     * 系统流水号
     */
    private String CnsmrSeqNo = "";
    /**
     * 商户号
     */
    private String MrchCode = "";

    public String getCnsmrSeqNo() {
        return CnsmrSeqNo;
    }

    public void setCnsmrSeqNo(String cnsmrSeqNo) {
        CnsmrSeqNo = cnsmrSeqNo;
    }

    public String getMrchCode() {
        return MrchCode;
    }

    public void setMrchCode(String mrchCode) {
        MrchCode = mrchCode;
    }
}
