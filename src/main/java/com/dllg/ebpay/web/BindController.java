package com.dllg.ebpay.web;


import com.alibaba.fastjson.JSON;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.service.bind.BindService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.ApiResult;
import com.dllg.pabank.model.request.BindRelateAccReUnionPayRequest;
import com.dllg.pabank.model.request.BindRelateAcctSmallAmountRequest;
import com.dllg.pabank.model.request.BindRelateAcctUnionPayRequest;
import com.dllg.pabank.model.request.CheckAmountRequest;
import com.dllg.pabank.model.response.BindRelateAccReUnionPayResponse;
import com.dllg.pabank.model.response.BindRelateAcctSmallAmountResponse;
import com.dllg.pabank.model.response.BindRelateAcctUnionPayResponse;
import com.dllg.pabank.model.response.CheckAmountResponse;
import com.dllg.pabank.service.PABankService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 绑定银行卡
 */
@RestController
@RequestMapping("/bind")
public class BindController extends BaseController {

    private final static Logger logger = LogManager.getLogger(BindController.class);

    @Autowired
    private BindService bindService;
    @Autowired
    private PABankService paBankService;
    @Autowired
    private EbpayProperties ebpayProperties;

    @GetMapping("/province")
    public ApiResult province() {
        List<Area> list = bindService.selectProvince();
        return success(list);
    }

    @PostMapping("/city")
    public ApiResult city(@RequestBody Area area) {
        List<Area> list = bindService.selectCity(area);
        return success(list);
    }

    @PostMapping("/district")
    public ApiResult district(@RequestBody Area area) {
        List<Area> list = bindService.selectDistrict(area);
        return success(list);
    }

    @GetMapping("/superBank")
    public ApiResult superBank() {
        List<SuperInternetBank> list = bindService.selectSuperInternetBank();
        return success(list);
    }

    @PostMapping("/bank")
    public ApiResult bank(@RequestBody PubPayCity pubPayCity) {
        List<ZjjzCnapsBankinfo> list = bindService.selectZjjzCnapsBankinfo(pubPayCity);
        return success(list);
    }

    BindRelateAcctUnionPayRequest bindRelateAcctUnionPayRequest = new BindRelateAcctUnionPayRequest();
    BindRelateAcctSmallAmountRequest bindRelateAcctSmallAmountRequest = new BindRelateAcctSmallAmountRequest();

    @PostMapping("/BindRelateAcctUnionPay")
    public ApiResult BindRelateAcctUnionPay(@RequestBody BindRelateAcctUnionPayRequest request) throws Exception {
        bindRelateAcctUnionPayRequest = request;
        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());
        BindRelateAcctUnionPayResponse bindRelateAcctUnionPayResponse = paBankService.bindRelateAcctUnionPay(request);
        if ("000000".equals(bindRelateAcctUnionPayResponse.getTxnReturnCode())) {
            return success(bindRelateAcctUnionPayResponse);
        } else {
            throw new AnyException(bindRelateAcctUnionPayResponse.getTxnReturnMsg());
        }
    }

    @PostMapping("/BindRelateAccReUnionPay")
    public ApiResult BindRelateAccReUnionPay(@RequestBody BindRelateAccReUnionPayRequest request) throws Exception {
        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());
        BindRelateAccReUnionPayResponse bindRelateAccReUnionPayResponse = paBankService.bindRelateAccReUnionPay(request);
        logger.debug(JSON.toJSONString(bindRelateAccReUnionPayResponse));
        if ("000000".equals(bindRelateAccReUnionPayResponse.getTxnReturnCode())) {
            BankCard bankCard = new BankCard();
            bankCard.setBankCardId(bindRelateAcctUnionPayRequest.getCnapsBranchId());
//            bankCard.setBankCardAccountId(bindRelateAcctUnionPayRequest.getTranNetMemberCode());
//            bankCard.setBankCardNumber(bindRelateAcctUnionPayRequest.getMemberAcctNo());
//            bankCard.setBankCardCertificateType(bindRelateAcctUnionPayRequest.getMemberGlobalType());
//            bankCard.setBankCardCertificateNumber(bindRelateAcctUnionPayRequest.getMemberGlobalId());
//            bankCard.setBankCardBankType(bindRelateAcctUnionPayRequest.getBankType());
//            bankCard.setBankCardPhone(bindRelateAcctUnionPayRequest.getMobile());
//            bankCard.setBankCardName(bindRelateAcctUnionPayRequest.getAcctOpenBranchName());
//            bankCard.setBankCardCnapsBranchId(bindRelateAcctUnionPayRequest.getCnapsBranchId());
//            bankCard.setBankCardCreateTime(new Date());
//            bankCard.setBankCardCreateUserId(bindRelateAcctUnionPayRequest.getMemberName());
//            bankCard.setBankCardIsVerify(true);
            bindService.insertBankCard(bankCard);
            return success(bindRelateAccReUnionPayResponse);
        } else {
            throw new AnyException(bindRelateAccReUnionPayResponse.getTxnReturnMsg());
        }
    }

    @PostMapping("/BindRelateAcctSmallAmount")
    public ApiResult BindRelateAcctSmallAmount(@RequestBody BindRelateAcctSmallAmountRequest request) throws Exception {
        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());
        bindRelateAcctSmallAmountRequest = request;
        BindRelateAcctSmallAmountResponse bindRelateAcctSmallAmountResponse = paBankService.bindRelateAcctSmallAmount(request);
        logger.debug(JSON.toJSONString(bindRelateAcctSmallAmountResponse));
        if ("000000".equals(bindRelateAcctSmallAmountResponse.getTxnReturnCode())) {
            return success(bindRelateAcctSmallAmountResponse);
        } else {
            throw new AnyException(bindRelateAcctSmallAmountResponse.getTxnReturnMsg());
        }
    }

    @PostMapping("/CheckAmount")
    public ApiResult CheckAmount(@RequestBody CheckAmountRequest request) throws Exception {
        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());
        CheckAmountResponse checkAmountResponse = paBankService.checkAmount(request);
        logger.debug(JSON.toJSONString(checkAmountResponse));
        if ("000000".equals(checkAmountResponse.getTxnReturnCode())) {
            BankCard bankCard = new BankCard();
            bankCard.setBankCardId(bindRelateAcctSmallAmountRequest.getCnapsBranchId());
//            bankCard.setBankCardAccountId(bindRelateAcctSmallAmountRequest.getTranNetMemberCode());
//            bankCard.setBankCardNumber(bindRelateAcctSmallAmountRequest.getMemberAcctNo());
//            bankCard.setBankCardCertificateType(bindRelateAcctSmallAmountRequest.getMemberGlobalType());
//            bankCard.setBankCardCertificateNumber(bindRelateAcctSmallAmountRequest.getMemberGlobalId());
//            bankCard.setBankCardBankType(bindRelateAcctSmallAmountRequest.getBankType());
//            bankCard.setBankCardPhone(bindRelateAcctSmallAmountRequest.getMobile());
//            bankCard.setBankCardName(bindRelateAcctSmallAmountRequest.getAcctOpenBranchName());
//            bankCard.setBankCardCnapsBranchId(bindRelateAcctSmallAmountRequest.getCnapsBranchId());
//            bankCard.setBankCardCreateTime(new Date());
//            bankCard.setBankCardCreateUserId(bindRelateAcctSmallAmountRequest.getMemberName());
//            bankCard.setBankCardIsVerify(true);
            bindService.insertBankCard(bankCard);
            return success(checkAmountResponse);
        } else {
            throw new AnyException(checkAmountResponse.getTxnReturnMsg());
        }
    }

}
