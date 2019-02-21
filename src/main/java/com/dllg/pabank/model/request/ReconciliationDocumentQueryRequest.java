package com.dllg.pabank.model.request;

/**
 * 查询对账文件信息
 */
public class ReconciliationDocumentQueryRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 文件类型
     * 充值文件-CZ
     * 提现文件-TX
     * 交易文件-JY
     * 余额文件-YE
     * 购买赎回-GSLD
     * 理财收益-profit
     */
    private String FileType;
    /**
     * 文件日期
     */
    private String FileDate;
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

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getFileDate() {
        return FileDate;
    }

    public void setFileDate(String fileDate) {
        FileDate = fileDate;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
