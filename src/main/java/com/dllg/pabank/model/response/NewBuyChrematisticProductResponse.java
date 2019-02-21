package com.dllg.pabank.model.response;

/**
 * 理财购买-支持多产品
 */
public class NewBuyChrematisticProductResponse extends PABankResponse {
    /**
     * 前置流水号
     */
    private String FrontSeqNo;
    /**
     * 客户号
     */
    private String ClientNo;
    /**
     * 产品代码
     */
    private String ProductCode;
    /**
     * 银行账号
     */
    private String BankAcctNo;
    /**
     * 处理状态
     * 5：待处理
     */
    private String DealStatus;
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

    public String getDealStatus() {
        return DealStatus;
    }

    public void setDealStatus(String dealStatus) {
        DealStatus = dealStatus;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
