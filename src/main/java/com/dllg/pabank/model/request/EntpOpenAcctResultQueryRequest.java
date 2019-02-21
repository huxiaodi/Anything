package com.dllg.pabank.model.request;

/**
 * 企业开户结果查询
 */
public class EntpOpenAcctResultQueryRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 客户名称
     */
    private String ClientName;
    /**
     * 证件类型
     * U-统一社会信用代码
     * K-组织机构代码
     */
    private String GlobalType;
    /**
     * 证件号码
     */
    private String GlobalId;
    /**
     * 基金公司代码
     */
    private String FundComCode;
    /**
     * 账号
     */
    private String AcctNo;
    /**
     * 商户的客户号
     */
    private String TraderClientNo;
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

    public String getFundComCode() {
        return FundComCode;
    }

    public void setFundComCode(String fundComCode) {
        FundComCode = fundComCode;
    }

    public String getAcctNo() {
        return AcctNo;
    }

    public void setAcctNo(String acctNo) {
        AcctNo = acctNo;
    }

    public String getTraderClientNo() {
        return TraderClientNo;
    }

    public void setTraderClientNo(String traderClientNo) {
        TraderClientNo = traderClientNo;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
