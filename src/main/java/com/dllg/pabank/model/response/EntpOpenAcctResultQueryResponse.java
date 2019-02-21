package com.dllg.pabank.model.response;

/**
 * 企业开户结果查询
 */
public class EntpOpenAcctResultQueryResponse extends PABankResponse {
    /**
     * 本次交易返回查询结果记录数
     */
    private String ResultNum;
    /**
     * 信息数组
     */
    private String InfoArray;
    /**
     * 基金帐号
     */
    private String FundAcctNo;
    /**
     * 基金交易帐号
     */
    private String FundTranAcctNo;
    /**
     * 客户号
     */
    private String ClientNo;
    /**
     * 开户状态
     * 0：未复核
     * 1：复核通过
     * 2：驳回修改
     * 3：驳回放弃
     * 4：驳回要求
     * 5：驳回修改时放弃
     * 6：申请中
     * 7：影像资料已上传
     * 8：平安银行审批中
     * 10：平安银行审批通过
     * 11：平安银行审批不通过
     */
    private String AcctOpenStatus;
    /**
     * 银行账号
     */
    private String BankAcctNo;
    /**
     * 银行账户名称
     */
    private String BankAcctName;
    /**
     * 申请时间
     */
    private String ApplyTime;
    /**
     * 错误信息
     */
    private String ErrorMsg;

    public String getResultNum() {
        return ResultNum;
    }

    public void setResultNum(String resultNum) {
        ResultNum = resultNum;
    }

    public String getInfoArray() {
        return InfoArray;
    }

    public void setInfoArray(String infoArray) {
        InfoArray = infoArray;
    }

    public String getFundAcctNo() {
        return FundAcctNo;
    }

    public void setFundAcctNo(String fundAcctNo) {
        FundAcctNo = fundAcctNo;
    }

    public String getFundTranAcctNo() {
        return FundTranAcctNo;
    }

    public void setFundTranAcctNo(String fundTranAcctNo) {
        FundTranAcctNo = fundTranAcctNo;
    }

    public String getClientNo() {
        return ClientNo;
    }

    public void setClientNo(String clientNo) {
        ClientNo = clientNo;
    }

    public String getAcctOpenStatus() {
        return AcctOpenStatus;
    }

    public void setAcctOpenStatus(String acctOpenStatus) {
        AcctOpenStatus = acctOpenStatus;
    }

    public String getBankAcctNo() {
        return BankAcctNo;
    }

    public void setBankAcctNo(String bankAcctNo) {
        BankAcctNo = bankAcctNo;
    }

    public String getBankAcctName() {
        return BankAcctName;
    }

    public void setBankAcctName(String bankAcctName) {
        BankAcctName = bankAcctName;
    }

    public String getApplyTime() {
        return ApplyTime;
    }

    public void setApplyTime(String applyTime) {
        ApplyTime = applyTime;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}