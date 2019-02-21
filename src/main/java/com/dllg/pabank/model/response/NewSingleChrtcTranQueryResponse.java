package com.dllg.pabank.model.response;

import java.util.List;

/**
 * 单笔理财交易查询-支持多产品
 */
public class NewSingleChrtcTranQueryResponse extends PABankResponse {
    /**
     * 交易类型
     * 1：购买 2：赎回
     */
    private String TranType;
    /**
     * 交易状态
     * （0：成功，1：失败，2：待确认 5：待处理）
     */
    private String TranStatus;
    /**
     * 交易金额
     */
    private String TranAmt;
    /**
     * 交易日期
     */
    private String TranDate;
    /**
     * 交易时间
     */
    private List TranTime;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getTranType() {
        return TranType;
    }

    public void setTranType(String tranType) {
        TranType = tranType;
    }

    public String getTranStatus() {
        return TranStatus;
    }

    public void setTranStatus(String tranStatus) {
        TranStatus = tranStatus;
    }

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
    }

    public String getTranDate() {
        return TranDate;
    }

    public void setTranDate(String tranDate) {
        TranDate = tranDate;
    }

    public List getTranTime() {
        return TranTime;
    }

    public void setTranTime(List tranTime) {
        TranTime = tranTime;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
