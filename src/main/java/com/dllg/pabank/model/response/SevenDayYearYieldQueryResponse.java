package com.dllg.pabank.model.response;

import java.util.List;

/**
 * 7日年化收益率查询-支持多产品
 */
public class SevenDayYearYieldQueryResponse extends PABankResponse {
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
     */
    private String EndFlag;
    /**
     * 符合业务查询条件的记录总数
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
     * 日期
     */
    private String Date;
    /**
     * 收益率
     */
    private String Yield;
    /**
     * 万份收益
     */
    private String UnitIncome;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getYield() {
        return Yield;
    }

    public void setYield(String yield) {
        Yield = yield;
    }

    public String getUnitIncome() {
        return UnitIncome;
    }

    public void setUnitIncome(String unitIncome) {
        UnitIncome = unitIncome;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
