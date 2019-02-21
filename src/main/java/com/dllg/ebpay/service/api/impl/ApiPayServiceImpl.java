package com.dllg.ebpay.service.api.impl;

import com.alibaba.fastjson.JSON;
import com.dllg.common.CommonUtil;
import com.dllg.common.Constants;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.custom.AccountSubCuMapper;
import com.dllg.ebpay.dao.generator.*;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.model.request.ApiPaymentApplicationDisposeRequest;
import com.dllg.ebpay.model.request.ApiPaymentDisposeRequest;
import com.dllg.ebpay.model.request.ApiQueryDataResquest;
import com.dllg.ebpay.model.response.ApiPaymentCallBackJsonResponse;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.ebpay.service.api.ApiPayService;
import com.dllg.ebpay.service.api.RegistService;
import com.dllg.ebpay.service.msgCode.MsgCodeService;
import com.dllg.ebpay.service.pay.AccountSubTradeService;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.ApiResult;
import com.dllg.pabank.model.request.MemberTransactionsInspectionRequest;
import com.dllg.pabank.model.response.MemberTransactionsInspectionResponse;
import com.dllg.pabank.service.PABankService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 15:04 2018/4/2
 */
@Service
@Transactional
public class ApiPayServiceImpl implements ApiPayService {

    private static final Logger logger = LogManager.getLogger(ApiPayServiceImpl.class);

    @Autowired
    private AccountSubMapper accountSubMapper;
    @Autowired
    private EbpayProperties ebpayProperties;
    @Autowired
    private BusinessTradeMapper businessTradeMapper;
    @Autowired
    private AccountSubTradeMapper accountSubTradeMapper;
    @Autowired
    private AccountSubTradeService accountSubTradeService;

    @Autowired
    private AccountSubBindMapper accountSubBindMapper;
    @Autowired
    private AccountSubCuMapper accountSubCuMapper;
    @Autowired
    private PABankService paBankService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RegistService registService;
    @Autowired
    private ApplicationTradeMapper applicationTradeMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private MsgCodeService msgCodeService;
    @Autowired
    private AccountSubService accountSubService;


