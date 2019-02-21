package com.dllg.pabank.service;

import com.dllg.pabank.model.request.*;
import com.dllg.pabank.model.response.*;


/**
 *
 */
public interface PABankService {

    /**
     * 会员子账户开立
     * 会员在银行注册，并开立会员子账户，交易网会员代码即会员在平台端系统的会员编号。
     * 平台需保存银行返回的子账户账号，后续交易接口都会用到。会员属性字段为预留扩展字段，当前必须送默认值
     *
     * @param request
     * @return
     * @throws Exception
     */
    OpenCustAcctIdResponse openCustAcctId(OpenCustAcctIdRequest request) throws Exception;

    /**
     * 会员绑定提现账户-银联鉴权
     *
     * @param request
     * @return
     * @throws Exception
     */
    BindRelateAcctUnionPayResponse bindRelateAcctUnionPay(BindRelateAcctUnionPayRequest request) throws Exception;

    /**
     * 会员绑定提现账户-回填银联鉴权短信码
     *
     * @param request
     * @return
     * @throws Exception
     */
    BindRelateAccReUnionPayResponse bindRelateAccReUnionPay(BindRelateAccReUnionPayRequest request) throws Exception;

    /**
     * 会员绑定提现账户-小额鉴权
     *
     * @param request
     * @return
     * @throws Exception
     */
    BindRelateAcctSmallAmountResponse bindRelateAcctSmallAmount(BindRelateAcctSmallAmountRequest request) throws Exception;

    /**
     * 验证鉴权金额
     *
     * @param request
     * @return
     * @throws Exception
     */
    CheckAmountResponse checkAmount(CheckAmountRequest request) throws Exception;

    /**
     * 会员绑定信息查询
     *
     * @param request
     * @return
     * @throws Exception
     */
    MemberBindQueryResponse memberBindQuery(MemberBindQueryRequest request) throws Exception;

    /**
     * 会员解绑提现账户
     *
     * @param request
     * @return
     * @throws Exception
     */
    UnbindRelateAcctResponse unbindRelateAcct(UnbindRelateAcctRequest request) throws Exception;

    /**
     * 会员提现-支持手续费
     *
     * @param request
     * @return
     * @throws Exception
     */
    MemberWithdrawCashResponse memberWithdrawCash(MemberWithdrawCashRequest request) throws Exception;

    /**
     * 会员间交易-不验证
     *
     * @param request
     * @return
     * @throws Exception
     */
    MemberTransactionResponse memberTransaction(MemberTransactionRequest request) throws Exception;

    /**
     * 登记挂账
     *
     * @param request
     * @return
     * @throws Exception
     */
    RegisterBillResponse registerBill(RegisterBillRequest request) throws Exception;

    /**
     * 查询小额鉴权转账结果
     *
     * @param request
     * @return
     * @throws Exception
     */
    SmallAmountTransferQueryResponse smallAmountTransferQuery(SmallAmountTransferQueryRequest request) throws Exception;

    /**
     * 查询银行在途清算结果
     *
     * @param request
     * @return
     * @throws Exception
     */
    BankClearQueryResponse bankClearQuery(BankClearQueryRequest request) throws Exception;

    /**
     * 查询银行子账户余额
     *
     * @param request
     * @return
     * @throws Exception
     */
    CustAcctIdBalanceQueryResponse custAcctIdBalanceQuery(CustAcctIdBalanceQueryRequest request) throws Exception;

    /**
     * 查询子帐号历史余额及待转可提现状态信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    CustAcctIdHistoryBalanceQueryResponse custAcctIdHistoryBalanceQuery(CustAcctIdHistoryBalanceQueryRequest request) throws Exception;

    /**
     * 查询银行单笔交易状态
     *
     * @param request
     * @return
     * @throws Exception
     */
    SingleTransactionStatusQueryResponse singleTransactionStatusQuery(SingleTransactionStatusQueryRequest request) throws Exception;

    /**
     * 查询银行提现退单信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    BankWithdrawCashBackQueryResponse bankWithdrawCashBackQuery(BankWithdrawCashBackQueryRequest request) throws Exception;

    /**
     * 查询普通转账充值明细
     *
     * @param request
     * @return
     * @throws Exception
     */
    CommonTransferRechargeQueryResponse commonTransferRechargeQuery(CommonTransferRechargeQueryRequest request) throws Exception;

    /**
     * 查询银行时间段内交易明细
     *
     * @param request
     * @return
     * @throws Exception
     */
    BankTransactionDetailsQueryResponse bankTransactionDetailsQuery(BankTransactionDetailsQueryRequest request) throws Exception;

    /**
     * 查询银行时间段内清分提现明细
     *
     * @param request
     * @return
     * @throws Exception
     */
    BankWithdrawCashDetailsQueryResponse bankWithdrawCashDetailsQuery(BankWithdrawCashDetailsQueryRequest request) throws Exception;

    /**
     * 查询资金汇总账户余额
     *
     * @param request
     * @return
     * @throws Exception
     */
    SupAcctIdBalanceQueryResponse supAcctIdBalanceQuery(SupAcctIdBalanceQueryRequest request) throws Exception;

