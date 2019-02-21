package com.dllg.pabank.model.request;

/**
 * 修改会员属性-普通商户子账户
 */
public class MbrPropertyReviseRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 会员属性
     * 00-普通子账号
     * SH-商户子账户
     * （暂时只支持00-普通子账号改为SH-商户子账户）
     */
    private String MemberProperty;
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

    public String getMemberProperty() {
        return MemberProperty;
    }

    public void setMemberProperty(String memberProperty) {
        MemberProperty = memberProperty;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
