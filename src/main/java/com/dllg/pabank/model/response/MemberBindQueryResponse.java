package com.dllg.pabank.model.response;

import javax.net.ssl.SSLContext;

/**
 * 会员绑定信息查询
 */
public class MemberBindQueryResponse extends PABankResponse {
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
     * 0:否
     * 1:是
     */
    private String EndFlag;
    /**
     * 符合业务查询条件的记录总数
     * 重复次数（一次最多返回20条记录）
     */
    private String TotalNum;
    /**
     * 数组
     */
    private String TranItemArray;
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

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
