package com.dllg.pabank.model.response;

/**
 * 理财赎回-支持多产品
 */
public class NewRedeemChrematisticProductResponse extends PABankResponse {
    /**
     * 前置流水号
     */
    private String FrontSeqNo;
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
