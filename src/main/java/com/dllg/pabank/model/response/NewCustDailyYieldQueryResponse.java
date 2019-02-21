package com.dllg.pabank.model.response;

import java.util.List;

/**
 * 会员每日收益查询-支持多产品
 */
public class NewCustDailyYieldQueryResponse extends PABankResponse {
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
     * 产品数组
     */
    private String ProductArray;
    /**
     * 产品代码
     */
    private String ProductCode;
    /**
     * 产品名称
     */
    private List ProductName;
    /**
     * 收益日期
     */
    private String IncomeDate;
    /**
     * 收益金额
     */
    private String IncomeAmt;
    /**
     * 万份收益
     * 小数点后带四位
     */
    private String UnitIncome;
    /**
     * 累计收入
     */
    private String TotalIncome;
    /**
     * 证件类型
     */
    private String GlobalType;
    /**
     * 证件号码
     */
    private String GlobalId;
    /**
     * 银行账号
     */
    private String BankAcctNo;
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

    public String getProductArray() {
        return ProductArray;
    }

    public void setProductArray(String productArray) {
        ProductArray = productArray;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public List getProductName() {
        return ProductName;
    }

    public void setProductName(List productName) {
        ProductName = productName;
    }

    public String getIncomeDate() {
        return IncomeDate;
    }

    public void setIncomeDate(String incomeDate) {
        IncomeDate = incomeDate;
    }

    public String getIncomeAmt() {
        return IncomeAmt;
    }

    public void setIncomeAmt(String incomeAmt) {
        IncomeAmt = incomeAmt;
    }

    public String getUnitIncome() {
        return UnitIncome;
    }

    public void setUnitIncome(String unitIncome) {
        UnitIncome = unitIncome;
    }

    public String getTotalIncome() {
        return TotalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        TotalIncome = totalIncome;
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
