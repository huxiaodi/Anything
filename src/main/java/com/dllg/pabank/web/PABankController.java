package com.dllg.pabank.web;


import com.alibaba.fastjson.JSON;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.pabank.model.request.*;
import com.dllg.pabank.model.response.*;
import com.dllg.pabank.service.PABankService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pabank")
public class PABankController extends BaseController {

    private final static Logger logger = LogManager.getLogger(PABankController.class);

    @Autowired
    private PABankService paBankService;
    @Autowired
    private EbpayProperties ebpayProperties;

    @Autowired
    private Auth auth;


    //资金总账户
    private final String fundSummaryAcctNo = "15000081987225";


    @GetMapping("/OpenCustAcctId")
    public ApiResult test() throws Exception {
        OpenCustAcctIdRequest request = new OpenCustAcctIdRequest();
        request.setFunctionFlag("1");
        request.setFundSummaryAcctNo("15000081971664");
        request.setTranNetMemberCode("3281");
        request.setMemberProperty("00");
        request.setUserNickname("test2");
        request.setMobile("");
        request.setReservedMsg("");
        OpenCustAcctIdResponse openCustAcctIdResponse = paBankService.openCustAcctId(request);
        logger.debug(JSON.toJSONString(openCustAcctIdResponse));
        return success(openCustAcctIdResponse);
    }

    @PostMapping("/BindRelateAcctUnionPay")
    public ApiResult BindRelateAcctUnionPay(@RequestBody BindRelateAcctUnionPayRequest request) throws Exception {
        BindRelateAcctUnionPayResponse bindRelateAcctUnionPayResponse = paBankService.bindRelateAcctUnionPay(request);
        logger.debug(JSON.toJSONString(bindRelateAcctUnionPayResponse));
        return success(bindRelateAcctUnionPayResponse);
    }

    @PostMapping("/BindRelateAccReUnionPay")
    public ApiResult BindRelateAccReUnionPay(@RequestBody BindRelateAccReUnionPayRequest request) throws Exception {
        BindRelateAccReUnionPayResponse bindRelateAccReUnionPayResponse = paBankService.bindRelateAccReUnionPay(request);
        logger.debug(JSON.toJSONString(bindRelateAccReUnionPayResponse));
        return success(bindRelateAccReUnionPayResponse);
    }

    @PostMapping("/BindRelateAcctSmallAmount")
    public ApiResult BindRelateAcctSmallAmount(@RequestBody BindRelateAcctSmallAmountRequest request) throws Exception {
        BindRelateAcctSmallAmountResponse bindRelateAcctSmallAmountResponse = paBankService.bindRelateAcctSmallAmount(request);
        logger.debug(JSON.toJSONString(bindRelateAcctSmallAmountResponse));
        return success(bindRelateAcctSmallAmountResponse);
    }

    @PostMapping("/CheckAmount")
    public ApiResult CheckAmount(@RequestBody CheckAmountRequest request) throws Exception {
        CheckAmountResponse checkAmountResponse = paBankService.checkAmount(request);
        logger.debug(JSON.toJSONString(checkAmountResponse));
        return success(checkAmountResponse);
    }

    @PostMapping("/MemberBindQuery")
    public ApiResult MemberBindQuery(@RequestBody MemberBindQueryRequest request) throws Exception {
        request.setFundSummaryAcctNo(fundSummaryAcctNo);
        MemberBindQueryResponse memberBindQueryResponse = paBankService.memberBindQuery(request);
        logger.debug(JSON.toJSONString(memberBindQueryResponse));

        if ("000000".equals(memberBindQueryResponse.getTxnReturnCode())) {
            return success(memberBindQueryResponse);
        } else {
            throw new AnyException(memberBindQueryResponse.getTxnReturnMsg());
        }
    }

