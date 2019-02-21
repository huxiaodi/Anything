package com.dllg.pabank.model.request;

/**
 * 赎回模式查询
 */
public class RedemptionModeQueryRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 客户号
     */
    private String ClientNo;
    /**
     * 交易份额
     */
    private String TranLot;
    /**
     * 产品代码
     */
    private String ProductCode;
    /**
     * 银行账号
     * 机构客户必须上送银行帐号
     */
    private String BankAcctNo;

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getClientNo() {
        return ClientNo;
    }

    public void setClientNo(String clientNo) {
        ClientNo = clientNo;
    }

    public String getTranLot() {
        return TranLot;
    }

    public void setTranLot(String tranLot) {
        TranLot = tranLot;
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
}
