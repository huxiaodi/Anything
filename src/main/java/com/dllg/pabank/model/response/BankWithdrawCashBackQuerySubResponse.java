package com.dllg.pabank.model.response;

public class BankWithdrawCashBackQuerySubResponse {
    //原交易流水号
   private String OldTranSeqNo;
    //原前置流水号
   private String OldFrontSeqNo;
    //原市场流水号
   private String OldMarketSeqNo;
    //原附言信息
   private String OldAddMsg;
    //退票原因
   private String RejectBillReason;
    //退票日期
   private String RejectBillDate;
   //退票入账的交易流水号
   private String RejectInAcctTranSeqNo;
   //退票入账的交易金额
   private String RejectInAcctTranAmt;
    //退票入账的付款账号
   private String RejectInPayerAcctNo;
    //退票入账的付款户名
   private String RejectInPayerAcctName;
    //退票入账的付款方行号
   private String RejectInPayerBranchId;
    //退票入账的付款方行名
   private String RejectInPayerBranchName;
    //收款人见证子账号
   private String PayeeWitnessSubAcctNo;
    //收款方前置流水号
   private String PayeeFrontSeqNo;
    //保留域1
   private String ReservedMsgOne;
    //保留域2
   private String ReservedMsgTwo;
    //保留域3
   private String ReservedMsgThree;

    public String getOldTranSeqNo() {
        return OldTranSeqNo;
    }

    public void setOldTranSeqNo(String oldTranSeqNo) {
        OldTranSeqNo = oldTranSeqNo;
    }

    public String getOldFrontSeqNo() {
        return OldFrontSeqNo;
    }

    public void setOldFrontSeqNo(String oldFrontSeqNo) {
        OldFrontSeqNo = oldFrontSeqNo;
    }

    public String getOldMarketSeqNo() {
        return OldMarketSeqNo;
    }

    public void setOldMarketSeqNo(String oldMarketSeqNo) {
        OldMarketSeqNo = oldMarketSeqNo;
    }

    public String getOldAddMsg() {
        return OldAddMsg;
    }

    public void setOldAddMsg(String oldAddMsg) {
        OldAddMsg = oldAddMsg;
    }

    public String getRejectBillReason() {
        return RejectBillReason;
    }

    public void setRejectBillReason(String rejectBillReason) {
        RejectBillReason = rejectBillReason;
    }

    public String getRejectBillDate() {
        return RejectBillDate;
    }

    public void setRejectBillDate(String rejectBillDate) {
        RejectBillDate = rejectBillDate;
    }

    public String getRejectInAcctTranSeqNo() {
        return RejectInAcctTranSeqNo;
    }

    public void setRejectInAcctTranSeqNo(String rejectInAcctTranSeqNo) {
        RejectInAcctTranSeqNo = rejectInAcctTranSeqNo;
    }

    public String getRejectInAcctTranAmt() {
        return RejectInAcctTranAmt;
    }

    public void setRejectInAcctTranAmt(String rejectInAcctTranAmt) {
        RejectInAcctTranAmt = rejectInAcctTranAmt;
    }

    public String getRejectInPayerAcctNo() {
        return RejectInPayerAcctNo;
    }

    public void setRejectInPayerAcctNo(String rejectInPayerAcctNo) {
        RejectInPayerAcctNo = rejectInPayerAcctNo;
    }

    public String getRejectInPayerAcctName() {
        return RejectInPayerAcctName;
    }

    public void setRejectInPayerAcctName(String rejectInPayerAcctName) {
        RejectInPayerAcctName = rejectInPayerAcctName;
    }

    public String getRejectInPayerBranchId() {
        return RejectInPayerBranchId;
    }

    public void setRejectInPayerBranchId(String rejectInPayerBranchId) {
        RejectInPayerBranchId = rejectInPayerBranchId;
    }

    public String getRejectInPayerBranchName() {
        return RejectInPayerBranchName;
    }

    public void setRejectInPayerBranchName(String rejectInPayerBranchName) {
        RejectInPayerBranchName = rejectInPayerBranchName;
    }

    public String getPayeeWitnessSubAcctNo() {
        return PayeeWitnessSubAcctNo;
    }

    public void setPayeeWitnessSubAcctNo(String payeeWitnessSubAcctNo) {
        PayeeWitnessSubAcctNo = payeeWitnessSubAcctNo;
    }

    public String getPayeeFrontSeqNo() {
        return PayeeFrontSeqNo;
    }

    public void setPayeeFrontSeqNo(String payeeFrontSeqNo) {
        PayeeFrontSeqNo = payeeFrontSeqNo;
    }

    public String getReservedMsgOne() {
        return ReservedMsgOne;
    }

    public void setReservedMsgOne(String reservedMsgOne) {
        ReservedMsgOne = reservedMsgOne;
    }

    public String getReservedMsgTwo() {
        return ReservedMsgTwo;
    }

    public void setReservedMsgTwo(String reservedMsgTwo) {
        ReservedMsgTwo = reservedMsgTwo;
    }

    public String getReservedMsgThree() {
        return ReservedMsgThree;
    }

    public void setReservedMsgThree(String reservedMsgThree) {
        ReservedMsgThree = reservedMsgThree;
    }
}
