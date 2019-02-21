package com.dllg.pabank.model.response;

/**
 * 根据补款流水查询交易
 */
public class QueryTransactionByBatchNoResponse extends PABankResponse {
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
     * 交易信息数组
     */
    private String TranItemArray;
    /**
     * 渠道业务流水号
     */
    private String ChannelBussSeqNo;
    /**
     * 客户号
     */
    private String ClientNo;
    /**
     * 交易日期
     */
    private String TranDate;
    /**
     * 交易时间
     */
    private String TranTime;
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
     * 交易状态
     * 0 申请成功待确认
     * 1 申请失败
     * 2 已撤单（成功）
     * 3 确认成功
     * 4 确认失败
     * 5 单笔确认失败
     * 6 已撤单（超时）
     * 7 错误失败
     * A 认购接受
     * T 待托管系统审核
     * X 处理中
     */
    private String TranStatus;
    /**
     * 币种
     */
    private String Ccy;
    /**
     * 处理状态
     * 4 扣款失败
     * 3 扣款成功
     * 0 未处理
     * 6 还款成功
     * 7 还款失败
     * PS 预受理成功
     */
    private String DealStatus;
    /**
     * 业务日期
     */
    private String BussDate;
    /**
     * 补款流水号
     */
    private String ExtraSeqNo;
    /**
     * 银行账号
     */
    private String BankAcctNo;
    /**
     * 证件类型
     */
    private String GlobalType;
    /**
     * 证件号码
     */
    private String GlobalId;
    /**
     * 客户名称
     */
    private String ClinetName;
    /**
     * 商户号
     */
    private String TraderCode;
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

    public String getTranItemArray() {
        return TranItemArray;
    }

    public void setTranItemArray(String tranItemArray) {
        TranItemArray = tranItemArray;
    }

    public String getChannelBussSeqNo() {
        return ChannelBussSeqNo;
    }

    public void setChannelBussSeqNo(String channelBussSeqNo) {
        ChannelBussSeqNo = channelBussSeqNo;
    }

    public String getClientNo() {
        return ClientNo;
    }

    public void setClientNo(String clientNo) {
        ClientNo = clientNo;
    }

    public String getTranDate() {
        return TranDate;
    }

    public void setTranDate(String tranDate) {
        TranDate = tranDate;
    }

    public String getTranTime() {
        return TranTime;
    }

    public void setTranTime(String tranTime) {
        TranTime = tranTime;
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

    public String getTranStatus() {
        return TranStatus;
    }

    public void setTranStatus(String tranStatus) {
        TranStatus = tranStatus;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public String getDealStatus() {
        return DealStatus;
    }

    public void setDealStatus(String dealStatus) {
        DealStatus = dealStatus;
    }

    public String getBussDate() {
        return BussDate;
    }

    public void setBussDate(String bussDate) {
        BussDate = bussDate;
    }

    public String getExtraSeqNo() {
        return ExtraSeqNo;
    }

    public void setExtraSeqNo(String extraSeqNo) {
        ExtraSeqNo = extraSeqNo;
    }

    public String getBankAcctNo() {
        return BankAcctNo;
    }

    public void setBankAcctNo(String bankAcctNo) {
        BankAcctNo = bankAcctNo;
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

    public String getClinetName() {
        return ClinetName;
    }

    public void setClinetName(String clinetName) {
        ClinetName = clinetName;
    }

    public String getTraderCode() {
        return TraderCode;
    }

    public void setTraderCode(String traderCode) {
        TraderCode = traderCode;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
