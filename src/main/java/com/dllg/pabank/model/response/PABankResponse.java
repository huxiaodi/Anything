package com.dllg.pabank.model.response;

/**
 * 平安支付接口，公共返回类
 */
public class PABankResponse {
    /**
     * 返回码
     */
    private String TxnReturnCode;
    /**
     * 返回信息
     */
    private String TxnReturnMsg;
    /**
     * 系统流水号,将输入报文的流水号原样返回
     */
    private String CnsmrSeqNo;

    public String getTxnReturnCode() {
        return TxnReturnCode;
    }

    public void setTxnReturnCode(String txnReturnCode) {
        TxnReturnCode = txnReturnCode;
    }

    public String getTxnReturnMsg() {
        return TxnReturnMsg;
    }

    public void setTxnReturnMsg(String txnReturnMsg) {
        TxnReturnMsg = txnReturnMsg;
    }

    public String getCnsmrSeqNo() {
        return CnsmrSeqNo;
    }

    public void setCnsmrSeqNo(String cnsmrSeqNo) {
        CnsmrSeqNo = cnsmrSeqNo;
    }
}