    /**会员解绑提现账户(解绑银行卡)*/
    @PostMapping("/UnbindRelateAcct")
    public ApiResult UnbindRelateAcct(@RequestBody UnbindRelateAcctRequest request) throws Exception {

        AuthUser user = auth.getCurrentUser();

        request.setFunctionFlag("1");                                     //1解绑
        request.setFundSummaryAcctNo(fundSummaryAcctNo);                //总账号
        if(user.getUserType()==1) { //个人解绑
            request.setTranNetMemberCode(user.getUserId()); //用户代码
        }
        if(user.getUserType()==2) { //企业解绑
            request.setTranNetMemberCode(user.getUserCompanyId()); //企业代码
        }
        request.setSubAcctNo(user.getAccountSubNo());                    //子账号

        UnbindRelateAcctResponse unbindRelateAcctResponse = paBankService.unbindRelateAcct(request);
        logger.debug(JSON.toJSONString(unbindRelateAcctResponse));
        return success(unbindRelateAcctResponse);
    }

    @PostMapping("/MemberWithdrawCash")
    public ApiResult MemberWithdrawCash(@RequestBody MemberWithdrawCashRequest request) throws Exception {
        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());
        MemberWithdrawCashResponse memberWithdrawCashResponse = paBankService.memberWithdrawCash(request);
        logger.debug(JSON.toJSONString(memberWithdrawCashResponse));
        if ("000000".equals(memberWithdrawCashResponse.getTxnReturnCode())) {
            return success(memberWithdrawCashResponse);
        } else {
            throw new AnyException(memberWithdrawCashResponse.getTxnReturnMsg());
        }
    }

    @PostMapping("/MemberTransaction")
    public ApiResult MemberTransaction(@RequestBody MemberTransactionRequest request) throws Exception {
        MemberTransactionResponse memberTransactionResponse = paBankService.memberTransaction(request);
        logger.debug(JSON.toJSONString(memberTransactionResponse));
        return success(memberTransactionResponse);
    }

    @PostMapping("/RegisterBill")
    public ApiResult RegisterBill(@RequestBody RegisterBillRequest request) throws Exception {
        RegisterBillResponse registerBillResponse = paBankService.registerBill(request);
        logger.debug(JSON.toJSONString(registerBillResponse));
        return success(registerBillResponse);
    }

    @PostMapping("/SmallAmountTransferQuery")
    public ApiResult SmallAmountTransferQuery(@RequestBody SmallAmountTransferQueryRequest request) throws Exception {
        SmallAmountTransferQueryResponse smallAmountTransferQueryResponse = paBankService.smallAmountTransferQuery(request);
        logger.debug(JSON.toJSONString(smallAmountTransferQueryResponse));
        return success(smallAmountTransferQueryResponse);
    }

    @PostMapping("/BankClearQuery")
    public ApiResult BankClearQuery(@RequestBody BankClearQueryRequest request) throws Exception {
        BankClearQueryResponse bankClearQueryResponse = paBankService.bankClearQuery(request);
        logger.debug(JSON.toJSONString(bankClearQueryResponse));
        return success(bankClearQueryResponse);
    }

    @PostMapping("/CustAcctIdBalanceQuery")
    public ApiResult CustAcctIdBalanceQuery() throws Exception {
        CustAcctIdBalanceQueryRequest request = new CustAcctIdBalanceQueryRequest();
        CustAcctIdBalanceQueryResponse custAcctIdBalanceQueryResponse = paBankService.custAcctIdBalanceQuery(request);
        logger.debug(JSON.toJSONString(custAcctIdBalanceQueryResponse));
        return success(custAcctIdBalanceQueryResponse);
    }

    @PostMapping("/CustAcctIdHistoryBalanceQuery")
    public ApiResult CustAcctIdHistoryBalanceQuery(@RequestBody CustAcctIdHistoryBalanceQueryRequest request) throws Exception {
        CustAcctIdHistoryBalanceQueryResponse custAcctIdHistoryBalanceQueryResponse = paBankService.custAcctIdHistoryBalanceQuery(request);
        logger.debug(JSON.toJSONString(custAcctIdHistoryBalanceQueryResponse));
        return success(custAcctIdHistoryBalanceQueryResponse);
    }

    @PostMapping("/SingleTransactionStatusQuery")
    public ApiResult SingleTransactionStatusQuery(@RequestBody SingleTransactionStatusQueryRequest request) throws Exception {
        SingleTransactionStatusQueryResponse singleTransactionStatusQueryResponse = paBankService.singleTransactionStatusQuery(request);
        logger.debug(JSON.toJSONString(singleTransactionStatusQueryResponse));
        return success(singleTransactionStatusQueryResponse);
    }

    @PostMapping("/BankWithdrawCashBackQuery")
    public ApiResult BankWithdrawCashBackQuery(@RequestBody BankWithdrawCashBackQueryRequest request) throws Exception {
        BankWithdrawCashBackQueryResponse bankWithdrawCashBackQueryResponse = paBankService.bankWithdrawCashBackQuery(request);
        logger.debug(JSON.toJSONString(bankWithdrawCashBackQueryResponse));
        return success(bankWithdrawCashBackQueryResponse);
    }

    @PostMapping("/CommonTransferRechargeQuery")
    public ApiResult CommonTransferRechargeQuery(@RequestBody CommonTransferRechargeQueryRequest request) throws Exception {
        CommonTransferRechargeQueryResponse commonTransferRechargeQueryResponse = paBankService.commonTransferRechargeQuery(request);
        logger.debug(JSON.toJSONString(commonTransferRechargeQueryResponse));
        return success(commonTransferRechargeQueryResponse);
    }

    @PostMapping("/BankTransactionDetailsQuery")
    public ApiResult BankTransactionDetailsQuery(@RequestBody BankTransactionDetailsQueryRequest request) throws Exception {
        BankTransactionDetailsQueryResponse bankTransactionDetailsQueryResponse = paBankService.bankTransactionDetailsQuery(request);
        logger.debug(JSON.toJSONString(bankTransactionDetailsQueryResponse));
        return success(bankTransactionDetailsQueryResponse);
    }

    @PostMapping("/BankWithdrawCashDetailsQuery")
    public ApiResult BankWithdrawCashDetailsQuery(@RequestBody BankWithdrawCashDetailsQueryRequest request) throws Exception {
        BankWithdrawCashDetailsQueryResponse bankWithdrawCashDetailsQueryResponse = paBankService.bankWithdrawCashDetailsQuery(request);
        logger.debug(JSON.toJSONString(bankWithdrawCashDetailsQueryResponse));
        return success(bankWithdrawCashDetailsQueryResponse);
    }

    @PostMapping("/SupAcctIdBalanceQuery")
    public ApiResult SupAcctIdBalanceQuery(@RequestBody SupAcctIdBalanceQueryRequest request) throws Exception {
        SupAcctIdBalanceQueryResponse supAcctIdBalanceQueryResponse = paBankService.supAcctIdBalanceQuery(request);
        logger.debug(JSON.toJSONString(supAcctIdBalanceQueryResponse));
        return success(supAcctIdBalanceQueryResponse);
    }

    @PostMapping("/QueryCustAcctIdByThirdCustId")
    public ApiResult QueryCustAcctIdByThirdCustId(@RequestBody QueryCustAcctIdByThirdCustIdRequest request) throws Exception {
        QueryCustAcctIdByThirdCustIdResponse queryCustAcctIdByThirdCustIdResponse = paBankService.queryCustAcctIdByThirdCustId(request);
        logger.debug(JSON.toJSONString(queryCustAcctIdByThirdCustIdResponse));
        return success(queryCustAcctIdByThirdCustIdResponse);
    }

    @PostMapping("/ReconciliationDocumentQuery")
    public ApiResult ReconciliationDocumentQuery(@RequestBody ReconciliationDocumentQueryRequest request) throws Exception {
        ReconciliationDocumentQueryResponse reconciliationDocumentQueryResponse = paBankService.reconciliationDocumentQuery(request);
        logger.debug(JSON.toJSONString(reconciliationDocumentQueryResponse));
        return success(reconciliationDocumentQueryResponse);
    }

    @PostMapping("/MemberWithdrawSprtFeePwdVerify")
    public ApiResult MemberWithdrawSprtFeePwdVerify(@RequestBody MemberWithdrawSprtFeePwdVerifyRequest request) throws Exception {
        MemberWithdrawSprtFeePwdVerifyResponse memberWithdrawSprtFeePwdVerifyResponse = paBankService.memberWithdrawSprtFeePwdVerify(request);
        logger.debug(JSON.toJSONString(memberWithdrawSprtFeePwdVerifyResponse));
        return success(memberWithdrawSprtFeePwdVerifyResponse);
    }

    @PostMapping("/MemberTranVerifyTextMsgs")
    public ApiResult MemberTranVerifyTextMsgs(@RequestBody MemberTranVerifyTextMsgsRequest request) throws Exception {
        MemberTranVerifyTextMsgsResponse memberTranVerifyTextMsgsResponse = paBankService.memberTranVerifyTextMsgs(request);
        logger.debug(JSON.toJSONString(memberTranVerifyTextMsgsResponse));
        return success(memberTranVerifyTextMsgsResponse);
    }

    @PostMapping("/MemberTransactionsInspection")
    public ApiResult MemberTransactionsInspection(@RequestBody MemberTransactionsInspectionRequest request) throws Exception {
        MemberTransactionsInspectionResponse memberTransactionsInspectionResponse = paBankService.memberTransactionsInspection(request);
        logger.debug(JSON.toJSONString(memberTransactionsInspectionResponse));
        return success(memberTransactionsInspectionResponse);
    }

    @PostMapping("/PlatformOrderManagement")
    public ApiResult PlatformOrderManagement(@RequestBody PlatformOrderManagementRequest request) throws Exception {
        PlatformOrderManagementResponse platformOrderManagementResponse = paBankService.platformOrderManagement(request);
        logger.debug(JSON.toJSONString(platformOrderManagementResponse));
        return success(platformOrderManagementResponse);
    }

    //修改手机号【ModifyType:1短信验证 - 2银联鉴权】
    @PostMapping("/ApplyForChangeOfCellPhoneNum")
    public ApiResult ApplyForChangeOfCellPhoneNum(@RequestBody ApplyForChangeOfCellPhoneNumRequest request) throws Exception {

        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo()); //资金总账户

        ApplyForChangeOfCellPhoneNumResponse applyForChangeOfCellPhoneNumResponse = paBankService.applyForChangeOfCellPhoneNum(request);
        logger.debug(JSON.toJSONString(applyForChangeOfCellPhoneNumResponse));
        if ("000000".equals(applyForChangeOfCellPhoneNumResponse.getTxnReturnCode())) {
            return success(applyForChangeOfCellPhoneNumResponse);
        } else {
            throw new AnyException(applyForChangeOfCellPhoneNumResponse.getTxnReturnMsg());
        }
    }

    //修改手机号【】
    @PostMapping("/BackfillDynamicPassword")
    public ApiResult BackfillDynamicPassword(@RequestBody BackfillDynamicPasswordRequest request) throws Exception {
        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());

        BackfillDynamicPasswordResponse backfillDynamicPasswordResponse = paBankService.backfillDynamicPassword(request);
        logger.debug(JSON.toJSONString(backfillDynamicPasswordResponse));
        if ("000000".equals(backfillDynamicPasswordResponse.getTxnReturnCode())) {
            return success(backfillDynamicPasswordResponse);
        } else {
            throw new AnyException(backfillDynamicPasswordResponse.getTxnReturnMsg());
        }
    }

    @PostMapping("/ApplicationTextMsgDynamicCode")
    public ApiResult ApplicationTextMsgDynamicCode(@RequestBody ApplicationTextMsgDynamicCodeRequest request) throws Exception {
        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());
        ApplicationTextMsgDynamicCodeResponse applicationTextMsgDynamicCodeResponse = paBankService.applicationTextMsgDynamicCode(request);
        logger.debug(JSON.toJSONString(applicationTextMsgDynamicCodeResponse));
        if ("000000".equals(applicationTextMsgDynamicCodeResponse.getTxnReturnCode())) {
            return success(applicationTextMsgDynamicCodeResponse);
        } else {
            throw new AnyException(applicationTextMsgDynamicCodeResponse.getTxnReturnMsg());
        }
    }

    @PostMapping("/RiskAssess")
    public ApiResult RiskAssess(@RequestBody RiskAssessRequest request) throws Exception {
        RiskAssessResponse riskAssessResponse = paBankService.riskAssess(request);
        logger.debug(JSON.toJSONString(riskAssessResponse));
        return success(riskAssessResponse);
    }

    @PostMapping("/RiskAssessQuery")
    public ApiResult RiskAssessQuery(@RequestBody RiskAssessQueryRequest request) throws Exception {
        RiskAssessQueryResponse riskAssessQueryResponse = paBankService.riskAssessQuery(request);
        logger.debug(JSON.toJSONString(riskAssessQueryResponse));
        return success(riskAssessQueryResponse);
    }

    @PostMapping("/EntpClientOpenAcct")
    public ApiResult EntpClientOpenAcct(@RequestBody EntpClientOpenAcctRequest request) throws Exception {
        EntpClientOpenAcctResponse entpClientOpenAcctResponse = paBankService.entpClientOpenAcct(request);
        logger.debug(JSON.toJSONString(entpClientOpenAcctResponse));
        return success(entpClientOpenAcctResponse);
    }

    @PostMapping("/EntpOpenAcctResultQuery")
    public ApiResult EntpOpenAcctResultQuery(@RequestBody EntpOpenAcctResultQueryRequest request) throws Exception {
        EntpOpenAcctResultQueryResponse entpOpenAcctResultQueryResponse = paBankService.entpOpenAcctResultQuery(request);
        logger.debug(JSON.toJSONString(entpOpenAcctResultQueryResponse));
        return success(entpOpenAcctResultQueryResponse);
    }

    @PostMapping("/RedemptionModeQuery")
    public ApiResult RedemptionModeQuery(@RequestBody RedemptionModeQueryRequest request) throws Exception {
        RedemptionModeQueryResponse redemptionModeQueryResponse = paBankService.redemptionModeQuery(request);
        logger.debug(JSON.toJSONString(redemptionModeQueryResponse));
        return success(redemptionModeQueryResponse);
    }

    @PostMapping("/BatchNoQuery")
    public ApiResult BatchNoQuery(@RequestBody BatchNoQueryRequest request) throws Exception {
        BatchNoQueryResponse batchNoQueryResponse = paBankService.batchNoQuery(request);
        logger.debug(JSON.toJSONString(batchNoQueryResponse));
        return success(batchNoQueryResponse);
    }

    @PostMapping("/QueryTransactionByBatchNo")
    public ApiResult QueryTransactionByBatchNo(@RequestBody QueryTransactionByBatchNoRequest request) throws Exception {
        QueryTransactionByBatchNoResponse queryTransactionByBatchNoResponse = paBankService.queryTransactionByBatchNo(request);
        logger.debug(JSON.toJSONString(queryTransactionByBatchNoResponse));
        return success(queryTransactionByBatchNoResponse);
    }

    @PostMapping("/NewOpenChrematisticAcctId")
    public ApiResult NewOpenChrematisticAcctId(@RequestBody NewOpenChrematisticAcctIdRequest request) throws Exception {
        NewOpenChrematisticAcctIdResponse newOpenChrematisticAcctIdResponse = paBankService.newOpenChrematisticAcctId(request);
        logger.debug(JSON.toJSONString(newOpenChrematisticAcctIdResponse));
        return success(newOpenChrematisticAcctIdResponse);
    }

    @PostMapping("/NewBuyChrematisticProduct")
    public ApiResult NewBuyChrematisticProduct(@RequestBody NewBuyChrematisticProductRequest request) throws Exception {
        NewBuyChrematisticProductResponse newBuyChrematisticProductResponse = paBankService.newBuyChrematisticProduct(request);
        logger.debug(JSON.toJSONString(newBuyChrematisticProductResponse));
        return success(newBuyChrematisticProductResponse);
    }

    @PostMapping("/NewRedeemChrematisticProduct")
    public ApiResult NewRedeemChrematisticProduct(@RequestBody NewRedeemChrematisticProductRequest request) throws Exception {
        NewRedeemChrematisticProductResponse newRedeemChrematisticProductResponse = paBankService.newRedeemChrematisticProduct(request);
        logger.debug(JSON.toJSONString(newRedeemChrematisticProductResponse));
        return success(newRedeemChrematisticProductResponse);
    }
}
