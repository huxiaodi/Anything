package com.dllg.pabank.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.pabank.model.request.*;
import com.dllg.pabank.model.response.*;
import com.dllg.pabank.service.PABankService;
import com.pabank.sdk.PABankSDK;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

@Service
public class PABankServiceImpl implements PABankService {


    @Autowired
    private EbpayProperties ebpayProperties;

    public static final Logger logger = LogManager.getLogger(PABankServiceImpl.class);

    /**
     * 每天的序列号不能重复
     *
     * @return
     */
    private String genetorCnsmrSeqNo() {
        return ebpayProperties.getUid() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"))+ new Random().nextInt(10);
    }

    private Map<String, Object> goApi(PABankRequest request) throws IOException {
        logger.debug(request);
        request.setCnsmrSeqNo(genetorCnsmrSeqNo());
        request.setMrchCode(ebpayProperties.getMrchCode());
        // 序列化配置对象
        SerializeConfig config = new SerializeConfig();
        config.setPropertyNamingStrategy(PropertyNamingStrategy.PascalCase);
        String retMap = JSONObject.toJSONString(request, config, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
        logger.debug(retMap);
        String simpleName = request.getClass().getSimpleName().replaceAll("Request", "");
        logger.debug(simpleName);
        Map<String, Object> result = PABankSDK.getInstance().apiInter(retMap, simpleName);
        logger.info("\n{} --> request:{}\n response:{}",simpleName,retMap,result);
        return result;
    }

    @Override
    public OpenCustAcctIdResponse openCustAcctId(OpenCustAcctIdRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), OpenCustAcctIdResponse.class);
    }

    @Override
    public BindRelateAcctUnionPayResponse bindRelateAcctUnionPay(BindRelateAcctUnionPayRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), BindRelateAcctUnionPayResponse.class);
    }

    @Override
    public BindRelateAccReUnionPayResponse bindRelateAccReUnionPay(BindRelateAccReUnionPayRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), BindRelateAccReUnionPayResponse.class);
    }

    @Override
    public BindRelateAcctSmallAmountResponse bindRelateAcctSmallAmount(BindRelateAcctSmallAmountRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), BindRelateAcctSmallAmountResponse.class);
    }

    @Override
    public CheckAmountResponse checkAmount(CheckAmountRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), CheckAmountResponse.class);
    }

    @Override
    public MemberBindQueryResponse memberBindQuery(MemberBindQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MemberBindQueryResponse.class);
    }

    @Override
    public UnbindRelateAcctResponse unbindRelateAcct(UnbindRelateAcctRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), UnbindRelateAcctResponse.class);
    }

    @Override
    public MemberWithdrawCashResponse memberWithdrawCash(MemberWithdrawCashRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MemberWithdrawCashResponse.class);
    }

    @Override
    public MemberTransactionResponse memberTransaction(MemberTransactionRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MemberTransactionResponse.class);
    }

    @Override
    public RegisterBillResponse registerBill(RegisterBillRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), RegisterBillResponse.class);
    }

    @Override
    public SmallAmountTransferQueryResponse smallAmountTransferQuery(SmallAmountTransferQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), SmallAmountTransferQueryResponse.class);
    }

    @Override
    public BankClearQueryResponse bankClearQuery(BankClearQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), BankClearQueryResponse.class);
    }

    @Override
    public CustAcctIdBalanceQueryResponse custAcctIdBalanceQuery(CustAcctIdBalanceQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        CustAcctIdBalanceQueryResponse response = JSONObject.parseObject(JSON.toJSONString(returnMap), CustAcctIdBalanceQueryResponse.class);
        response.setCustAcctIdBalanceQuerySubResponseList(JSONArray.parseArray(response.getAcctArray(), CustAcctIdBalanceQuerySubResponse.class));
        return response;
    }

    @Override
    public CustAcctIdHistoryBalanceQueryResponse custAcctIdHistoryBalanceQuery(CustAcctIdHistoryBalanceQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), CustAcctIdHistoryBalanceQueryResponse.class);
    }

    @Override
    public SingleTransactionStatusQueryResponse singleTransactionStatusQuery(SingleTransactionStatusQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), SingleTransactionStatusQueryResponse.class);
    }

    @Override
    public BankWithdrawCashBackQueryResponse bankWithdrawCashBackQuery(BankWithdrawCashBackQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), BankWithdrawCashBackQueryResponse.class);
    }

    @Override
    public CommonTransferRechargeQueryResponse commonTransferRechargeQuery(CommonTransferRechargeQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), CommonTransferRechargeQueryResponse.class);
    }

    @Override
    public BankTransactionDetailsQueryResponse bankTransactionDetailsQuery(BankTransactionDetailsQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), BankTransactionDetailsQueryResponse.class);
    }

    @Override
    public BankWithdrawCashDetailsQueryResponse bankWithdrawCashDetailsQuery(BankWithdrawCashDetailsQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), BankWithdrawCashDetailsQueryResponse.class);
    }

    @Override
    public SupAcctIdBalanceQueryResponse supAcctIdBalanceQuery(SupAcctIdBalanceQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), SupAcctIdBalanceQueryResponse.class);
    }

    @Override
    public QueryCustAcctIdByThirdCustIdResponse queryCustAcctIdByThirdCustId(QueryCustAcctIdByThirdCustIdRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), QueryCustAcctIdByThirdCustIdResponse.class);
    }

    @Override
    public ReconciliationDocumentQueryResponse reconciliationDocumentQuery(ReconciliationDocumentQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), ReconciliationDocumentQueryResponse.class);
    }

    @Override
    public MemberWithdrawSprtFeePwdVerifyResponse memberWithdrawSprtFeePwdVerify(MemberWithdrawSprtFeePwdVerifyRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MemberWithdrawSprtFeePwdVerifyResponse.class);
    }

    @Override
    public MemberTranVerifyTextMsgsResponse memberTranVerifyTextMsgs(MemberTranVerifyTextMsgsRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MemberTranVerifyTextMsgsResponse.class);
    }

    @Override
    public MemberTransactionsInspectionResponse memberTransactionsInspection(MemberTransactionsInspectionRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MemberTransactionsInspectionResponse.class);
    }

    @Override
    public PlatformOrderManagementResponse platformOrderManagement(PlatformOrderManagementRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), PlatformOrderManagementResponse.class);
    }

    @Override
    public ApplyForChangeOfCellPhoneNumResponse applyForChangeOfCellPhoneNum(ApplyForChangeOfCellPhoneNumRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), ApplyForChangeOfCellPhoneNumResponse.class);
    }

    @Override
    public BackfillDynamicPasswordResponse backfillDynamicPassword(BackfillDynamicPasswordRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), BackfillDynamicPasswordResponse.class);
    }

    @Override
    public ApplicationTextMsgDynamicCodeResponse applicationTextMsgDynamicCode(ApplicationTextMsgDynamicCodeRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), ApplicationTextMsgDynamicCodeResponse.class);
    }

    @Override
    public RiskAssessResponse riskAssess(RiskAssessRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), RiskAssessResponse.class);
    }

    @Override
    public RiskAssessQueryResponse riskAssessQuery(RiskAssessQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), RiskAssessQueryResponse.class);
    }

    @Override
    public EntpClientOpenAcctResponse entpClientOpenAcct(EntpClientOpenAcctRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), EntpClientOpenAcctResponse.class);
    }

    @Override
    public EntpOpenAcctResultQueryResponse entpOpenAcctResultQuery(EntpOpenAcctResultQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), EntpOpenAcctResultQueryResponse.class);
    }

    @Override
    public RedemptionModeQueryResponse redemptionModeQuery(RedemptionModeQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), RedemptionModeQueryResponse.class);
    }

    @Override
    public BatchNoQueryResponse batchNoQuery(BatchNoQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), BatchNoQueryResponse.class);
    }

    @Override
    public QueryTransactionByBatchNoResponse queryTransactionByBatchNo(QueryTransactionByBatchNoRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), QueryTransactionByBatchNoResponse.class);
    }

    @Override
    public NewOpenChrematisticAcctIdResponse newOpenChrematisticAcctId(NewOpenChrematisticAcctIdRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), NewOpenChrematisticAcctIdResponse.class);
    }

    @Override
    public NewBuyChrematisticProductResponse newBuyChrematisticProduct(NewBuyChrematisticProductRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), NewBuyChrematisticProductResponse.class);
    }

    @Override
    public NewRedeemChrematisticProductResponse newRedeemChrematisticProduct(NewRedeemChrematisticProductRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), NewRedeemChrematisticProductResponse.class);
    }

    @Override
    public SevenDayYearYieldQueryResponse sevenDayYearYieldQuery(SevenDayYearYieldQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), SevenDayYearYieldQueryResponse.class);
    }

    @Override
    public NewCustChrtcBalanceQueryResponse newCustChrtcBalanceQuery(NewCustChrtcBalanceQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), NewCustChrtcBalanceQueryResponse.class);
    }

    @Override
    public NewCustDailyYieldQueryResponse newCustDailyYieldQuery(NewCustDailyYieldQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), NewCustDailyYieldQueryResponse.class);
    }

    @Override
    public NewSingleChrtcTranQueryResponse newSingleChrtcTranQuery(NewSingleChrtcTranQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), NewSingleChrtcTranQueryResponse.class);
    }

    @Override
    public AccountRegulationResponse accountRegulation(AccountRegulationRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), AccountRegulationResponse.class);
    }

    @Override
    public ChargeDetailQueryResponse chargeDetailQuery(ChargeDetailQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), ChargeDetailQueryResponse.class);
    }

    @Override
    public PlatformAccountSupplyResponse platformAccountSupply(PlatformAccountSupplyRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), PlatformAccountSupplyResponse.class);
    }

    @Override
    public MbrPropertyReviseResponse mbrPropertyRevise(MbrPropertyReviseRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MbrPropertyReviseResponse.class);
    }

    @Override
    public OpenAcctBindCardUnionPayResponse openAcctBindCardUnionPay(OpenAcctBindCardUnionPayRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), OpenAcctBindCardUnionPayResponse.class);
    }

    @Override
    public OpenAcctBindCardReUnionPayResponse openAcctBindCardReUnionPay(OpenAcctBindCardReUnionPayRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), OpenAcctBindCardReUnionPayResponse.class);
    }

    @Override
    public MntMbrBindRelateAcctBankCodeResponse mntMbrBindRelateAcctBankCode(MntMbrBindRelateAcctBankCodeRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MntMbrBindRelateAcctBankCodeResponse.class);
    }

    @Override
    public MembershipWithdrawCashResponse membershipWithdrawCash(MembershipWithdrawCashRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MembershipWithdrawCashResponse.class);
    }

    @Override
    public MembershipBatchTransactionsResponse membershipBatchTransactions(MembershipBatchTransactionsRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MembershipBatchTransactionsResponse.class);
    }

    @Override
    public MbrBatchTransVerifiedTextMsgsResponse mbrBatchTransVerifiedTextMsgs(MbrBatchTransVerifiedTextMsgsRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MbrBatchTransVerifiedTextMsgsResponse.class);
    }

    @Override
    public MembershipTrancheFreezeResponse membershipTrancheFreeze(MembershipTrancheFreezeRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MembershipTrancheFreezeResponse.class);
    }

    @Override
    public MbrTchFrzVerifiedTextMsgsResponse mbrTchFrzVerifiedTextMsgs(MbrTchFrzVerifiedTextMsgsRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MbrTchFrzVerifiedTextMsgsResponse.class);
    }

    @Override
    public MembershipTranchePayResponse membershipTranchePay(MembershipTranchePayRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MembershipTranchePayResponse.class);
    }

    @Override
    public MbrTchPayVerifiedTextMsgsResponse mbrTchPayVerifiedTextMsgs(MbrTchPayVerifiedTextMsgsRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MbrTchPayVerifiedTextMsgsResponse.class);
    }

    @Override
    public MemberTransactionRefundResponse memberTransactionRefund(MemberTransactionRefundRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), MemberTransactionRefundResponse.class);
    }

    @Override
    public QueryCustAcctIdResponse queryCustAcctId(QueryCustAcctIdRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), QueryCustAcctIdResponse.class);
    }

    @Override
    public QueryCustAcctIdBalanceResponse queryCustAcctIdBalance(QueryCustAcctIdBalanceRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), QueryCustAcctIdBalanceResponse.class);
    }

    @Override
    public BankCostDsDealResultQueryResponse bankCostDsDealResultQuery(BankCostDsDealResultQueryRequest request) throws Exception {
        Map<String, Object> returnMap = goApi(request);
        return JSONObject.parseObject(JSON.toJSONString(returnMap), BankCostDsDealResultQueryResponse.class);
    }
}