    /**
     * 根据会员代码查询会员子账号
     *
     * @param request
     * @return
     * @throws Exception
     */
    QueryCustAcctIdByThirdCustIdResponse queryCustAcctIdByThirdCustId(QueryCustAcctIdByThirdCustIdRequest request) throws Exception;

    /**
     * 查询对账文件信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    ReconciliationDocumentQueryResponse reconciliationDocumentQuery(ReconciliationDocumentQueryRequest request) throws Exception;

    /**
     * 会员提现-支持手续费及支付密码验证
     *
     * @param request
     * @return
     * @throws Exception
     */
    MemberWithdrawSprtFeePwdVerifyResponse memberWithdrawSprtFeePwdVerify(MemberWithdrawSprtFeePwdVerifyRequest request) throws Exception;

    /**
     * 会员间交易-验证短信动态码
     *
     * @param request
     * @return
     * @throws Exception
     */
    MemberTranVerifyTextMsgsResponse memberTranVerifyTextMsgs(MemberTranVerifyTextMsgsRequest request) throws Exception;

    /**
     * 会员交易-验密
     *
     * @param request
     * @return
     * @throws Exception
     */
    MemberTransactionsInspectionResponse memberTransactionsInspection(MemberTransactionsInspectionRequest request) throws Exception;

    /**
     * 平台订单管理
     *
     * @param request
     * @return
     * @throws Exception
     */
    PlatformOrderManagementResponse platformOrderManagement(PlatformOrderManagementRequest request) throws Exception;

    /**
     * 申请修改手机号码
     *
     * @param request
     * @return
     * @throws Exception
     */
    ApplyForChangeOfCellPhoneNumResponse applyForChangeOfCellPhoneNum(ApplyForChangeOfCellPhoneNumRequest request) throws Exception;

    /**
     * 回填动态码-修改手机
     *
     * @param request
     * @return
     * @throws Exception
     */
    BackfillDynamicPasswordResponse backfillDynamicPassword(BackfillDynamicPasswordRequest request) throws Exception;

    /**
     * 申请提现或支付短信动态码
     *
     * @param request
     * @return
     * @throws Exception
     */
    ApplicationTextMsgDynamicCodeResponse applicationTextMsgDynamicCode(ApplicationTextMsgDynamicCodeRequest request) throws Exception;

    /**
     * 风险承受度测评
     *
     * @param request
     * @return
     * @throws Exception
     */
    RiskAssessResponse riskAssess(RiskAssessRequest request) throws Exception;

    /**
     * 风险承受度测评查询
     *
     * @param request
     * @return
     * @throws Exception
     */
    RiskAssessQueryResponse riskAssessQuery(RiskAssessQueryRequest request) throws Exception;

    /**
     * 企业客户开户
     *
     * @param request
     * @return
     * @throws Exception
     */
    EntpClientOpenAcctResponse entpClientOpenAcct(EntpClientOpenAcctRequest request) throws Exception;

    /**
     * 企业开户结果查询
     *
     * @param request
     * @return
     * @throws Exception
     */
    EntpOpenAcctResultQueryResponse entpOpenAcctResultQuery(EntpOpenAcctResultQueryRequest request) throws Exception;

    /**
     * 赎回模式查询
     *
     * @param request
     * @return
     * @throws Exception
     */
    RedemptionModeQueryResponse redemptionModeQuery(RedemptionModeQueryRequest request) throws Exception;

    /**
     * 补款流水查询
     *
     * @param request
     * @return
     * @throws Exception
     */
    BatchNoQueryResponse batchNoQuery(BatchNoQueryRequest request) throws Exception;

    /**
     * 根据补款流水查询交易
     *
     * @param request
     * @return
     * @throws Exception
     */
    QueryTransactionByBatchNoResponse queryTransactionByBatchNo(QueryTransactionByBatchNoRequest request) throws Exception;

    /**
     * 理财开户-支持多产品
     *
     * @param request
     * @return
     * @throws Exception
     */
    NewOpenChrematisticAcctIdResponse newOpenChrematisticAcctId(NewOpenChrematisticAcctIdRequest request) throws Exception;

    /**
     * 理财购买-支持多产品
     *
     * @param request
     * @return
     * @throws Exception
     */
    NewBuyChrematisticProductResponse newBuyChrematisticProduct(NewBuyChrematisticProductRequest request) throws Exception;

    /**
     * 理财赎回-支持多产品
     *
     * @param request
     * @return
     * @throws Exception
     */
    NewRedeemChrematisticProductResponse newRedeemChrematisticProduct(NewRedeemChrematisticProductRequest request) throws Exception;

    /**
     * 7日年化收益率查询-支持多产品
     *
     * @param request
     * @return
     * @throws Exception
     */
    SevenDayYearYieldQueryResponse sevenDayYearYieldQuery(SevenDayYearYieldQueryRequest request) throws Exception;

    /**
     * 会员理财余额查询-支持多产品
     *
     * @param request
     * @return
     * @throws Exception
     */
    NewCustChrtcBalanceQueryResponse newCustChrtcBalanceQuery(NewCustChrtcBalanceQueryRequest request) throws Exception;

