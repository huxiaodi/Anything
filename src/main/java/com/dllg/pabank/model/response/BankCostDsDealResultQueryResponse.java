package com.dllg.pabank.model.response;

import java.util.List;

/**
 * 查询银行费用扣收结果
 */
public class BankCostDsDealResultQueryResponse extends PABankResponse {
    /**
     * 本次交易返回查询结果记录数
     */
    private String ResultNum;
    /**
     * 起始记录号
     */
    private String StartRecordNo;
    /**
     * 结束标志
     * 0：否  1：是
     */
    private String EndFlag;
    /**
     * 符合业务查询条件的记录总数
     * 重复次数（一次最多返回20条记录）
     */
    private String TotalNum;
    /**
     * 费项数组
     */
    private String FeeArray;
    /**
     * 费用类型
     * 1：提现手续费 2：会员验证费 3：服务费
     */
    private String FeeType;
    /**
     * 费用起始日期
     */
    private String FeeStartDate;
    /**
     * 费用结束日期
     */
    private String FeeEndDate;
    /**
     * 费用扣收日期
     */
    private String FeeDeductDate;
    /**
     * 收费金额
     */
    private String ChargeAmt;
    /**
     * 交易状态
     * 0 成功 1失败 2超时 3未处理
     */
    private String TranStatus;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getResultNum() {
        return ResultNum;
    }

    public void setResultNum(String resultNum) {
        ResultNum = resultNum;
    }

    public String getStartRecordNo() {
        return StartRecordNo;
    }

    public void setStartRecordNo(String startRecordNo) {
        StartRecordNo = startRecordNo;
    }

    public String getEndFlag() {
        return EndFlag;
    }

    public void setEndFlag(String endFlag) {
        EndFlag = endFlag;
    }

    public String getTotalNum() {
        return TotalNum;
    }

    public void setTotalNum(String totalNum) {
        TotalNum = totalNum;
    }

    public String getFeeArray() {
        return FeeArray;
    }

    public void setFeeArray(String feeArray) {
        FeeArray = feeArray;
    }

    public String getFeeType() {
        return FeeType;
    }

    public void setFeeType(String feeType) {
        FeeType = feeType;
    }

    public String getFeeStartDate() {
        return FeeStartDate;
    }

    public void setFeeStartDate(String feeStartDate) {
        FeeStartDate = feeStartDate;
    }

    public String getFeeEndDate() {
        return FeeEndDate;
    }

    public void setFeeEndDate(String feeEndDate) {
        FeeEndDate = feeEndDate;
    }

    public String getFeeDeductDate() {
        return FeeDeductDate;
    }

    public void setFeeDeductDate(String feeDeductDate) {
        FeeDeductDate = feeDeductDate;
    }

    public String getChargeAmt() {
        return ChargeAmt;
    }

    public void setChargeAmt(String chargeAmt) {
        ChargeAmt = chargeAmt;
    }

    public String getTranStatus() {
        return TranStatus;
    }

    public void setTranStatus(String tranStatus) {
        TranStatus = tranStatus;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