    @Override
    public String paymentDispose(ApiPaymentDisposeRequest apiPaymentDisposeRequest) {
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubNoEqualTo(apiPaymentDisposeRequest.getAccountSubNo()).andAccountSubIsDeleteEqualTo(false);
        AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        AccountSubBindExample accountSubBindExample = new AccountSubBindExample();
        accountSubBindExample.createCriteria().andAccountSubBindAccountSubIdEqualTo(accountSub.getAccountSubId()).andAccountSubBindStatusEqualTo(1);
        List<AccountSubBind> accountSubBindList = accountSubBindMapper.selectByExample(accountSubBindExample);
        if (accountSubBindList == null || accountSubBindList.size() == 0) {
            throw new AnyException("关联主账户有误");
        }
        List<String> stringList = accountSubBindList.stream().map(as -> as.getAccountSubBindRelationId()).collect(Collectors.toList());
        if (accountSub == null) {
            throw new AnyException("主账号填写有误");
        }
        if (!stringList.contains(apiPaymentDisposeRequest.getRelationId())) {
            throw new AnyException("关联主账户有误");
        }
        BigDecimal bigDecimal = apiPaymentDisposeRequest.getTranAmt();

        if (!(bigDecimal.compareTo(BigDecimal.ZERO) == 1)) {
            throw new AnyException("交易金额必须大于0");
        }
        accountSubExample.clear();
        accountSubExample.createCriteria().andAccountSubNoEqualTo(apiPaymentDisposeRequest.getInAccountSubNo()).andAccountSubIsDeleteEqualTo(false);
        AccountSub inAccountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        if (inAccountSub == null) {
            throw new AnyException("交易账号填写有误");
        }

        /**
         * 插入 AccountSubTrade  子账户流水记录表
         */
        AccountSubTrade accountSubTrade = new AccountSubTrade();
        accountSubTrade.setAccountSubTradeId(CommonUtil.uuid());
        accountSubTrade.setAccountSubTradeAccountSubId(accountSub.getAccountSubId());
        accountSubTrade.setAccountSubTradeInAccountSubId(inAccountSub.getAccountSubId());
        accountSubTrade.setAccountSubTradeNo("TX-LS" + new Date().getTime());
        accountSubTrade.setAccountSubTradeType(1);
        accountSubTrade.setAccountIsDeal("1");
        accountSubTrade.setAccountSubTradeFeatureIdentifier(6);  //account_sub_trade_feature_identifier
        accountSubTrade.setAccountSubTradeTranAmt(bigDecimal);//account_sub_trade_tran_amt
        accountSubTrade.setAccountSubTradeTranType("01");
        accountSubTrade.setAccountSubTradeTranStatus(-1);
        accountSubTrade.setAccountSubTradeTranPaymentType(1);
        accountSubTrade.setAccountSubTradeCcy("RMB");
        accountSubTrade.setAccountSubTradeCreateUserId(apiPaymentDisposeRequest.getCreaterUserId());
        accountSubTrade.setAccountSubTradeCreateTime(new Date());

        /**
         * 插入 BusinessTrade 业务交易记录表
         */
        BusinessTrade businessTrade = new BusinessTrade();
        businessTrade.setBusinessTradeId(CommonUtil.uuid());
        businessTrade.setBusinessTradeAccountSubTradeId(accountSubTrade.getAccountSubTradeId());//business_trade_account_sub_trade_id
        businessTrade.setBusinessTradeBusinessNo(apiPaymentDisposeRequest.getBusinessNo());  // business_trade_business_no
        businessTrade.setBusinessTradeBusinessType(apiPaymentDisposeRequest.getBusinessType());   //business_trade_business_type

        businessTrade.setBusinessTradeBusinessFrom(apiPaymentDisposeRequest.getBusinessFrom());      //business_trade_business_from
        businessTrade.setBusinessTradeBusinessContent(apiPaymentDisposeRequest.getBusinessContent());        //business_trade_business_content
        businessTrade.setBusinessTradeRemark(apiPaymentDisposeRequest.getRemark());       //business_trade_remark
        businessTrade.setBusinessTradeStatus(1);      // business_trade_status
        businessTrade.setBusinessTradePayUrl(ebpayProperties.getPaymentDisposeReturnUrl());        //business_trade_pay_url
        businessTrade.setBusinessTradeBackUrl(apiPaymentDisposeRequest.getBackUrl());     //business_trade_back_url
        businessTrade.setBusinessTradeCreateUserId(apiPaymentDisposeRequest.getCreaterUserId());       //business_trade_create_user_id
        businessTrade.setBusinessTradeCreateTime(new Date());//business_trade_create_time
        businessTrade.setBusinessTradeReservedMsg(apiPaymentDisposeRequest.getReservedMsg());  // business_trade_reserved_ms
        businessTrade.setBusinessTradeBindRelationId(apiPaymentDisposeRequest.getRelationId());
        businessTrade.setBusinessTradeClearingType(apiPaymentDisposeRequest.getBusinessTradeClearingType());
        businessTrade.setBusinessTradeGoodsName(apiPaymentDisposeRequest.getBusinessTradeGoodsName());
        accountSubTradeMapper.insertSelective(accountSubTrade);
        businessTradeMapper.insertSelective(businessTrade);

        return ebpayProperties.getPaymentDisposeReturnUrl() + "?id=" + businessTrade.getBusinessTradeId();
    }

