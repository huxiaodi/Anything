package com.dllg.pabank.model.request;

/**
 * 理财赎回-支持多产品
 */
public class NewRedeemChrematisticProductRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 客户号
     */
    private String ClientNo;
    /**
     * 赎回金额
     */
    private String RedeemAmt;
    /**
     * 产品代码
     */
    private String ProductCode;
    /**
     * 赎回方式
     * 2-实时赎回
     */
    private String RedeemMode;
    /**
     * 收款子账号
     */
    private String RecvSubAcctNo;
    /**
     * 回款帐号
     * 机构必送
     * 个人可以不上送
     * 转入、开户对应的银行卡账上送了就要校验
     */
    private String ReturnAcctNo;
    /**
     * 保留域
     */
    private String ReservedMsg;
}
