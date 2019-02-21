package com.dllg.pabank.model.response;

/**
 * 平台补账-见证收单
 */
public class PlatformAccountSupplyResponse extends PABankResponse {
    /**
     * 前置流水号
     */
    private String FrontSeqNo;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 金额
     */
    private String Amt;
    /**
     * 备注
     */
    private String Remark;
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

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getAmt() {
        return Amt;
    }

    public void setAmt(String amt) {
        Amt = amt;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
