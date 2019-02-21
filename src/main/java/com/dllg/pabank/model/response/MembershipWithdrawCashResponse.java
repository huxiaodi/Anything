package com.dllg.pabank.model.response;

/**
 * 会员提现-不验证
 */
public class MembershipWithdrawCashResponse extends PABankResponse {
    /**
     * 前置流水号
     */
    private String FrontSeqNo;
    /**
     * 转账手续费
     * 固定返回0.00
     */
    private String TransferFee;
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

    public String getTransferFee() {
        return TransferFee;
    }

    public void setTransferFee(String transferFee) {
        TransferFee = transferFee;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
