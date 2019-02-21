package com.dllg.ebpay.service.pay.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dllg.common.CommonUtil;
import com.dllg.common.DlebException;
import com.dllg.common.HttpInteractionUtils;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.custom.AccountSubTradeCuMapper;
import com.dllg.ebpay.dao.custom.ApplicationTradeCuMapper;
import com.dllg.ebpay.dao.generator.*;
import com.dllg.ebpay.model.form.BusinessPayForm;
import com.dllg.ebpay.model.form.PayApplicationForm;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.model.response.ApiPaymentCallBackJsonResponse;
import com.dllg.ebpay.model.response.PayResponse;
import com.dllg.ebpay.service.account.UserAccountService;
import com.dllg.ebpay.service.company.CompanyService;
import com.dllg.ebpay.service.dict.DictService;
import com.dllg.ebpay.service.pay.PayService;
import com.dllg.ebpay.service.user.UserService;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.ecc.emp.data.KeyedCollection;
import com.sdb.payclient.core.PayclientInterfaceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PayServiceImpl implements PayService {
    private final static Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private EbpayProperties ebpayProperties;
    @Autowired
    private Auth auth;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private AccountSubMapper accountSubMapper;
    @Autowired
    private BusinessTradeMapper businessTradeMapper;
    @Autowired
    private AccountSubTradeMapper accountSubTradeMapper;
    @Autowired
    private HttpInteractionUtils httpInteractionUtils;
    @Autowired
    private RefundMapper refundMapper;
    @Autowired
    private AccountSubTradeCuMapper accountSubTradeCuMapper;
    @Autowired
    private ApplicationTradeCuMapper applicationTradeCuMapper;
    @Autowired
    private ApplicationTradeMapper applicationTradeMapper;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserAccountService userAccountService;


    @Override
    public PayResponse payMoney(String money, String fee, String remark) throws Exception {
        AuthUser currentUser = auth.getCurrentUser();
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubNoEqualTo(currentUser.getAccountSubNo());
        AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        String customerId = "";
        if (currentUser.getUserType() == 1) {
            User user = userService.selectByPrimaryKey(currentUser.getUserId());
            customerId = user.getUserCertificateNumber();
        } else if (currentUser.getUserType() == 2) {
            Company company = companyService.selectByPrimaryKey(currentUser.getUserCompanyId());
            if (company.getCompanyIsThreeInOne()) {
                customerId = company.getCompanyUnifiedSocialCreditLdentifier();
            } else {
                customerId = company.getCompanyLicenseNo();
            }
        }
        PayResponse payDate = getPayDate(money, accountSub.getAccountSubNo(), customerId, ebpayProperties.getReturnback(), fee);
        AccountSubTrade accountSubTrade = new AccountSubTrade();
        accountSubTrade.setAccountSubTradeId(CommonUtil.uuid());

        accountSubTrade.setAccountSubTradeTranType("01");
        accountSubTrade.setAccountSubTradeTranPaymentType(2);
        accountSubTrade.setAccountSubTradeCcy("RMB");
        accountSubTrade.setAccountSubTradeCreateTime(new Date());
        accountSubTrade.setAccountSubTradeRemark(remark);
        accountSubTrade.setAccountSubTradeTranDate(new Date());// '交易时间',
        accountSubTrade.setAccountSubTradeNo(payDate.getOrderId());
        accountSubTrade.setAccountSubTradeTranAmt(new BigDecimal(money));//交易金额（元
//                                accountSubTrade.setAccountSubTradeBankAccount(map.get("InAcctName")); // 账户名称（挂账）
//                                accountSubTrade.setAccountSubTradeBankNum(map.get("InAcctNo")); // '银行卡号(挂账)',

        //accountSubTrade.setAccountSubTradeAccountingDate();//会计日期
//                                accountSubTrade.setAccountSubTradeBankName(map.get("BankName"));// account_sub_trade_bank_name 银行名称（付款账户银行名称
//        accountSubTrade.setAccountSubTradeBankRemark(map.get("Remark"));//  `account_sub_trade_remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
        accountSubTrade.setAccountSubTradeAdjustTime(null);
        accountSubTrade.setAccountSubTradeAccountSubId("");
        accountSubTrade.setAccountSubTradeType(3);
        accountSubTrade.setAccountSubTradeTranStatus(6); // 交易状态
        accountSubTrade.setAccountSubTradeInAccountSubId(accountSub.getAccountSubId());
        accountSubTrade.setAccountSubTradeBankNo(payDate.getOrderId());
        accountSubTradeMapper.insertSelective(accountSubTrade);

        return payDate;
    }

    @Override
    public PayResponse payMoneyForBusiness(BusinessPayForm businessPayForm) throws Exception {
        //校验数据
        Map<String, String> validate = CommonUtil.validate(businessPayForm);
        if (!validate.isEmpty()) {
            throw new DlebException(validate.values().toString());
        }
        //判断订单是否已发起过支付
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(3);
        BusinessTradeExample businessTradeExample = new BusinessTradeExample();
        businessTradeExample.createCriteria().andBusinessTradeTrasactionNoEqualTo(businessPayForm.getBusinessTradeTrasactionNo()).andBusinessTradeBusinessFromEqualTo(businessPayForm.getBusinessTradeBusinessFrom()).andBusinessTradeStatusIn(integers);
        BusinessTrade businessTrade1 = businessTradeMapper.selectByExample(businessTradeExample).stream().findFirst().orElse(null);

        if (businessTrade1 == null) {
            AccountSubExample accountSubExample = new AccountSubExample();
            accountSubExample.createCriteria().andAccountSubNoEqualTo(businessPayForm.getAccountSubNo());
            AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
            PayResponse payDate = getPayDate(businessPayForm.getMoney(), businessPayForm.getAccountSubNo(), null, ebpayProperties.getReturnbackForBusiness(), businessPayForm.getFee());
            /**
             * 插入 AccountSubTrade  子账户流水记录表
             */
            AccountSubTrade accountSubTrade = new AccountSubTrade();
            accountSubTrade.setAccountSubTradeId(CommonUtil.uuid());
            accountSubTrade.setAccountSubTradeAccountSubId("");
            accountSubTrade.setAccountSubTradeInAccountSubId(accountSub.getAccountSubId());
            accountSubTrade.setAccountSubTradeNo("TX-LS" + new Date().getTime());
            accountSubTrade.setAccountSubTradeType(1);
            accountSubTrade.setAccountIsDeal("0");
            accountSubTrade.setAccountSubTradeTranDate(new Date());
            accountSubTrade.setAccountSubTradeFeatureIdentifier(6);  //account_sub_trade_feature_identifier
            accountSubTrade.setAccountSubTradeTranAmt(new BigDecimal(businessPayForm.getMoney()));//account_sub_trade_tran_amt
            accountSubTrade.setAccountSubTradeTranType("01");
            accountSubTrade.setAccountSubTradeTranStatus(-1);
            accountSubTrade.setAccountSubTradeTranPaymentType(1);
            accountSubTrade.setAccountSubTradeCcy("RMB");
            accountSubTrade.setAccountSubTradeTranFee(new BigDecimal(businessPayForm.getFee()));
            accountSubTrade.setAccountSubTradeRemark(businessPayForm.getRemark());
//        accountSubTrade.setAccountSubTradeCreateUserId();
            accountSubTrade.setAccountSubTradeCreateTime(new Date());
            accountSubTrade.setAccountSubTradeBankNo(payDate.getOrderId());

            /**
             * 插入 BusinessTrade 业务交易记录表
             */
            BusinessTrade businessTrade = new BusinessTrade();
            businessTrade.setBusinessTradeId(CommonUtil.uuid());
            businessTrade.setBusinessTradeAccountSubTradeId(accountSubTrade.getAccountSubTradeId());//business_trade_account_sub_trade_id
            businessTrade.setBusinessTradeBusinessNo(businessPayForm.getOrderId());  // business_trade_business_no
            businessTrade.setBusinessTradeBusinessType(businessPayForm.getBusinessTradeBusinessType());   //business_trade_business_type
            businessTrade.setBusinessTradeObject(businessPayForm.getBusinessTradeObject());
            businessTrade.setBusinessTradeBusinessFrom(businessPayForm.getBusinessTradeBusinessFrom());      //business_trade_business_from
//        businessTrade.setBusinessTradeBusinessContent(apiPaymentDisposeRequest.getBusinessContent());        //business_trade_business_content
//        businessTrade.setBusinessTradeRemark(apiPaymentDisposeRequest.getRemark());       //business_trade_remark
            businessTrade.setBusinessTradeStatus(1);      // business_trade_status
//        businessTrade.setBusinessTradePayUrl(ebpayProperties.getPaymentDisposeReturnUrl());        //business_trade_pay_url
            businessTrade.setBusinessTradeBackUrl(businessPayForm.getUrl());     //business_trade_back_url
//        businessTrade.setBusinessTradeCreateUserId(auth.getCurrentUser().getUserId());       //business_trade_create_user_id
            businessTrade.setBusinessTradeCreateTime(new Date());//business_trade_create_time
//        businessTrade.setBusinessTradeReservedMsg(apiPaymentDisposeRequest.getReservedMsg());  // business_trade_reserved_ms
//            businessTrade.setBusinessTradeBindRelationId(apiPaymentDisposeRequest.getRelationId());
            businessTrade.setBusinessTradeClearingType(businessPayForm.getBusinessTradeClearingType());
            businessTrade.setBusinessTradeGoodsName(businessPayForm.getBusinessTradeGoodsName());
            accountSubTradeMapper.insertSelective(accountSubTrade);
            businessTradeMapper.insertSelective(businessTrade);
            return payDate;
        } else {
            throw new DlebException("订单已提交，不能重复提交！");
        }


    }

    public PayResponse getPayDate(String money, String accountSubNo, String customerId, String notifyurl, String tranFee) throws Exception {
        PayclientInterfaceUtil util = new PayclientInterfaceUtil();
        com.ecc.emp.data.KeyedCollection input = new com.ecc.emp.data.KeyedCollection("input");
        com.ecc.emp.data.KeyedCollection output = new com.ecc.emp.data.KeyedCollection("output");
        com.ecc.emp.data.KeyedCollection signDataput = new com.ecc.emp.data.KeyedCollection("signDataput");

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = formatter.format(date);  //时间
        String datetamp = timestamp.substring(0, 8);  //日期

        input.put("masterId", ebpayProperties.getMasterId());
        //订单号，严格遵守格式：商户号+8位日期YYYYMMDD+8位流水
        String orderId = ebpayProperties.getMasterId() + datetamp + getOrderId();
        input.put("orderId", orderId);
        //币种，目前只支持RMB
        input.put("currency", "RMB");
        //订单金额，12整数，2小数
        input.put("amount", money);
        //下单时间，YYYYMMDDHHMMSS
        input.put("paydate", timestamp);
        //备注字段（商户自定）
        Map<String, Object> orderremark = new HashMap<>();
        orderremark.put("SFJOrdertype", "0");
        orderremark.put("remarktype", "SDS0100000");
        orderremark.put("plantCode", ebpayProperties.getMrchCode());
        List<Map<String, String>> orderList = new ArrayList<>();
        Map<String, String> order = new HashMap<>();
        order.put("SubAccNo", accountSubNo);
        order.put("PayModel", "1");
        order.put("TranFee", (tranFee == null || "".equals(tranFee)) ? "0" : tranFee);
        orderList.add(order);
        orderremark.put("oderlist", orderList);

        List<Map<String, Object>> maps = new ArrayList<>();
        maps.add(orderremark);
        String remark = JSONArray.toJSONString(maps);
        input.put("remark", remark);
        //订单款项描述（商户自定）
        input.put("objectName", "KHpaygate");
        //订单有效期(秒)，0不生效
        input.put("validtime", "0");

        //客户号
        if (!StringUtils.isEmpty(customerId)) {
            input.put("customerId", customerId);
        }
        //二级商户号
        input.put("subcstno", "");
//        KeyedCollection signDataput = new KeyedCollection("signDataput");
//        signDataput = util.getSignData(inputOrig);
//        //获取签名源数据
//        String  orig = (String) signDataput.getDataValue("orig");
//        //获取签名后数据
//        String  sign= (String) signDataput.getDataValue("sign");
//        orig = java.net.URLEncoder.encode(orig, "GBK");
////         orig = java.net.URLEncoder.encode(orig, encoding);
////        sign = java.net.URLEncoder.encode(sign, encoding);
//        sign = java.net.URLEncoder.encode(sign, "GBK");
        String orig = "";        //原始数据
        String origData = "";
        String sign = "";        //产生签名
        String signData = "";
        String encoding = "GBK";
        //发送前，得到签名数据和签名后数据，单独使用
        signDataput = util.getSignData(input);
        System.out.println("--2222----" + signDataput.toString());
        orig = (String) signDataput.getDataValue("orig");
        origData = orig.replace("\t", "");
        System.out.println(origData);
        sign = (String) signDataput.getDataValue("sign");
        signData = sign;
        orig = com.sdb.payclient.core.PayclientInterfaceUtil.Base64Encode(orig, encoding);
        sign = com.sdb.payclient.core.PayclientInterfaceUtil.Base64Encode(sign, encoding);
        orig = java.net.URLEncoder.encode(orig, encoding);
        // orig = java.net.URLEncoder.encode(orig, encoding);
        //sign = java.net.URLEncoder.encode(sign, encoding);
        sign = java.net.URLEncoder.encode(sign, encoding);
        PayResponse payResponse = new PayResponse();
        payResponse.setOrig(orig);
        payResponse.setSign(sign);
        payResponse.setReturnurl("dleb.qiatuchina.com");
        payResponse.setNotifyurl(notifyurl);
        payResponse.setAction(ebpayProperties.getTopupUrl());
        payResponse.setOrderId(orderId);
        return payResponse;
    }

    //生成8位随机数
    private static String getOrderId() {
        String orderId;
        java.util.Random r = new java.util.Random();
        while (true) {
            int i = r.nextInt(99999999);
            if (i < 0) i = -i;
            orderId = String.valueOf(i);
            System.out.println("---生成随机数---" + orderId);
            if (orderId.length() < 8) {
                System.out.println("---位数不够8位---" + orderId);
                continue;
            }
            if (orderId.length() >= 8) {
                orderId = orderId.substring(0, 8);
                System.out.println("---生成8位流水---" + orderId);
                break;
            }
        }
        return orderId;
    }


    //业务系统支付，银行回调
    public void returnbackToBusiness(String orderId, String status) {
        boolean flag = false;
        AccountSubTrade accountSubTrade = getAccountSubTradeByBankNo(orderId);
        logger.info("业务平台支付回调，流水号" + orderId + "状态：" + status);
        if (accountSubTrade != null) {
            BusinessTrade businessTrade = getBusinessTrade(accountSubTrade.getAccountSubTradeId());
            switch (status) {
                case "01":
                    accountSubTrade.setAccountSubTradeTranStatus(0);
                    businessTrade.setBusinessTradeStatus(3);
                    flag = true;
                    break;
                case "02":
                    accountSubTrade.setAccountSubTradeTranStatus(1);
                    businessTrade.setBusinessTradeStatus(4);
                    flag = true;
                    break;
            }

            if (flag) {
                accountSubTradeMapper.updateByPrimaryKeySelective(accountSubTrade);
                businessTradeMapper.updateByPrimaryKeySelective(businessTrade);
            }
            //通知业务系统
            Map<String, String> map = new HashMap<>();
            map.put("orderId", orderId);
            map.put("status", status);
            map.put("tradeBankNo", accountSubTrade.getAccountSubTradeBankNo());
            map.put("remark", accountSubTrade.getAccountSubTradeRemark());
            try {

                MappingJacksonValue value = new MappingJacksonValue(map);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
                HttpEntity<MappingJacksonValue> httpEntity = new HttpEntity<MappingJacksonValue>(value, httpHeaders);

                boolean isSend = true;
                int sendCount = 1;
                while (isSend && sendCount < 3) {
                    String post = httpInteractionUtils.post(businessTrade.getBusinessTradeBackUrl(), httpEntity, String.class, new HashMap<>());
                    sendCount++;
                    if ("success".equals(post.toLowerCase())) {
                        isSend = false;
                    }
                }


                businessTrade.setBusinessTradeBackJson(JSON.toJSONString(map));
                businessTradeMapper.updateByPrimaryKeySelective(businessTrade);
            } catch (Exception e) {
                e.printStackTrace();
                //通知发送失败记录状态
                businessTrade.setBusinessTradeBackJson(JSON.toJSONString(map) + "发送失败：" + e.getMessage());
                businessTradeMapper.updateByPrimaryKeySelective(businessTrade);
            }
        }
    }

    //e宝支付，银行回调
    public void returnback(String orderId, String status) {
        boolean flag = false;
        AccountSubTrade accountSubTrade = getAccountSubTradeByBankNo(orderId);
        logger.info("支付回调 流水号" + orderId + "状态：" + status);
        if (accountSubTrade != null) {
            switch (status) {
                case "01":
                    accountSubTrade.setAccountSubTradeTranStatus(0);
                    flag = true;
                    break;
                case "02":
                    accountSubTrade.setAccountSubTradeTranStatus(1);
                    flag = true;
                    break;
            }
            if (flag) {
                accountSubTradeMapper.updateByPrimaryKeySelective(accountSubTrade);
            }
        }
    }

    public BusinessTrade getBusinessTrade(String businessTradeAccountSubTradeId) {
        BusinessTradeExample businessTradeExample = new BusinessTradeExample();
        businessTradeExample.createCriteria().andBusinessTradeAccountSubTradeIdEqualTo(businessTradeAccountSubTradeId);
        BusinessTrade businessTrade = businessTradeMapper.selectByExample(businessTradeExample).stream().findFirst().orElse(null);
        return businessTrade;
    }

    public AccountSubTrade getAccountSubTradeById(String id) {
        AccountSubTradeExample accountSubTradeExample = new AccountSubTradeExample();
        accountSubTradeExample.createCriteria().andAccountSubTradeIdEqualTo(id);
        AccountSubTrade accountSubTrade = accountSubTradeMapper.selectByExample(accountSubTradeExample).stream().findFirst().orElse(null);
        return accountSubTrade;
    }

    public AccountSubTrade getAccountSubTradeByBankNo(String accountSubTradeBankNo) {
        AccountSubTradeExample accountSubTradeExample = new AccountSubTradeExample();
        accountSubTradeExample.createCriteria().andAccountSubTradeBankNoEqualTo(accountSubTradeBankNo);
        AccountSubTrade accountSubTrade = accountSubTradeMapper.selectByExample(accountSubTradeExample).stream().findFirst().orElse(null);
        return accountSubTrade;
    }

    public Map<String, String> getOrderStatus(String orderId) throws Exception {
        com.sdb.payclient.core.PayclientInterfaceUtil util = new com.sdb.payclient.core.PayclientInterfaceUtil();
        com.ecc.emp.data.KeyedCollection input = new com.ecc.emp.data.KeyedCollection("input");
        com.ecc.emp.data.KeyedCollection output = new com.ecc.emp.data.KeyedCollection("output");

        BusinessTradeExample businessTradeExample = new BusinessTradeExample();
        businessTradeExample.createCriteria().andBusinessTradeBusinessNoEqualTo(orderId);
        BusinessTrade businessTrade = businessTradeMapper.selectByExample(businessTradeExample).stream().findFirst().orElse(null);
        if (businessTrade != null) {
            AccountSubTradeExample accountSubTradeExample = new AccountSubTradeExample();
            accountSubTradeExample.createCriteria().andAccountSubTradeIdEqualTo(businessTrade.getBusinessTradeAccountSubTradeId());
            AccountSubTrade accountSubTrade = accountSubTradeMapper.selectByExample(accountSubTradeExample).stream().findFirst().orElse(null);
            if (accountSubTrade != null) {
                input.put("masterId", ebpayProperties.getMasterId());  //商户号，注意生产环境上要替换成商户自己的生产商户号
                input.put("orderId", accountSubTrade.getAccountSubTradeBankNo());  //订单号，严格遵守格式：商户号+8位日期YYYYMMDD+8位流水
                Map<String, String> map = new HashMap<>();
                try {
                    output = util.execute(input, "SF0008"); //执行发送（SF0008）单笔订单状态查询请求，并返回结果对象
                    String errorCode = (String) output.getDataValue("errorCode");
                    String errorMsg = (String) output.getDataValue("errorMsg");

                    System.out.println("---单笔订单支付状态查询结果详细信息---" + output);

                    if ((errorCode == null || errorCode.equals("")) && (errorMsg == null || errorMsg.equals(""))) {
                        System.out.println("---订单状态---" + output.getDataValue("status"));
                        System.out.println("---支付完成时间---" + output.getDataValue("date"));
                        System.out.println("---手续费金额---" + output.getDataValue("charge"));
                        System.out.println("---商户号---" + output.getDataValue("masterId"));
                        System.out.println("---订单号---" + output.getDataValue("orderId"));
                        System.out.println("---币种---" + output.getDataValue("currency"));
                        System.out.println("---订单金额---" + output.getDataValue("amount"));
                        System.out.println("---下单时间---" + output.getDataValue("paydate"));
                        System.out.println("---商品描述---" + output.getDataValue("objectName"));
                        System.out.println("---订单有效期---" + output.getDataValue("validtime"));
                        System.out.println("---备注---" + output.getDataValue("remark"));
                        System.out.println("---本金清算标志---" + output.getDataValue("settleflg"));  //1已清算，0待清算
                        System.out.println("---本金清算时间---" + output.getDataValue("settletime"));
                        System.out.println("---手续费清算标志---" + output.getDataValue("chargeflg"));  //1已清算，0待清算
                        System.out.println("---手续费清算时间---" + output.getDataValue("chargetime"));
                        System.out.println("---商户订单类型---" + output.getDataValue("orderType"));
                        map.put("status", output.getDataValue("status").toString());
                        map.put("orderId", orderId);
                        return map;
                    } else {
                        System.out.println("---错误码---" + output.getDataValue("errorCode"));
                        System.out.println("---错误说明---" + output.getDataValue("errorMsg"));
                        throw new DlebException(output.getDataValue("errorMsg").toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new DlebException(e.getMessage());
                }
            } else {
                throw new DlebException("订单不存在！");
            }
        } else {
            throw new DlebException("订单不存在！");
        }
    }

    public KeyedCollection parsing(String orig) throws Exception {
        PayclientInterfaceUtil util = new PayclientInterfaceUtil();
        orig = com.sdb.payclient.core.PayclientInterfaceUtil.Base64Decode(orig, "GBK");
        //KeyedCollection origKcoll = new KeyedCollection("output ");
        KeyedCollection output = util.parseOrigData(orig);
        return output;
    }

    public Map<String, String> reconciliation() throws Exception {
        com.sdb.payclient.core.PayclientInterfaceUtil util = new com.sdb.payclient.core.PayclientInterfaceUtil();
        com.ecc.emp.data.KeyedCollection input = new com.ecc.emp.data.KeyedCollection("input");
        com.ecc.emp.data.KeyedCollection output = new com.ecc.emp.data.KeyedCollection("output");

        input.put("masterId", "2000311146");  //商户号，注意生产环境上要替换成商户自己的生产商户号
        input.put("date", "20170512");  //对账日期，格式：YYYYMMDD
        System.out.println(input);
        try {
            output = util.execute(input, "SF0003"); //执行发送（SF0003）每日对账单查询请求，并返回结果对象
            String errorCode = (String) output.getDataValue("errorCode");
            String errorMsg = (String) output.getDataValue("errorMsg");
            System.out.println(output);
            System.out.println("---每日对账单查询结果详细信息---" + output);

            if ((errorCode == null || errorCode.equals("")) && (errorMsg == null || errorMsg.equals(""))) {
                System.out.println("---总金额---" + output.getDataValue("sumAmount"));
                System.out.println("---总笔数---" + output.getDataValue("sumCount"));
                com.ecc.emp.data.IndexedCollection orderList = (com.ecc.emp.data.IndexedCollection) output.getDataElement("iOrderListDetail");
                for (int i = 0; i < orderList.size(); i++) {
                    com.ecc.emp.data.KeyedCollection orderDetail = (com.ecc.emp.data.KeyedCollection) orderList.getElementAt(i);
                    System.out.print("---订单状态---" + orderDetail.getDataValue("status"));
                    System.out.print("---支付完成时间---" + orderDetail.getDataValue("date"));
                    System.out.print("---手续费金额---" + orderDetail.getDataValue("charge"));
                    System.out.print("---商户号---" + orderDetail.getDataValue("masterId"));
                    System.out.print("---订单号---" + orderDetail.getDataValue("orderId"));
                    System.out.print("---币种---" + orderDetail.getDataValue("currency"));
                    System.out.print("---订单金额---" + orderDetail.getDataValue("amount"));
                    System.out.print("---下单时间---" + orderDetail.getDataValue("paydate"));
                    System.out.print("---商品描述---" + orderDetail.getDataValue("objectName"));
                    System.out.print("---订单有效期---" + orderDetail.getDataValue("validtime"));
                    System.out.print("---备注---" + orderDetail.getDataValue("remark"));
                    System.out.print("---本金清算标志---" + orderDetail.getDataValue("settleflg"));  //1已清算，0待清算
                    System.out.print("---本金清算时间---" + orderDetail.getDataValue("settletime"));
                    System.out.print("---手续费清算标志---" + orderDetail.getDataValue("chargeflg"));  //1已清算，0待清算
                    System.out.println("---手续费清算时间---" + orderDetail.getDataValue("chargetime"));
                }
            } else {
                System.out.println("---错误码---" + output.getDataValue("errorCode"));
                System.out.println("---错误说明---" + output.getDataValue("errorMsg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sf0005(Refund refund, AccountSubTrade accountSubTrade) throws Exception {
        com.sdb.payclient.core.PayclientInterfaceUtil util = new com.sdb.payclient.core.PayclientInterfaceUtil();
        com.ecc.emp.data.KeyedCollection input = new com.ecc.emp.data.KeyedCollection("input");
        com.ecc.emp.data.KeyedCollection output = new com.ecc.emp.data.KeyedCollection("output");
        AccountSub inAccount = accountSubMapper.selectByPrimaryKey(accountSubTrade.getAccountSubTradeInAccountSubId());
        int orderid;
        String orderids;
        java.util.Random r = new java.util.Random();
        while (true) {
            orderid = r.nextInt(99999999);
            if (orderid < 0) orderid = -orderid;
            orderids = String.valueOf(orderid);
            if (orderids.length() < 8) {
                continue;
            }
            if (orderids.length() >= 8) {
                orderids = orderids.substring(0, 8);
                break;
            }
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = formatter.format(date);
        String datetamp = timestamp.substring(0, 8);

        //备注字段（商户自定）
        Map<String, Object> orderremark = new HashMap<>();
        orderremark.put("SFJOrdertype", "0");
        orderremark.put("remarktype", "SDT0100000");
        orderremark.put("plantCode", ebpayProperties.getMrchCode());
        List<Map<String, String>> orderList = new ArrayList<>();
        Map<String, String> order = new HashMap<>();
        order.put("SubAccNo", inAccount.getAccountSubNo());
        order.put("refundModel", "1");
        order.put("RefundTranFee", "0");
        orderList.add(order);
        orderremark.put("oderlist", orderList);

        List<Map<String, Object>> maps = new ArrayList<>();
        maps.add(orderremark);
        String remark = JSONArray.toJSONString(maps);
        String no = ebpayProperties.getMasterId() + datetamp + orderids;//退款单号

        input.put("masterId", ebpayProperties.getMasterId());  //商户号，注意生产环境上要替换成商户自己的生产商户号
        input.put("refundNo", no);  //退款单号，严格遵守格式：商户号+8位日期YYYYMMDD+8位流水
        input.put("orderId", accountSubTrade.getAccountSubTradeBankNo());  //原订单号，严格遵守格式：商户号+8位日期YYYYMMDD+8位流水
        input.put("currency", "RMB");  //币种，目前只支持RMB
        input.put("refundAmt", refund.getRefundFee());  //退款金额，12整数，2小数，必须等于原订单金额
        input.put("remark", remark);  //备注字段（商户自定）
        input.put("objectName", "");  //退款原因（商户自定）
        input.put("NOTIFYURL", ebpayProperties.getReimburseBackUrl());  //商户接收订单退款结果服务器异步通知的url，支付平台通知银行，则银行通知商户，否则不通知
        System.out.println(input);
        try {
            output = util.execute(input, "SF0005"); //执行发送（SF0005）单笔订单退款请求，并返回结果对象
            String errorCode = (String) output.getDataValue("errorCode");
            String errorMsg = (String) output.getDataValue("errorMsg");
            System.out.println(output);
            System.out.println("---单笔订单退款结果详细信息---" + output);

            if ((errorCode == null || errorCode.equals("")) && (errorMsg == null || errorMsg.equals(""))) {
                System.out.println("---订单状态---" + output.getDataValue("status"));  //01：接收成功，退款是否成功请以异步通知结果为准；02：失败
                System.out.println("---商户号---" + output.getDataValue("masterId"));
                System.out.println("---退款单号---" + output.getDataValue("refundAmt"));
                System.out.println("---原订单号---" + output.getDataValue("orderId"));
                System.out.println("---币种---" + output.getDataValue("currency"));
                System.out.println("---退款金额---" + output.getDataValue("refundAmt"));
                refund.setRefundBankNo(no);
                if ("01".equals(output.getDataValue("status"))) {
                    refund.setRefundStatus("7");
                } else {
                    refund.setRefundStatus("6");
                }
                refundMapper.updateByPrimaryKeySelective(refund);
            } else {
                System.out.println("---错误码---" + output.getDataValue("errorCode"));
                System.out.println("---错误说明---" + output.getDataValue("errorMsg"));
                refund.setRefundBankNo(no);
                refund.setRefundStatus("6");
                refund.setRefundFalseReason(output.getDataValue("errorMsg").toString());
                refundMapper.updateByPrimaryKeySelective(refund);
                throw new DlebException(output.getDataValue("errorMsg").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sf0006() throws Exception {
        com.sdb.payclient.core.PayclientInterfaceUtil util = new com.sdb.payclient.core.PayclientInterfaceUtil();
        com.ecc.emp.data.KeyedCollection input = new com.ecc.emp.data.KeyedCollection("input");
        com.ecc.emp.data.KeyedCollection output = new com.ecc.emp.data.KeyedCollection("output");

        input.put("masterId", ebpayProperties.getMasterId());  //商户号，注意生产环境上要替换成商户自己的生产商户号
        input.put("beginDate", "20180613000000");  //查询开始时间（退款请求接收时间）YYYYMMDDHHMMSS
        input.put("endDate", "20180615000000");  //查询结束时间（退款请求接收时间）YYYYMMDDHHMMSS
        System.out.println(input);
        try {
            output = util.execute(input, "SF0006"); //执行发送（SF0006）订单退款查询请求，并返回结果对象
            String errorCode = (String) output.getDataValue("errorCode");
            String errorMsg = (String) output.getDataValue("errorMsg");
            System.out.println(output);
            System.out.println("---订单退款查询结果详细信息---" + output);

            if ((errorCode == null || errorCode.equals("")) && (errorMsg == null || errorMsg.equals(""))) {
                System.out.println("---总金额---" + output.getDataValue("sumAmount"));
                System.out.println("---总笔数---" + output.getDataValue("sumCount"));
                com.ecc.emp.data.IndexedCollection orderList = (com.ecc.emp.data.IndexedCollection) output.getDataElement("iOrderListDetail");
                for (int i = 0; i < orderList.size(); i++) {
                    com.ecc.emp.data.KeyedCollection orderDetail = (com.ecc.emp.data.KeyedCollection) orderList.getElementAt(i);
                    System.out.print("---商户号---" + orderDetail.getDataValue("masterId"));
                    System.out.print("---退款单号---" + orderDetail.getDataValue("refundNo"));
                    System.out.print("---原订单号---" + orderDetail.getDataValue("orderId"));
                    System.out.print("---退款请求接收时间---" + orderDetail.getDataValue("refundTime"));
                    System.out.print("---币种---" + orderDetail.getDataValue("currency"));
                    System.out.print("---退款金额---" + orderDetail.getDataValue("refundAmt"));
                    System.out.print("---退款状态---" + orderDetail.getDataValue("refundStatus"));  //01成功；02失败；00处理中
                    System.out.print("---退款本金扣收标志---" + orderDetail.getDataValue("settleflg"));  //0：未扣款；1：已扣款；2：扣款中
                    System.out.print("---退款失败本金退还标志---" + orderDetail.getDataValue("refundFlag"));  //0：未退款；1：已退款；2：处理中
                    System.out.print("---错误码---" + orderDetail.getDataValue("errorCode"));  //1已清算，0待清算
                    System.out.println("---错误说明---" + orderDetail.getDataValue("errorMsg"));
                }
            } else {
                System.out.println("---错误码---" + output.getDataValue("errorCode"));
                System.out.println("---错误说明---" + output.getDataValue("errorMsg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Map<String, Object> sf0009(String orderNo) throws Exception {
        com.sdb.payclient.core.PayclientInterfaceUtil util = new com.sdb.payclient.core.PayclientInterfaceUtil();
        com.ecc.emp.data.KeyedCollection input = new com.ecc.emp.data.KeyedCollection("input");
        com.ecc.emp.data.KeyedCollection output = new com.ecc.emp.data.KeyedCollection("output");

        input.put("masterId", ebpayProperties.getMasterId());  //商户号，注意生产环境上要替换成商户自己的生产商户号
        input.put("refundNo", orderNo);  //退款单号，严格遵守格式：商户号+8位日期YYYYMMDD+8位流水
        System.out.println(input);
        Map<String, Object> map = new HashMap<>();
        try {
            output = util.execute(input, "SF0009"); //执行发送（SF0009）单笔退款状态查询请求，并返回结果对象
            String errorCode = (String) output.getDataValue("errorCode");
            String errorMsg = (String) output.getDataValue("errorMsg");
            System.out.println("---单笔退款状态查询查询结果详细信息---" + output);
            if ((errorCode == null || errorCode.equals("")) && (errorMsg == null || errorMsg.equals(""))) {
                System.out.println("---商户号---" + output.getDataValue("masterId"));
                System.out.println("---退款单号---" + output.getDataValue("refundNo"));
                System.out.println("---退款请求接收时间---" + output.getDataValue("refundTime"));
                System.out.println("---原订单号---" + output.getDataValue("orderId"));
                System.out.println("---币种---" + output.getDataValue("currency"));
                System.out.println("---本金清算标志---" + output.getDataValue("settleflg")); //退款本金扣收标志：0：未扣款；1：已扣款；2：扣款中
                System.out.println("---手续费清算标志---" + output.getDataValue("refundFlag"));//退款失败本金退还标志：0：未退款；1：已退款；2：处理中
                System.out.println("---下单时间---" + output.getDataValue("refundAmt"));
                System.out.println("---商品描述---" + output.getDataValue("refundStatus"));//退款状态：01成功；02失败；00处理中
                map.put("refundStatus", output.getDataValue("refundStatus"));
            } else {
                System.out.println("---错误码---" + output.getDataValue("errorCode"));
                System.out.println("---错误说明---" + output.getDataValue("errorMsg"));
                map.put("errorMsg", output.getDataValue("errorCode"));
                map.put("errorMsg", output.getDataValue("errorMsg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public void disposeUnfinishedTrade() throws Exception {
        logger.info("开始同步支付状态");
        List<AccountSubTrade> unfinishedTrades = accountSubTradeCuMapper.getUnfinishedTrade();
        if (unfinishedTrades != null && unfinishedTrades.size() > 0) {
            for (AccountSubTrade accountSubTrade : unfinishedTrades) {
                logger.info("开始同步支付状态" + accountSubTrade.getAccountSubTradeBankNo());
                com.sdb.payclient.core.PayclientInterfaceUtil util = new com.sdb.payclient.core.PayclientInterfaceUtil();
                com.ecc.emp.data.KeyedCollection input = new com.ecc.emp.data.KeyedCollection("input");
                com.ecc.emp.data.KeyedCollection output = new com.ecc.emp.data.KeyedCollection("output");
                input.put("masterId", ebpayProperties.getMasterId());  //商户号，注意生产环境上要替换成商户自己的生产商户号
                input.put("orderId", accountSubTrade.getAccountSubTradeBankNo());  //订单号，严格遵守格式：商户号+8位日期YYYYMMDD+8位流水
                try {
                    output = util.execute(input, "SF0008"); //执行发送（SF0008）单笔订单状态查询请求，并返回结果对象
                    String errorCode = (String) output.getDataValue("errorCode");
                    String errorMsg = (String) output.getDataValue("errorMsg");
                    logger.info("---单笔订单支付状态查询结果详细信息---" + output);
                    if ((errorCode == null || errorCode.equals("")) && (errorMsg == null || errorMsg.equals(""))) {
                        String status = output.getDataValue("status").toString();
                        //更新单据状态
                        if ("01".equals(status)) {
                            accountSubTrade.setAccountSubTradeTranStatus(0);
                            accountSubTradeMapper.updateByPrimaryKeySelective(accountSubTrade);
                        }
                    } else {
                        System.out.println("---错误码---" + output.getDataValue("errorCode"));
                        System.out.println("---错误说明---" + output.getDataValue("errorMsg"));
                        accountSubTrade.setAccountSubTradeTranStatus(1);
                        accountSubTradeMapper.updateByPrimaryKeySelective(accountSubTrade);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("同步支付状态失败" + e);
                }
            }
        }
    }

    @Override
    public List<Map<String, String>> getPayApplicationList(PayApplicationForm payApplicationForm) {
        String relationId = "";
        AuthUser authUser = auth.getCurrentUser();
        if(authUser.getUserType() == 1){
            relationId = authUser.getUserId();
        }else if (authUser.getUserType() ==  2){
            relationId = authUser.getUserCompanyId();
        }
        payApplicationForm.setRelationId(relationId);
        return applicationTradeCuMapper.getPayApplicationList(payApplicationForm);
    }

    @Override
    public ApplicationTrade getPayApplicationDetail(String applicationTradeId) {
        return applicationTradeMapper.selectByPrimaryKey(applicationTradeId);
    }

    @Override
    public void payApplicationAudit(Map<String, String> map) {
        AuthUser authUser = auth.getCurrentUser();
        String refundId = map.get("applicationTradeId");
        String type = map.get("type"); // 审核类型
        Integer isPass = Integer.valueOf(map.get("isPass"));
        ApplicationTrade applicationTrade = applicationTradeMapper.selectByPrimaryKey(refundId);
        if (applicationTrade == null) {
            throw new AnyException("参数错误，请稍后再试");
        }
        if (!applicationTrade.getApplicationTradeApplicationStatus().toString().equals(type)) {
            throw new AnyException("单据状态已发生变化，请刷新后再次尝试");
        }
        applicationTrade.setApplicationTradeReason(map.get("reason"));
        applicationTrade.setApplicationTradeUpdateTime(new Date());
        applicationTrade.setApplicationTradeUpdateUserId(authUser.getUserId());
        if (0 == isPass) {
            applicationTrade.setApplicationTradeApplicationStatus(5);
        } else {
            if (applicationTrade.getApplicationTradeApplicationStatus() == 0) {
                applicationTrade.setApplicationTradeApplicationStatus(1);
            } else if (1 == applicationTrade.getApplicationTradeApplicationStatus()) {
                List<Dict> list = dictService.getDictByType("付款申请额度字典");
                if (list == null || list.size() != 1) {
                    throw new AnyException("付款申请额度字典设定有误");
                }
                BigDecimal decimal = new BigDecimal(list.get(0).getDictValue());
                if (applicationTrade.getApplicationTradeTranAmt().compareTo(decimal) >= 1) {
                    applicationTrade.setApplicationTradeApplicationStatus(2);
                } else {
                    applicationTrade.setApplicationTradeApplicationStatus(3);
                }
            } else if (2 == applicationTrade.getApplicationTradeApplicationStatus()) {
                applicationTrade.setApplicationTradeApplicationStatus(3);
            }
        }
        applicationTradeMapper.updateByPrimaryKeySelective(applicationTrade);
        send(applicationTrade);

    }

    @Override
    public String payApplicationPay(Map<String, String> map) {
        AuthUser authUser = auth.getCurrentUser();
        String refundId = map.get("applicationTradeId");
        String type = map.get("type"); // 审核类型
        ApplicationTrade applicationTrade = applicationTradeMapper.selectByPrimaryKey(refundId);
        if (applicationTrade == null) {
            throw new AnyException("参数错误，请稍后再试");
        }
        if (!applicationTrade.getApplicationTradeApplicationStatus().toString().equals(type)) {
            throw new AnyException("单据状态已发生变化，请刷新后再次尝试");
        }
        if (applicationTrade.getApplicationTradeApplicationStatus() != 3 && applicationTrade.getApplicationTradeApplicationStatus() != 6 ) {
            throw new AnyException("当前单据状态不可支付，请刷新后再次尝试");
        }


        Map<String, BigDecimal> balanceByAccountSub = null;
        try {
            balanceByAccountSub = userAccountService.getBalanceByAccountSubId(applicationTrade.getApplicationTradeAccountSubId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AnyException("获取余额出错");
        }
        if (balanceByAccountSub == null) {
            throw new AnyException("获取余额出错");
        }
        BigDecimal balance = balanceByAccountSub.get("acctAvailBal");
        if (balance.compareTo(applicationTrade.getApplicationTradeTranAmt()) < 0) {
            throw new AnyException("账户余额不足");
        }

        /**
         * 插入 AccountSubTrade  子账户流水记录表
         */
        AccountSubTrade accountSubTrade = new AccountSubTrade();
        accountSubTrade.setAccountSubTradeId(CommonUtil.uuid());
        accountSubTrade.setAccountSubTradeAccountSubId(applicationTrade.getApplicationTradeAccountSubId());
        accountSubTrade.setAccountSubTradeInAccountSubId(applicationTrade.getApplicationTradeInAccountSubId());
        accountSubTrade.setAccountSubTradeNo("TX-LS" + new Date().getTime());
        accountSubTrade.setAccountSubTradeType(1);
        accountSubTrade.setAccountIsDeal("1");
        accountSubTrade.setAccountSubTradeFeatureIdentifier(6);  //account_sub_trade_feature_identifier
        accountSubTrade.setAccountSubTradeTranAmt(applicationTrade.getApplicationTradeTranAmt());//account_sub_trade_tran_amt
        accountSubTrade.setAccountSubTradeTranType("01");
        accountSubTrade.setAccountSubTradeTranStatus(-1);
        accountSubTrade.setAccountSubTradeTranPaymentType(1);
        accountSubTrade.setAccountSubTradeCcy("RMB");
        accountSubTrade.setAccountSubTradeCreateUserId(applicationTrade.getApplicationTradeCreateUserId());
        accountSubTrade.setAccountSubTradeCreateTime(new Date());
        accountSubTrade.setAccountSubTradePayUserId(authUser.getUserId());
        accountSubTrade.setAccountSubTradePayUserName(authUser.getUserName());

        /**
         * 插入 BusinessTrade 业务交易记录表
         */
        BusinessTrade businessTrade = new BusinessTrade();
        businessTrade.setBusinessTradeId(CommonUtil.uuid());
        businessTrade.setBusinessTradeAccountSubTradeId(accountSubTrade.getAccountSubTradeId());//business_trade_account_sub_trade_id
        businessTrade.setBusinessTradeBusinessNo(applicationTrade.getApplicationTradeBusinessNo());  // business_trade_business_no
        businessTrade.setBusinessTradeBusinessType(applicationTrade.getApplicationTradeBusinessType());   //business_trade_business_type

        businessTrade.setBusinessTradeBusinessFrom(applicationTrade.getApplicationTradeTrasactionFrom());      //business_trade_business_from
        businessTrade.setBusinessTradeBusinessContent(applicationTrade.getApplicationTradeBusinessContent());        //business_trade_business_content
        businessTrade.setBusinessTradeRemark(applicationTrade.getApplicationTradeRemark());       //business_trade_remark
        businessTrade.setBusinessTradeStatus(1);      // business_trade_status
        businessTrade.setBusinessTradePayUrl(ebpayProperties.getPaymentDisposeReturnUrl());        //business_trade_pay_url
        businessTrade.setBusinessTradeBackUrl(applicationTrade.getApplicationTradeBackUrl());     //business_trade_back_url
        businessTrade.setBusinessTradeCreateUserId(applicationTrade.getApplicationTradeCreateUserId());       //business_trade_create_user_id
        businessTrade.setBusinessTradeCreateTime(new Date());//business_trade_create_time
        businessTrade.setBusinessTradeReservedMsg(applicationTrade.getApplicationTradeReservedMsg());  // business_trade_reserved_ms
        businessTrade.setBusinessTradeBindRelationId(applicationTrade.getApplicationTradeBindRelationId());
        businessTrade.setBusinessTradeClearingType(applicationTrade.getApplicationTradeClearingType());
        businessTrade.setBusinessTradeGoodsName(applicationTrade.getApplicationTradeGoodsName());

        applicationTrade.setApplicationTradeBusinessTradeId(businessTrade.getBusinessTradeId());
        applicationTrade.setApplicationTradeAccountSubTradeId(accountSubTrade.getAccountSubTradeId());
        accountSubTradeMapper.insertSelective(accountSubTrade);
        businessTradeMapper.insertSelective(businessTrade);
        applicationTradeMapper.updateByPrimaryKeySelective(applicationTrade);

        return businessTrade.getBusinessTradeId();

    }

    @Override
    public List<String> getObjecSource() {
        String relationId = "";
        AuthUser authUser = auth.getCurrentUser();
        if(authUser.getUserType() == 1){
            relationId = authUser.getUserId();
        }else if (authUser.getUserType() ==  2){
            relationId = authUser.getUserCompanyId();
        }
        return applicationTradeCuMapper.getObjecSource(relationId);
    }

    //回调对接平台
    public void send(ApplicationTrade applicationTrade) {
        try {
            ApiPaymentCallBackJsonResponse apiPaymentCallBackJsonResponse = new ApiPaymentCallBackJsonResponse();
            apiPaymentCallBackJsonResponse.setApplicationNo(applicationTrade.getApplicationTradeApplicationNo());
            apiPaymentCallBackJsonResponse.setProposer(applicationTrade.getApplicationTradeProposer());
            apiPaymentCallBackJsonResponse.setApplicationStatus(applicationTrade.getApplicationTradeApplicationStatus().toString());
            apiPaymentCallBackJsonResponse.setRejectReason(applicationTrade.getApplicationTradeReason());
            apiPaymentCallBackJsonResponse.setPayType("在线交易");

            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("msg", "成功");
            map.put("data", apiPaymentCallBackJsonResponse);

            MappingJacksonValue value = new MappingJacksonValue(map);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<MappingJacksonValue> httpEntity = new HttpEntity<MappingJacksonValue>(value, httpHeaders);
            httpInteractionUtils.post(applicationTrade.getApplicationTradeBackUrl(), httpEntity, String.class, new HashMap<>());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AnyException("通知状态失败");
        }
    }

    public static void main(String[] args) {
        Map map = new HashMap<String ,String>();
        map.put("aaaa","111");
        map.put("bbbb","2222");
        System.out.println(JSON.toJSONString(map));

    }
}