    /**
     * 会员每日收益查询-支持多产品
     *
     * @param request
     * @return
     * @throws Exception
     */
    NewCustDailyYieldQueryResponse newCustDailyYieldQuery(NewCustDailyYieldQueryRequest request) throws Exception;

    /**
     * 单笔理财交易查询-支持多产品
     *
     * @param request
     * @return
     * @throws Exception
     */
    NewSingleChrtcTranQueryResponse newSingleChrtcTranQuery(NewSingleChrtcTranQueryRequest request) throws Exception;

    /**
     * 调账-见证收单
     *
     * @param request
     * @return
     * @throws Exception
     */
    AccountRegulationResponse accountRegulation(AccountRegulationRequest request) throws Exception;

    /**
     * 查询充值明细-见证收单
     *
     * @param request
     * @return
     * @throws Exception
     */
    ChargeDetailQueryResponse chargeDetailQuery(ChargeDetailQueryRequest request) throws Exception;

    /**
     * 平台补账-见证收单
     *
     * @param request
     * @return
     * @throws Exception
     */
    PlatformAccountSupplyResponse platformAccountSupply(PlatformAccountSupplyRequest request) throws Exception;

    /**
     * 修改会员属性-普通商户子账户
     *
     * @param request
     * @return
     * @throws Exception
     */
    MbrPropertyReviseResponse mbrPropertyRevise(MbrPropertyReviseRequest request) throws Exception;

    /**
     * 开户并绑卡-银联鉴权
     *
     * @param request
     * @return
     * @throws Exception
     */
    OpenAcctBindCardUnionPayResponse openAcctBindCardUnionPay(OpenAcctBindCardUnionPayRequest request) throws Exception;

    /**
     * 开户并绑卡-回填短信验证码
     *
     * @param request
     * @return
     * @throws Exception
     */
    OpenAcctBindCardReUnionPayResponse openAcctBindCardReUnionPay(OpenAcctBindCardReUnionPayRequest request) throws Exception;

    /**
     * 维护会员绑定提现账户联行号
     *
     * @param request
     * @return
     * @throws Exception
     */
    MntMbrBindRelateAcctBankCodeResponse mntMbrBindRelateAcctBankCode(MntMbrBindRelateAcctBankCodeRequest request) throws Exception;

    /**
     * 会员提现-不验证
     *
     * @param request
     * @return
     * @throws Exception
     */
    MembershipWithdrawCashResponse membershipWithdrawCash(MembershipWithdrawCashRequest request) throws Exception;

    /**
     * 会员批量交易-不验证
     *
     * @param request
     * @return
     * @throws Exception
     */
    MembershipBatchTransactionsResponse membershipBatchTransactions(MembershipBatchTransactionsRequest request) throws Exception;

    /**
     * 会员批量交易-验证短信动态码
     *
     * @param request
     * @return
     * @throws Exception
     */
    MbrBatchTransVerifiedTextMsgsResponse mbrBatchTransVerifiedTextMsgs(MbrBatchTransVerifiedTextMsgsRequest request) throws Exception;

    /**
     * 会员资金冻结-不验证
     *
     * @param request
     * @return
     * @throws Exception
     */
    MembershipTrancheFreezeResponse membershipTrancheFreeze(MembershipTrancheFreezeRequest request) throws Exception;

    /**
     * 会员资金冻结-验证短信动态码
     *
     * @param request
     * @return
     * @throws Exception
     */
    MbrTchFrzVerifiedTextMsgsResponse mbrTchFrzVerifiedTextMsgs(MbrTchFrzVerifiedTextMsgsRequest request) throws Exception;

    /**
     * 会员资金支付-不验证
     *
     * @param request
     * @return
     * @throws Exception
     */
    MembershipTranchePayResponse membershipTranchePay(MembershipTranchePayRequest request) throws Exception;

    /**
     * 会员资金支付-验短信动态码
     *
     * @param request
     * @return
     * @throws Exception
     */
    MbrTchPayVerifiedTextMsgsResponse mbrTchPayVerifiedTextMsgs(MbrTchPayVerifiedTextMsgsRequest request) throws Exception;

    /**
     * 会员间交易退款-不验证
     *
     * @param request
     * @return
     * @throws Exception
     */
    MemberTransactionRefundResponse memberTransactionRefund(MemberTransactionRefundRequest request) throws Exception;

    /**
     * 查询会员子账号
     *
     * @param request
     * @return
     * @throws Exception
     */
    QueryCustAcctIdResponse queryCustAcctId(QueryCustAcctIdRequest request) throws Exception;

    /**
     * 查询会员子账号余额
     *
     * @param request
     * @return
     * @throws Exception
     */
    QueryCustAcctIdBalanceResponse queryCustAcctIdBalance(QueryCustAcctIdBalanceRequest request) throws Exception;

    /**
     * 查询银行费用扣收结果
     *
     * @param request
     * @return
     * @throws Exception
     */
    BankCostDsDealResultQueryResponse bankCostDsDealResultQuery(BankCostDsDealResultQueryRequest request) throws Exception;

}
