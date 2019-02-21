package com.dllg.ebpay.service.accountSub.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dllg.common.DlebException;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.custom.AccountSubCuMapper;
import com.dllg.ebpay.dao.custom.AccountSubTradeCuMapper;
import com.dllg.ebpay.dao.generator.*;
import com.dllg.ebpay.model.dto.AccountSubDto;
import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.ebpay.service.pay.AccountSubTradeService;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.pabank.model.request.CustAcctIdBalanceQueryRequest;
import com.dllg.pabank.model.request.SupAcctIdBalanceQueryRequest;
import com.dllg.pabank.model.response.CustAcctIdBalanceQueryResponse;
import com.dllg.pabank.model.response.CustAcctIdBalanceQuerySubResponse;
import com.dllg.pabank.model.response.SupAcctIdBalanceQueryResponse;
import com.dllg.pabank.service.PABankService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountSubServiceImpl implements AccountSubService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountSubCuMapper accountSubCuMapper;
    @Autowired
    private AccountSubMapper accountSubMapper;
    @Autowired
    private PABankService paBankService;
    @Autowired
    private EbpayProperties ebpayProperties;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Auth auth;
    @Autowired
    private AccountSubTradeService accountSubTradeService;
    @Autowired
    private BankCardMapper bankCardMapper;
    @Autowired
    private AccountSubTradeCuMapper accountSubTradeCuMapper;
    @Autowired
    private BusinessTradeMapper businessTradeMapper;
    @Autowired
    private AccountSubTradeMapper accountSubTradeMapper;

    @Override
    public List<AccountSubDto> getAccountSubs() {
        AuthUser authUser = auth.getCurrentUser();
        String relationId = "";
        if (authUser.getUserType().equals(2)) {
            relationId = authUser.getUserCompanyId();
        } else {
            relationId = authUser.getUserId();
        }
        List<AccountSubDto> accountSubs = accountSubCuMapper.getAccountSub(relationId);
        return accountSubs;

    }

    @Override
    public PaymentCodeForm paymentCodeManage(String orderId, String accountSubNo, String type, Object orig, String returnurl, String notifyUrl, String mobile) {
        String name = "";
        String thirdCustId = "";
        String idType = "";
        String idNo = "";
//        String mobile = "";
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubNoEqualTo(accountSubNo);
        AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        if (accountSub == null) {
            throw new AnyException("子账户不存在！");
        }
        if (accountSub.getAccountSubRelationIdType() == 1) {
            User user = userMapper.selectByPrimaryKey(accountSub.getAccountSubRelationId());
            if (user == null) {
                throw new AnyException("用户不存在！");
            }
            thirdCustId = user.getUserId();
            name = user.getUserCnName();
            idType = user.getUserCertificateType();
            idNo = user.getUserCertificateNumber();
            if (mobile == null || "".equals(mobile)) {
                mobile = user.getUserMobile();
            }

        } else {
            Company company = companyMapper.selectByPrimaryKey(accountSub.getAccountSubRelationId());
            if (company == null) {
                throw new AnyException("企业不存在！");
            }
            thirdCustId = company.getCompanyId();
            name = company.getCompanyName();
            if (company.getCompanyIsThreeInOne()) {
                idType = "5";
                idNo = company.getCompanyUnifiedSocialCreditLdentifier();
            } else {
                idType = "68";
                idNo = company.getCompanyLicenseNo();
            }
            if (mobile == null || "".equals(mobile)) {
                mobile = company.getCompanyContactTel();
            }
        }
        PaymentCodeForm paymentCodeForm = new PaymentCodeForm();
        paymentCodeForm.setType(type);
        paymentCodeForm.setP2PCode(ebpayProperties.getMrchCode());
        paymentCodeForm.setP2PType("2");
        paymentCodeForm.setThirdCustId(thirdCustId);
        paymentCodeForm.setCustAccId(accountSubNo);
        paymentCodeForm.setName(name);
        paymentCodeForm.setIdType(idType);
        paymentCodeForm.setIdNo(idNo);
        paymentCodeForm.setMobile(mobile);
        paymentCodeForm.setReturnurl(returnurl);
        paymentCodeForm.setNotifyUrl(notifyUrl);
        String genetorCnsmrSeqNo = "";
        if (type.equals("V")) {
            paymentCodeForm.setOrderId(orderId);
            paymentCodeForm.setOrig(getOrig(orig));
        } else {
            genetorCnsmrSeqNo = genetorCnsmrSeqNo();
            paymentCodeForm.setOrderId(genetorCnsmrSeqNo);
            //记录子账户关联流水号
            accountSub.setAccountSubOrderId(genetorCnsmrSeqNo);
            accountSubMapper.updateByExampleSelective(accountSub, accountSubExample);
        }
        paymentCodeForm.setFrontUrl(ebpayProperties.getFrontUrl());
        System.out.println(JSON.toJSONString(paymentCodeForm));
        return paymentCodeForm;
    }

    @Override
    public void returnBack(String orderid, String flag) {
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubOrderIdEqualTo(orderid);
        AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        if (accountSub != null) {
            accountSub.setAccountSubPwdStatus(Integer.parseInt(flag));
            accountSubMapper.updateByPrimaryKeySelective(accountSub);
        }

    }

    /**
     * 管理端 账户列表
     */
    @Override
    public List manageList(QueryParams queryParams) throws Exception {
        List<BankCard> bankCardList = new ArrayList<>();
        List<Map<String, Object>> list = accountSubCuMapper.manageList(queryParams);
        String fundSummaryAcctNo = ebpayProperties.getFundSummaryAcctNo();
        for (Map<String, Object> map : list) {
            String accountSubId = (String) map.get("account_sub_id");
            BankCardExample bankCardExample = new BankCardExample();
            bankCardExample.createCriteria().andBankCardAccountSubIdEqualTo(accountSubId).andBankCardStatusEqualTo(2);
            bankCardList = bankCardMapper.selectByExample(bankCardExample);
            if (bankCardList.size() == 0) {
                map.put("bank_card_cnt", "0");
            }
            String subAcctNo = (String) map.get("account_sub_no");
            CustAcctIdBalanceQueryRequest request = new CustAcctIdBalanceQueryRequest();
            request.setFundSummaryAcctNo(fundSummaryAcctNo);
            request.setQueryFlag("2");
            request.setPageNum("1");
            request.setSubAcctNo(subAcctNo);
            CustAcctIdBalanceQueryResponse response = paBankService.custAcctIdBalanceQuery(request);
            if ("000000".equals(response.getTxnReturnCode())) {
                CustAcctIdBalanceQuerySubResponse subResponse = response.getCustAcctIdBalanceQuerySubResponseList().get(0);
                map.put("account_sub_avail_balance", subResponse.getAcctAvailBal());
                map.put("account_sub_ash_balance", subResponse.getCashAmt());
            }
        }
        return list;
    }

    @Override
    public AccountSub selectByPrimaryKey(String id) {
        return accountSubMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(AccountSub accountSub) {
        accountSubMapper.updateByPrimaryKeySelective(accountSub);
    }

    @Override
    public String getPhone(String subAcctId) {
        if (StringUtils.isBlank(subAcctId)) {
            return null;
        }
        return accountSubCuMapper.getPhone(subAcctId);
    }

    private String genetorCnsmrSeqNo() {
        return "q" + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmssS"));
    }

    private String getOrig(Object object) {
        try {
            Class aClass = object.getClass();
            Field[] fields = aClass.getDeclaredFields();
            String orig = "";
            for (Field field : fields) {
                field.setAccessible(true);
                Object o = field.get(object);
                if (o == null) {
                    orig += "&";
                } else {
                    orig += o.toString() + "&";

                }
            }
            System.out.println(orig.substring(0, orig.length() - 1));
            return orig.substring(0, orig.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AnyException("转换Orig失败");
        }
    }

    @Override
    public Map<String, Object> getAccountSubBalance() throws Exception {
        Map<String, Object> result = new HashMap<>();
        String fundSummaryAcctNo = ebpayProperties.getFundSummaryAcctNo();
        // 总账户余额
        SupAcctIdBalanceQueryRequest supAcctIdBalanceQueryRequest = new SupAcctIdBalanceQueryRequest();
        supAcctIdBalanceQueryRequest.setFundSummaryAcctNo(fundSummaryAcctNo);
        Double totalAccountBalance = null;
        SupAcctIdBalanceQueryResponse supAcctIdBalanceQueryResponse = paBankService.supAcctIdBalanceQuery(supAcctIdBalanceQueryRequest);
        if ("000000".equals(supAcctIdBalanceQueryResponse.getTxnReturnCode())) {
            totalAccountBalance = Double.valueOf(supAcctIdBalanceQueryResponse.getCurBalabce());
            result.put("totalAccountBalance", totalAccountBalance);
        } else {
            throw new AnyException("获取数据失败");
        }
        // 3:功能子账号(2：挂账子账号  3：手续费子账号 4：利息子账号 5：平台担保子账号 7:平台在途子账户)
        List<CustAcctIdBalanceQuerySubResponse> functionalSubAccount = getCustAcctIdBalanceQuerySubResponseList("3");
        result.put("functionalSubAccount", "");
        Double dbSubAcctAvailBal = functionalSubAccount.stream().filter(res -> res.getSubAcctProperty().equals("5")).mapToDouble(item -> Double.valueOf(item.getAcctAvailBal())).sum();
        result.put("dbSubAcctAvailBal", dbSubAcctAvailBal);
        Double ztSubAcctAvailBal = functionalSubAccount.stream().filter(res -> res.getSubAcctProperty().equals("7")).mapToDouble(item -> Double.valueOf(item.getAcctAvailBal())).sum();
        result.put("ztSubAcctAvailBal", ztSubAcctAvailBal);
        Double gzSubAcctAvailBal = functionalSubAccount.stream().filter(res -> res.getSubAcctProperty().equals("2")).mapToDouble(item -> Double.valueOf(item.getAcctAvailBal())).sum();
        result.put("gzSubAcctAvailBal", gzSubAcctAvailBal);
        Double sxfSubAcctAvailBal = functionalSubAccount.stream().filter(res -> res.getSubAcctProperty().equals("3")).mapToDouble(item -> Double.valueOf(item.getAcctAvailBal())).sum();
        result.put("sxfSubAcctAvailBal", sxfSubAcctAvailBal);
        Double lxSubAcctAvailBal = functionalSubAccount.stream().filter(res -> res.getSubAcctProperty().equals("4")).mapToDouble(item -> Double.valueOf(item.getAcctAvailBal())).sum();
        result.put("lxSubAcctAvailBal", lxSubAcctAvailBal);
        // 2:普通会员子账号(业务子账户) 3:功能子账号
        // List<CustAcctIdBalanceQuerySubResponse> businessSubAccount = getCustAcctIdBalanceQuerySubResponseList("2");
        //map.put("businessSubAccount", businessSubAccount.stream().mapToDouble(item -> Double.valueOf(item.getAcctAvailBal())).sum());
        Double ywSubAcctAvailBal = totalAccountBalance - functionalSubAccount.stream().mapToDouble(item -> Double.valueOf(item.getAcctAvailBal())).sum();
        result.put("ywSubAcctAvailBal", ywSubAcctAvailBal);

        QueryParams queryParams = new QueryParams();
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountTypeEqualTo(1);
        Account account = accountMapper.selectByExample(accountExample).stream().findFirst().orElse(null);
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubAccountIdEqualTo(account.getAccountId());
        List<String> nos = accountSubMapper.selectByExample(accountSubExample).stream().map(AccountSub::getAccountSubNo).collect(Collectors.toList());
        queryParams.setNos(nos);
        Map dayPay = accountSubTradeCuMapper.totalAccountDayPay(queryParams);
        Map dayIncome = accountSubTradeCuMapper.totalAccountDayIncome(queryParams);
        Map monthPay = accountSubTradeCuMapper.totalAccountMonthPay(queryParams);
        Map monthIncome = accountSubTradeCuMapper.totalAccountMonthIncome(queryParams);
        result.putAll(dayPay);
        result.putAll(dayIncome);
        result.putAll(monthPay);
        result.putAll(monthIncome);
        return result;
    }

    @Override
    public void setSendSMS(String businessTradeId) {
        BusinessTrade businessTrade = businessTradeMapper.selectByPrimaryKey(businessTradeId);
        if (businessTrade != null) {
            AccountSubTrade accountSubTrade = accountSubTradeMapper.selectByPrimaryKey(businessTrade.getBusinessTradeAccountSubTradeId());
            accountSubTrade.setAccountSubTradeIsSendSms(true);
            accountSubTradeMapper.updateByPrimaryKeySelective(accountSubTrade);
        } else{
            throw new DlebException("未找到相应的业务单");
        }
    }

    private List<CustAcctIdBalanceQuerySubResponse> getCustAcctIdBalanceQuerySubResponseList(String queryFlag) throws Exception {
        String fundSummaryAcctNo = ebpayProperties.getFundSummaryAcctNo();
        CustAcctIdBalanceQueryRequest request = new CustAcctIdBalanceQueryRequest();
        Integer pageNum = 1;
        request.setFundSummaryAcctNo(fundSummaryAcctNo);
        request.setQueryFlag(queryFlag);
        request.setPageNum(pageNum.toString());
        List<CustAcctIdBalanceQuerySubResponse> allList = new ArrayList();
        do {
            CustAcctIdBalanceQueryResponse response = paBankService.custAcctIdBalanceQuery(request);
            if ("000000".equals(response.getTxnReturnCode())) {
                allList.addAll(response.getCustAcctIdBalanceQuerySubResponseList());
                // 结束标志 0：否  1：是
                String EndFlag = response.getEndFlag();
                if ("0".equals(EndFlag)) {
                    request.setPageNum((++pageNum).toString());
                } else {
                    break;
                }
            } else {
                throw new AnyException("获取数据失败");
            }
        } while (true);
        return allList;
    }
}
