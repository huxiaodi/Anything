package com.dllg.pabank.model.request;

/**
 * 理财开户-支持多产品
 */
public class NewOpenChrematisticAcctIdRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 客户名称
     */
    private String ClinetName;
    /**
     * 证件类型
     * 0：身份证
     */
    private String GlobalType;
    /**
     * 证件号码
     */
    private String GlobalId;
    /**
     * 证件失效日期
     */
    private String GlobalInvalidDate;
    /**
     * 手机号码
     */
    private String Mobile;
    /**
     * 产品代码
     * 二选一，目前一般上送TA_NO
     */
    private String ProductCode;
    /**
     * 基金公司代码
     */
    private String FundComCode;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getClinetName() {
        return ClinetName;
    }

    public void setClinetName(String clinetName) {
        ClinetName = clinetName;
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

    public String getGlobalInvalidDate() {
        return GlobalInvalidDate;
    }

    public void setGlobalInvalidDate(String globalInvalidDate) {
        GlobalInvalidDate = globalInvalidDate;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getFundComCode() {
        return FundComCode;
    }

    public void setFundComCode(String fundComCode) {
        FundComCode = fundComCode;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
