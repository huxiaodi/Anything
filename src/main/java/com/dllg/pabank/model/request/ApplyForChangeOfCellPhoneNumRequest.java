package com.dllg.pabank.model.request;

/**
 * 申请修改手机号码
 */
public class ApplyForChangeOfCellPhoneNumRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 修改方式
     * 1：短信验证码
     * 2：银联鉴权
     */
    private String ModifyType;
    /**
     * 新手机号码
     */
    private String NewMobile;
    /**
     * 银行卡号
     * 当修改方式为2时必输
     */
    private String BankCardNo;
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

    public String getTranNetMemberCode() {
        return TranNetMemberCode;
    }

    public void setTranNetMemberCode(String tranNetMemberCode) {
        TranNetMemberCode = tranNetMemberCode;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getModifyType() {
        return ModifyType;
    }

    public void setModifyType(String modifyType) {
        ModifyType = modifyType;
    }

    public String getNewMobile() {
        return NewMobile;
    }

    public void setNewMobile(String newMobile) {
        NewMobile = newMobile;
    }

    public String getBankCardNo() {
        return BankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        BankCardNo = bankCardNo;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
