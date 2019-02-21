package com.dllg.pabank.model.response;

/**
 * 查询银行提现退单信息
 */
public class BankWithdrawCashBackQueryResponse extends PABankResponse {
    /**
     * 本次交易返回查询结果记录数
     */
    private String ResultNum;
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
