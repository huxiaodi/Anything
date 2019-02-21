package com.dllg.pabank.model.request;

/**
 * 会员子账户开立
 */
public class OpenCustAcctIdRequest extends PABankRequest {
    /**
     * 功能标志
     * 1:开户
     * 3:销户
     */
    private String FunctionFlag;
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 会员属性
     */
    private String MemberProperty;
    /**
     * 用户昵称
     */
    private String UserNickname;
    /**
     * 手机号码
     */
    private String Mobile;
    /**
     * 邮箱
     */
    private String Email;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getFunctionFlag() {
        return FunctionFlag;
    }

    public void setFunctionFlag(String functionFlag) {
        FunctionFlag = functionFlag;
    }

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

    public String getMemberProperty() {
        return MemberProperty;
    }

    public void setMemberProperty(String memberProperty) {
        MemberProperty = memberProperty;
    }

    public String getUserNickname() {
        return UserNickname;
    }

    public void setUserNickname(String userNickname) {
        UserNickname = userNickname;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
