package com.dllg.pabank.model.response;

/**
 * 理财开户-支持多产品
 */
public class NewOpenChrematisticAcctIdResponse extends PABankResponse {
    /**
     * 前置流水号
     */
    private String FrontSeqNo;
    /**
     * 客户号
     */
    private String ClientNo;
    /**
     * 客户名称
     */
    private String ClinetName;
    /**
     * 证件类型
     */
    private String GlobalType;
    /**
     * 证件号码
     */
    private String GlobalId;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
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

    public String getClientNo() {
        return ClientNo;
    }

    public void setClientNo(String clientNo) {
        ClientNo = clientNo;
    }

    public String getClinetName() {
        return ClinetName;
    }

    public void setClinetName(String clinetName) {
        ClinetName = clinetName;
    }

    public String getGlobalType() {
        return GlobalType;
    }

    public void setGlobalType(String globalType) {
        GlobalType = globalType;
    }

    public String getGlobalId() {
        return GlobalId;
    }

    public void setGlobalId(String globalId) {
        GlobalId = globalId;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
