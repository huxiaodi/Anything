package com.dllg.pabank.model.request;

/**
 * 风险承受度测评
 */
public class RiskAssessRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 客户号
     */
    private String ClientNo;
    /**
     * 风险评估答案
     * 答案用,号分割，如：A,B,C,D
     * 个人测评答案数量为10
     * 企业答案数量为17
     */
    private String RiskEvalAnswers;
    /**
     * 证件类型
     * 0-身份证
     * U-统一社会信用代码
     * K-组织机构代码
     */
    private String GlobalType;
    /**
     * 证件号码
     */
    private String GlobalId;

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getClientNo() {
        return ClientNo;
    }

    public void setClientNo(String clientNo) {
        ClientNo = clientNo;
    }

    public String getRiskEvalAnswers() {
        return RiskEvalAnswers;
    }

    public void setRiskEvalAnswers(String riskEvalAnswers) {
        RiskEvalAnswers = riskEvalAnswers;
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
}

