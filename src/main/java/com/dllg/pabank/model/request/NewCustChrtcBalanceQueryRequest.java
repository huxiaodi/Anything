package com.dllg.pabank.model.request;

/**
 * 会员理财余额查询-支持多产品
 */
public class NewCustChrtcBalanceQueryRequest extends PABankRequest {
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
     * 证件类型、证件号码和客户号二选一
     */
    private String ClientNo;
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
     * 产品代码
     */
    private String ProductCode;
    /**
     * 银行账号
     * 机构客户必须上送银行帐号
     */
    private String BankAcctNo;
    /**
     * 商户的客户号
     */
    private String TraderClientNo;
    /**
     * 子商户的客户号
     * 支付机构商户客户号，为空时查询支付机构所有商户的创建结果（包含支付机构）
     */
    private String SubTraderClientNo;
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

    public String getTraderClientNo() {
        return TraderClientNo;
    }

    public void setTraderClientNo(String traderClientNo) {
        TraderClientNo = traderClientNo;
    }

    public String getSubTraderClientNo() {
        return SubTraderClientNo;
    }

    public void setSubTraderClientNo(String subTraderClientNo) {
        SubTraderClientNo = subTraderClientNo;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
