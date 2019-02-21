package com.dllg.pabank.model.response;

/**
 * 补款流水查询
 */
public class BatchNoQueryResponse extends PABankResponse {
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
     * 总金额
     */
    private String TotalAmt;
    /**
     * 支付机构客户号
     */
    private String PayOrgClientNo;
    /**
     * 交易信息数组
     */
    private String TranItemArray;
    /**
     * 执行日期
     */
    private String ExecDate;
    /**
     * 执行时间
     */
    private String ExecTime;
    /**
     * 交易金额
     */
    private String TranAmt;
    /**
     * 产品代码
     */
    private String ProductCode;
    /**
     * 产品名称
     */
    private String ProductName;
    /**
     * 执行状态
     * 0：未执行
     * 1：执行中
     * 2：执行成功
     * 3：执行失败
     * x：执行前
     */
    private String ExecStatus;
    /**
     * 币种
     */
    private String Ccy;
    /**
     * 补款流水号
     */
    private String ExtraSeqNo;
    /**
     * 支付机构渠道商户客户号
     */
    private String PayOrgTraderClientNo;
    /**
     * 商户号
     */
    private String TraderCode;
    /**
     * 补款类型
     * 402 普通赎回补款
     * 401 实时赎回补款
     */
    private String ExtraType;
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

    public String getTotalAmt() {
        return TotalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        TotalAmt = totalAmt;
    }

    public String getPayOrgClientNo() {
        return PayOrgClientNo;
    }

    public void setPayOrgClientNo(String payOrgClientNo) {
        PayOrgClientNo = payOrgClientNo;
    }

    public String getTranItemArray() {
        return TranItemArray;
    }

    public void setTranItemArray(String tranItemArray) {
        TranItemArray = tranItemArray;
    }

    public String getExecDate() {
        return ExecDate;
    }

    public void setExecDate(String execDate) {
        ExecDate = execDate;
    }

    public String getExecTime() {
        return ExecTime;
    }

    public void setExecTime(String execTime) {
        ExecTime = execTime;
    }

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getExecStatus() {
        return ExecStatus;
    }

    public void setExecStatus(String execStatus) {
        ExecStatus = execStatus;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public String getExtraSeqNo() {
        return ExtraSeqNo;
    }

    public void setExtraSeqNo(String extraSeqNo) {
        ExtraSeqNo = extraSeqNo;
    }

    public String getPayOrgTraderClientNo() {
        return PayOrgTraderClientNo;
    }

    public void setPayOrgTraderClientNo(String payOrgTraderClientNo) {
        PayOrgTraderClientNo = payOrgTraderClientNo;
    }

    public String getTraderCode() {
        return TraderCode;
    }

    public void setTraderCode(String traderCode) {
        TraderCode = traderCode;
    }

    public String getExtraType() {
        return ExtraType;
    }

    public void setExtraType(String extraType) {
        ExtraType = extraType;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
