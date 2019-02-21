package com.dllg.pabank.model.response;

/**
 * 风险承受度测评查询
 */
public class RiskAssessQueryResponse extends PABankResponse {
    /**
     * 客户号
     */
    private String ClientNo;
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
    /**
     * 客户风险等级
     * 0：极低
     * 1：低
     * 2：中
     * 3：高
     * 4：极高
     */
    private String ClientRiskLevel;
    /**
     * 评估日期
     */
    private String EvalDate;
    /**
     * 失效日期
     */
    private String InvalidDate;

    public String getClientNo() {
        return ClientNo;
    }

    public void setClientNo(String clientNo) {
        ClientNo = clientNo;
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

    public String getClientRiskLevel() {
        return ClientRiskLevel;
    }

    public void setClientRiskLevel(String clientRiskLevel) {
        ClientRiskLevel = clientRiskLevel;
    }

    public String getEvalDate() {
        return EvalDate;
    }

    public void setEvalDate(String evalDate) {
        EvalDate = evalDate;
    }

    public String getInvalidDate() {
        return InvalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        InvalidDate = invalidDate;
    }
}