    @Override
    public void paymentApplicationDispose(ApiPaymentApplicationDisposeRequest apiPaymentApplicationDisposeRequest) {
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubNoEqualTo(apiPaymentApplicationDisposeRequest.getAccountSubNo()).andAccountSubIsDeleteEqualTo(false);
        AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        AccountSubBindExample accountSubBindExample = new AccountSubBindExample();
        accountSubBindExample.createCriteria().andAccountSubBindAccountSubIdEqualTo(accountSub.getAccountSubId()).andAccountSubBindStatusEqualTo(1);
        List<AccountSubBind> accountSubBindList = accountSubBindMapper.selectByExample(accountSubBindExample);
        if (accountSubBindList == null || accountSubBindList.size() == 0) {
            throw new AnyException("关联主账户有误");
        }
        List<String> stringList = accountSubBindList.stream().map(as -> as.getAccountSubBindRelationId()).collect(Collectors.toList());
        if (accountSub == null) {
            throw new AnyException("主账号填写有误");
        }
        if (!stringList.contains(apiPaymentApplicationDisposeRequest.getRelationId())) {
            throw new AnyException("关联主账户有误");
        }
        BigDecimal bigDecimal = apiPaymentApplicationDisposeRequest.getTranAmt();

        if (!(bigDecimal.compareTo(BigDecimal.ZERO) == 1)) {
            throw new AnyException("交易金额必须大于0");
        }
        accountSubExample.clear();
        accountSubExample.createCriteria().andAccountSubNoEqualTo(apiPaymentApplicationDisposeRequest.getInAccountSubNo()).andAccountSubIsDeleteEqualTo(false);
        AccountSub inAccountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        if (inAccountSub == null) {
            throw new AnyException("交易账号填写有误");
        }
        Account account = accountMapper.selectByPrimaryKey(accountSub.getAccountSubAccountId());
        ApplicationTrade applicationTrade = new ApplicationTrade();
        applicationTrade.setApplicationTradeId(CommonUtil.uuid());
        applicationTrade.setApplicationTradeAccountSubId(accountSub.getAccountSubId());
        applicationTrade.setApplicationTradeAccountSubNo(accountSub.getAccountSubNo());
        applicationTrade.setApplicationTradeAccountTypeName(account.getAccountType() == 1 ? "平安银行":account.getAccountType().toString());
        applicationTrade.setApplicationTradeAccountSubTradeId("");
        applicationTrade.setApplicationTradeBusinessTradeId("");
        applicationTrade.setApplicationTradeBusinessContent(apiPaymentApplicationDisposeRequest.getBusinessContent());
        applicationTrade.setApplicationTradeTrasactionFrom(apiPaymentApplicationDisposeRequest.getBusinessFrom());
        applicationTrade.setApplicationTradeApplicationNo(apiPaymentApplicationDisposeRequest.getApplicationNo());
        applicationTrade.setApplicationTradeBackUrl(apiPaymentApplicationDisposeRequest.getBackUrl());
        applicationTrade.setApplicationTradeBindRelationId(apiPaymentApplicationDisposeRequest.getRelationId());
        applicationTrade.setApplicationTradeBusinessNo(apiPaymentApplicationDisposeRequest.getBusinessNo());
        applicationTrade.setApplicationTradeBusinessType(apiPaymentApplicationDisposeRequest.getBusinessType());
        applicationTrade.setApplicationTradeClearingType(apiPaymentApplicationDisposeRequest.getBusinessTradeClearingType());
        applicationTrade.setApplicationTradeCreateTime(new Date());
        applicationTrade.setApplicationTradeCreateUserId(apiPaymentApplicationDisposeRequest.getCreaterUserId());
        applicationTrade.setApplicationTradeGoodsName(apiPaymentApplicationDisposeRequest.getBusinessTradeGoodsName());
        applicationTrade.setApplicationTradeInAccountSubId(inAccountSub.getAccountSubId());
        applicationTrade.setApplicationTradeIsDelete(false);
        applicationTrade.setApplicationTradeTrasactionObject(inAccountSub.getAccountSubName());
        applicationTrade.setApplicationTradeProposer(apiPaymentApplicationDisposeRequest.getProposer());
        applicationTrade.setApplicationTradeRemark(apiPaymentApplicationDisposeRequest.getRemark());
        applicationTrade.setApplicationTradeReservedMsg(apiPaymentApplicationDisposeRequest.getReservedMsg());
        applicationTrade.setApplicationTradeApplicationStatus(0);
        applicationTrade.setApplicationTradeTranAmt(apiPaymentApplicationDisposeRequest.getTranAmt());
        applicationTrade.setApplicationTradeTrasactionNo("");

        applicationTradeMapper.insertSelective(applicationTrade);

    }

