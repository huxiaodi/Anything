package com.dllg.pabank.model.response;

/**
 * 企业客户开户
 */
public class EntpClientOpenAcctResponse extends PABankResponse {
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
    private String ClientName;
    /**
     * 证件类型
     */
    private String GlobalType;
    /**
     * 证件号码
     */
    private String GlobalId;
    /**
     * 银行账户名称
     */
    private String BankAcctName;
    /**
     * 银行账号
     */
    private String BankAcctNo;
    /**
     * 开户网点
     */
    private String OpenSubBranchId;
    /**
     * 开户网点名称
     */
    private String OpenSubBranchName;
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

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
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

    public String getBankAcctName() {
        return BankAcctName;
    }

    public void setBankAcctName(String bankAcctName) {
        BankAcctName = bankAcctName;
    }

    public String getBankAcctNo() {
        return BankAcctNo;
    }

    public void setBankAcctNo(String bankAcctNo) {
        BankAcctNo = bankAcctNo;
    }

    public String getOpenSubBranchId() {
        return OpenSubBranchId;
    }

    public void setOpenSubBranchId(String openSubBranchId) {
        OpenSubBranchId = openSubBranchId;
    }

    public String getOpenSubBranchName() {
        return OpenSubBranchName;
    }

    public void setOpenSubBranchName(String openSubBranchName) {
        OpenSubBranchName = openSubBranchName;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
