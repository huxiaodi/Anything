package com.dllg.pabank.model.request;

/**
 * 理财购买-支持多产品
 */
public class NewBuyChrematisticProductRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
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
     * 0-身份证
     * U-统一社会信用代码
     * K-组织机构代码
     */
    private String GlobalType;
    /**
     * 证件号码
     */
    private String GlobalId;
    /**
     * 购买金额
     */
    private String BuyAmt;
    /**
     * 产品代码
     */
    private String ProductCode;
    /**
     * 银行账号
     * 企业购买时为必输
     */
    private String BankAcctNo;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
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

    public String getBuyAmt() {
        return BuyAmt;
    }

    public void setBuyAmt(String buyAmt) {
        BuyAmt = buyAmt;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getBankAcctNo() {
        return BankAcctNo;
    }

    public void setBankAcctNo(String bankAcctNo) {
        BankAcctNo = bankAcctNo;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