    @Override
    public void paymentCallBack(String orderid, String flag, String sign) {
        AccountSubTrade order = accountSubTradeService.getOrder(orderid);
        logger.info("平安业务回调数据 orderid:{},flag:{},subtradetype:{}", order, flag, order.getAccountSubTradeType());
        //  进行验证信息
        if (order == null) {
            logger.info("转账验密回调已完成，单据被删除");
            return;
        }
        if (order.getAccountSubTradeTranStatus() != -1) {
            logger.info("转账验密回调已完成，单据已被修改");
            return;
        }
        BusinessTradeExample businessTradeExample = new BusinessTradeExample();
        businessTradeExample.createCriteria().andBusinessTradeAccountSubTradeIdEqualTo(order.getAccountSubTradeId());
        BusinessTrade businessTrade = businessTradeMapper.selectByExample(businessTradeExample).stream().findFirst().orElse(null);
        ApplicationTradeExample applicationTradeExample = new ApplicationTradeExample();
        applicationTradeExample.createCriteria().andApplicationTradeBusinessTradeIdEqualTo(businessTrade.getBusinessTradeId()).
                andApplicationTradeAccountSubTradeIdEqualTo(order.getAccountSubTradeId()).
                andApplicationTradeIsDeleteEqualTo(false);
        ApplicationTrade applicationTrade = applicationTradeMapper.selectByExample(applicationTradeExample).stream().findFirst().orElse(null);

        if (StringUtils.isBlank(flag) || !"1".equals(flag)) {
            logger.info("平安业务回调删除单据");
            // 删除掉刚才创建的单据
            order.setAccountSubTradeTranStatus(1);
            order.setAccountSubTradeIsDelete(true);
            accountSubTradeMapper.deleteByPrimaryKey(order.getAccountSubTradeId());

            businessTrade.setBusinessTradeStatus(4);
            businessTrade.setBusinessTradeIsDelete(true);
            businessTradeMapper.deleteByPrimaryKey(businessTrade.getBusinessTradeId());

            if (applicationTrade != null) {
                applicationTrade.setApplicationTradeApplicationStatus(6);
                applicationTradeMapper.updateByPrimaryKeySelective(applicationTrade);
            }
            return;
        }


        //设置交易状态为处理中
        logger.info("平安业务回调更新状态");
        businessTrade.setBusinessTradeStatus(2);
        businessTradeMapper.updateByPrimaryKeySelective(businessTrade);
        order.setAccountSubTradeTranStatus(6);
        accountSubTradeMapper.updateByPrimaryKeySelective(order);
        // 业务端发起的支付交易 都为会员交易
        if (order.getAccountSubTradeType() == 1) {


        /*    UserAccountInfoDto outAccount = accountSubCuMapper.getAccountById(order.getAccountSubTradeAccountSubId());
            UserAccountInfoDto inAccount = accountSubCuMapper.getAccountById(order.getAccountSubTradeInAccountSubId());

            MemberTransactionsInspectionRequest send = new MemberTransactionsInspectionRequest();
            send.setFunctionFlag(order.getAccountSubTradeFeatureIdentifier().toString());
            send.setOutSubAcctNo(outAccount.getSubAcctNo());
            send.setFundSummaryAcctNo(outAccount.getFundSummaryAcctNo());
            send.setOutMemberCode(outAccount.getUserId());
            send.setOutSubAcctName(outAccount.getUserAccountName());
            send.setInSubAcctNo(inAccount.getSubAcctNo());
            send.setInMemberCode(inAccount.getUserId());
            send.setInSubAcctName(inAccount.getUserAccountName());
            send.setTranAmt(order.getAccountSubTradeTranAmt().toString());
            send.setTranFee(order.getAccountSubTradeTranFee().toString());
            send.setCcy(order.getAccountSubTradeCcy());
            send.setOrderNo(order.getAccountSubTradeNo());*/
            MemberTransactionsInspectionRequest send = registService.getMemberTransactionsInspectionRequest(order, businessTrade);
            send.setWebSign(sign);
            try {
                Class aClass = send.getClass();
                Field[] fields = aClass.getDeclaredFields();
                String orig = "";
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object o = field.get(send);
                    if (o != null && !o.toString().equals("")) {
                        orig += o.toString() + "&";
                    }
                }
                System.out.println(orig.substring(0, orig.length() - 1));
            } catch (Exception e) {
                e.printStackTrace();
                throw new AnyException("转换Orig失败");
            }
            try {
                MemberTransactionsInspectionResponse response = paBankService.memberTransactionsInspection(send);
                order.setAccountSubTradeTranDate(new Date());
                logger.info("平安业务回调请求response：{}", response);
                if (response == null) {
                    throw new AnyException("请求异常");
                }
                if (!response.getTxnReturnCode().equals(Constants.PA_TRADE_SUCCESS_CODE)) {
                    logger.error("转账请求被拒：{}  {}", response.getTxnReturnCode(), response.getTxnReturnMsg());
                    // 请求失败
                    order.setAccountSubTradeTranStatus(1); //交易状态，（0：成功，1：失败，2：待确认, 5：待处理，6：处理中）',
                    accountSubTradeMapper.updateByPrimaryKeySelective(order);

                    businessTrade.setBusinessTradeStatus(4); //'状态，1-创建，2-交易中，3-交易成功，4-交易失败，5-交易撤销',
                    setData(businessTrade, order, -1, applicationTrade);
                    businessTradeMapper.updateByPrimaryKeySelective(businessTrade);
                    if (applicationTrade == null) { // 不是支付申请的单据
                        postForObjectExt(businessTrade.getBusinessTradeBackUrl(), businessTrade.getBusinessTradeBackJson());
                    } else { // 为支付申请单据，支付失败不通知第三方平台
                        applicationTrade.setApplicationTradeApplicationStatus(6);
                        applicationTradeMapper.updateByPrimaryKeySelective(applicationTrade);
                    }
                    throw new AnyException("转账失败");
                }

                order.setAccountSubTradeBankNo(response.getCnsmrSeqNo());
                order.setAccountSubTradeTranStatus(0);
                accountSubTradeMapper.updateByPrimaryKeySelective(order);

                businessTrade.setBusinessTradeStatus(3);
                setData(businessTrade, order, 1, applicationTrade);
                businessTradeMapper.updateByPrimaryKeySelective(businessTrade);
                if (applicationTrade != null) {
                    applicationTrade.setApplicationTradeApplicationStatus(4);
                    applicationTradeMapper.updateByPrimaryKeySelective(applicationTrade);
                }
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        if (BooleanUtils.isTrue(order.getAccountSubTradeIsSendSms())) {
                            String phone = accountSubService.getPhone(order.getAccountSubTradeInAccountSubId());
                            if (StringUtils.isNotBlank(phone)) {
                                msgCodeService.sendMsg(phone, getSMSContent(order.getAccountSubTradeTranAmt()));
                            }
                        }
                    }
                };
                thread.start();
                postForObjectExt(businessTrade.getBusinessTradeBackUrl(), businessTrade.getBusinessTradeBackJson());

            } catch (AnyException e) {
                logger.error(e);
            } catch (Exception e) {
                logger.error("转账请求失败：" + e);
                order.setAccountSubTradeTranStatus(1);
                businessTrade.setBusinessTradeStatus(4);
                setData(businessTrade, order, -1, applicationTrade);
                businessTradeMapper.updateByPrimaryKeySelective(businessTrade);
                accountSubTradeMapper.updateByPrimaryKeySelective(order);
                if (applicationTrade == null) { // 不是支付申请的单据
                    postForObjectExt(businessTrade.getBusinessTradeBackUrl(), businessTrade.getBusinessTradeBackJson());
                } else { // 为支付申请单据，支付失败不通知第三方平台
                    applicationTrade.setApplicationTradeApplicationStatus(6);
                    applicationTradeMapper.updateByPrimaryKeySelective(applicationTrade);
                }

            }
        }
    }

    private String getSMSContent(BigDecimal accountSubTradeTranAmt) {
        return "收到转账" + accountSubTradeTranAmt + "元";
    }

    @Override
    public ApiPaymentCallBackJsonResponse queryData(ApiQueryDataResquest apiQueryDataResquest) {
        BusinessTrade businessTrade = businessTradeMapper.selectByPrimaryKey(apiQueryDataResquest.getTradeId());
        if (businessTrade == null) {
            throw new AnyException("交易id有误");
        }
        if (!businessTrade.getBusinessTradeBindRelationId().equals(apiQueryDataResquest.getRelationId())) {
            throw new AnyException("关联主账户有误");
        }
        return JSON.parseObject(businessTrade.getBusinessTradeBackJson(), ApiPaymentCallBackJsonResponse.class);
    }

    /**
     * 设置返回json
     *
     * @param businessTrade
     * @param info
     */
    private void setData(BusinessTrade businessTrade, AccountSubTrade accountSubTrade, int info, ApplicationTrade applicationTrade) {
        ApiPaymentCallBackJsonResponse apiPaymentCallBackJsonResponse = new ApiPaymentCallBackJsonResponse();

        apiPaymentCallBackJsonResponse.setTradeBankName(accountSubTrade.getAccountSubTradeBankName());
        apiPaymentCallBackJsonResponse.setTradeBankNo(accountSubTrade.getAccountSubTradeBankNo());
        apiPaymentCallBackJsonResponse.setTradeFeatureIdentifier("直接支付");
        apiPaymentCallBackJsonResponse.setTradeNo(accountSubTrade.getAccountSubTradeNo());  // 流水号
        apiPaymentCallBackJsonResponse.setTradeTranAmt(accountSubTrade.getAccountSubTradeTranAmt());
        apiPaymentCallBackJsonResponse.setTradeTranDate(accountSubTrade.getAccountSubTradeTranDate());
        apiPaymentCallBackJsonResponse.setTradeTranFee(accountSubTrade.getAccountSubTradeTranFee());
        apiPaymentCallBackJsonResponse.setTradeTranPaymentType("转出");
        apiPaymentCallBackJsonResponse.setTradeTranType(accountSubTrade.getAccountSubTradeTranType());
        apiPaymentCallBackJsonResponse.setTradeType("会员交易");

        //apiPaymentCallBackJsonResponse.setRelationId();
        //apiPaymentCallBackJsonResponse.setAccountSubNo();
        // apiPaymentCallBackJsonResponse.setInAccountSubNo();
        apiPaymentCallBackJsonResponse.setCreateUserId(businessTrade.getBusinessTradeCreateUserId());
        apiPaymentCallBackJsonResponse.setBusinessTradeId(businessTrade.getBusinessTradeId());
        apiPaymentCallBackJsonResponse.setBusinessContent(businessTrade.getBusinessTradeBusinessContent());
        apiPaymentCallBackJsonResponse.setBusinessFrom(businessTrade.getBusinessTradeBusinessFrom());
        apiPaymentCallBackJsonResponse.setBusinessNo(businessTrade.getBusinessTradeBusinessNo());
        apiPaymentCallBackJsonResponse.setBusinessType(businessTrade.getBusinessTradeBusinessType());
        apiPaymentCallBackJsonResponse.setReservedMsg(businessTrade.getBusinessTradeReservedMsg());
        apiPaymentCallBackJsonResponse.setRemark(businessTrade.getBusinessTradeRemark());

        ApiResult apiResult = new ApiResult();
        if (info == 1) {
            apiResult.setCode(1);
            apiResult.setMsg("成功");
            apiPaymentCallBackJsonResponse.setStatus("成功");
            if (applicationTrade != null) {
                apiPaymentCallBackJsonResponse.setApplicationStatus("4");
                apiPaymentCallBackJsonResponse.setProposer(applicationTrade.getApplicationTradeProposer());
                apiPaymentCallBackJsonResponse.setPayUserName(accountSubTrade.getAccountSubTradePayUserName());
                apiPaymentCallBackJsonResponse.setApplicationNo(applicationTrade.getApplicationTradeApplicationNo());
                apiPaymentCallBackJsonResponse.setPayType("在线交易");
            }
        } else {
            apiResult.setCode(-1);
            apiResult.setMsg("失败");
            apiPaymentCallBackJsonResponse.setStatus("失败");
        }
        apiResult.setData(apiPaymentCallBackJsonResponse);
        businessTrade.setBusinessTradeBackJson(JSON.toJSONString(apiResult));

    }


    /**
     * 将发送的post请求中的参数转为utf-8编码
     *
     * @param url
     * @param param
     * @return
     */
    private void postForObjectExt(String url, String param) {
        try {
            logger.info("平安业务回调发送平台回调请求，param ---> {}", param);
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            HttpEntity<String> formEntity = new HttpEntity<String>(param, headers);
            String result = restTemplate.postForObject(url, formEntity, String.class);
            logger.info("平台返回回调信息，result ---> {}", result);
        } catch (Exception e) {
            logger.error("发送平台回调信息出错", e);
        }

    }


}
