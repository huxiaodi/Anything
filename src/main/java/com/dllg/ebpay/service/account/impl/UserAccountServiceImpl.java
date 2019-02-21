package com.dllg.ebpay.service.account.impl;

import com.alibaba.fastjson.JSONArray;
import com.dllg.common.Constants;
import com.dllg.ebpay.dao.custom.AccountSubCuMapper;
import com.dllg.ebpay.dao.custom.UserCuMapper;
import com.dllg.ebpay.dao.generator.AccountMapper;
import com.dllg.ebpay.dao.generator.AccountSubMapper;
import com.dllg.ebpay.dao.generator.CompanyMapper;
import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.enums.AccountTypeEnum;
import com.dllg.ebpay.model.dto.UserAccountInfoDto;
import com.dllg.ebpay.model.form.InSubAcctForm;
import com.dllg.ebpay.model.generator.Account;
import com.dllg.ebpay.model.generator.AccountSub;
import com.dllg.ebpay.model.generator.AccountSubExample;
import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.model.response.OutSubAcctResponse;
import com.dllg.ebpay.model.response.UserAccountBalanceResponse;
import com.dllg.ebpay.service.account.UserAccountService;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.framework.utils.FileUploadUtils;
import com.dllg.pabank.model.request.CustAcctIdBalanceQueryRequest;
import com.dllg.pabank.model.response.CustAcctIdBalanceQueryResponse;
import com.dllg.pabank.model.response.CustAcctIdBalanceQuerySubResponse;
import com.dllg.pabank.service.PABankService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private Logger logger = LogManager.getLogger(UserAccountServiceImpl.class);

    @Autowired
    private Auth auth;

    @Autowired
    private PABankService paBankService;

    @Autowired
    private AccountSubCuMapper accountSubCuMapper;

    @Autowired
    private UserCuMapper userCuMapper;

    @Autowired
    private FileUploadUtils fileUploadUtils;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountSubMapper accountSubMapper;

    @Override
    public UserAccountBalanceResponse getAvailableBalance() {
        List<UserAccountInfoDto> accountQueryRequest = getUserAccountList(auth.getCurrentUser().getUserId());
        return totalBalance(accountQueryRequest);
    }

    @Override
    public UserAccountBalanceResponse getAvailableBalance(String accountNo) {
        UserAccountInfoDto account = accountSubCuMapper.getAccountByNo(accountNo);
        return totalBalance(Collections.singletonList(account));
    }

    @Override
    public String getUserAccountName() {
        return userCuMapper.getUserAccountName(auth.getCurrentUser().getUserId());
    }

    @Override
    public Map<String,BigDecimal> getBalanceByAccountSub(String accountSubNo) throws Exception{
        BigDecimal acctAvailBal = BigDecimal.ZERO;
        BigDecimal cashAmt = BigDecimal.ZERO;
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("acctAvailBal",acctAvailBal);
        result.put("cashAmt",cashAmt);
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubNoEqualTo(accountSubNo);
        AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        if(accountSub != null){
            Account account = accountMapper.selectByPrimaryKey(accountSub.getAccountSubAccountId());
            if(account != null){
                CustAcctIdBalanceQueryRequest custAcctIdBalanceQueryRequest = new CustAcctIdBalanceQueryRequest();
                custAcctIdBalanceQueryRequest.setFundSummaryAcctNo(account.getAccountFundSummaryAccountNo());
                custAcctIdBalanceQueryRequest.setPageNum("1");
                custAcctIdBalanceQueryRequest.setQueryFlag("2");
                custAcctIdBalanceQueryRequest.setReservedMsg("");
                custAcctIdBalanceQueryRequest.setSubAcctNo(accountSub.getAccountSubNo());
                CustAcctIdBalanceQueryResponse custAcctIdBalanceQueryResponse = paBankService.custAcctIdBalanceQuery(custAcctIdBalanceQueryRequest);
                List<CustAcctIdBalanceQuerySubResponse> custAcctIdBalanceQuerySubResponses =  JSONArray.parseArray(custAcctIdBalanceQueryResponse.getAcctArray(),CustAcctIdBalanceQuerySubResponse.class);
                if(custAcctIdBalanceQuerySubResponses == null || custAcctIdBalanceQuerySubResponses.size() <= 0){
                    return result;
                }
                for(CustAcctIdBalanceQuerySubResponse custAcctIdBalanceQuerySubResponse : custAcctIdBalanceQuerySubResponses){
                    acctAvailBal = acctAvailBal.add(new BigDecimal(custAcctIdBalanceQuerySubResponse.getAcctAvailBal()));
                    cashAmt = cashAmt.add(new BigDecimal(custAcctIdBalanceQuerySubResponse.getCashAmt()));
                }
                result.put("acctAvailBal",acctAvailBal);
                result.put("cashAmt",cashAmt);
                return result;
            }
        }
        return result;
    }

    @Override
    public Map<String, BigDecimal> getBalanceByAccountSubId(String accountSubId) throws Exception {
        BigDecimal acctAvailBal = BigDecimal.ZERO;
        BigDecimal cashAmt = BigDecimal.ZERO;
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("acctAvailBal",acctAvailBal);
        result.put("cashAmt",acctAvailBal);
        AccountSub accountSub = accountSubMapper.selectByPrimaryKey(accountSubId);
        if(accountSub != null){
            Account account = accountMapper.selectByPrimaryKey(accountSub.getAccountSubAccountId());
            if(account != null){
                CustAcctIdBalanceQueryRequest custAcctIdBalanceQueryRequest = new CustAcctIdBalanceQueryRequest();
                custAcctIdBalanceQueryRequest.setFundSummaryAcctNo(account.getAccountFundSummaryAccountNo());
                custAcctIdBalanceQueryRequest.setPageNum("1");
                custAcctIdBalanceQueryRequest.setQueryFlag("2");
                custAcctIdBalanceQueryRequest.setReservedMsg("");
                custAcctIdBalanceQueryRequest.setSubAcctNo(accountSub.getAccountSubNo());
                CustAcctIdBalanceQueryResponse custAcctIdBalanceQueryResponse = paBankService.custAcctIdBalanceQuery(custAcctIdBalanceQueryRequest);
                List<CustAcctIdBalanceQuerySubResponse> custAcctIdBalanceQuerySubResponses =  JSONArray.parseArray(custAcctIdBalanceQueryResponse.getAcctArray(),CustAcctIdBalanceQuerySubResponse.class);
                if(custAcctIdBalanceQuerySubResponses == null || custAcctIdBalanceQuerySubResponses.size() <= 0){
                    return result;
                }
                for(CustAcctIdBalanceQuerySubResponse custAcctIdBalanceQuerySubResponse : custAcctIdBalanceQuerySubResponses){
                    acctAvailBal = acctAvailBal.add(new BigDecimal(custAcctIdBalanceQuerySubResponse.getAcctAvailBal()));
                    cashAmt = cashAmt.add(new BigDecimal(custAcctIdBalanceQuerySubResponse.getCashAmt()));
                }
                result.put("acctAvailBal",acctAvailBal);
                result.put("cashAmt",acctAvailBal);
                return result;
            }
        }
        return result;
    }


    @Override
    public List<UserAccountInfoDto> getUserAccountList() {
        return getUserAccountList(auth.getCurrentUser().getUserId());
    }

    @Override
    public List<UserAccountInfoDto> getUserAccountList(String userId) {
        String relationId = userId;
        // 企业
        if (auth.getCurrentUser().getUserType() == 2) {
            relationId = auth.getCurrentUser().getUserCompanyId();
            if (!auth.getCurrentUser().getUserId().equals(userId)) {
                User user = userCuMapper.selectByPrimaryKey(userId);
                if (user == null || StringUtils.isBlank(user.getUserCompanyId())) {
                    throw new AnyException("用户异常!");
                }
                relationId = user.getUserCompanyId();
            }
        }
        return accountSubCuMapper.getAccountList(relationId);
    }

    @Override
    public List<OutSubAcctResponse> getOutSubAcctNameList(String keyword) {
        return userCuMapper.getOutSubAcctNameList(keyword);
    }

    @Override
    public List<UserAccountInfoDto> getInSubAcct(InSubAcctForm form) {
        form.setPageNum(1);
        PageHelper.startPage(form);
        return accountSubCuMapper.getInSubAcct(form);
    }

    @Override
    public List<UserAccountInfoDto> getInSubAcctInfo(UserAccountInfoDto userAccountInfoDto) {
        return accountSubCuMapper.getInSubAcctInfo(userAccountInfoDto);
    }

    @Override
    public String getUploadInfo(MultipartFile file) {
        AuthUser authUser = auth.getCurrentUser();
        User user = userMapper.selectByPrimaryKey(authUser.getUserId());
        if (authUser.getUserType() == 1) {
            user.setUserHeadImg(fileUploadUtils.upload(file, authUser.getUserId(), FileUploadUtils.FOLDER_USER));

        } else {
            user.setUserHeadImg(fileUploadUtils.upload(file, authUser.getUserCompanyId(), FileUploadUtils.FOLDER_USER));
        }
        userMapper.updateByPrimaryKeySelective(user);
        return user.getUserHeadImg();
    }

    private UserAccountBalanceResponse totalBalance(List<UserAccountInfoDto> list) {
        // 没有子账户
        if (list.isEmpty()) {
            return new UserAccountBalanceResponse();
        }
        BigDecimal balance = BigDecimal.ZERO;
        BigDecimal withdrawalAmount = BigDecimal.ZERO;
        for (UserAccountInfoDto request : list) {
            if (Objects.equals(AccountTypeEnum.PA.getCode(), request.getAccountType())) {
                try {
                    CustAcctIdBalanceQueryRequest queryRequest = new CustAcctIdBalanceQueryRequest();
                    BeanUtils.copyProperties(request, queryRequest);
                    queryRequest.setQueryFlag("2");
                    queryRequest.setPageNum("1");
                    queryRequest.setReservedMsg("");
                    CustAcctIdBalanceQueryResponse response = paBankService.custAcctIdBalanceQuery(queryRequest);
                    if (response == null || !response.getTxnReturnCode().equals(Constants.PA_TRADE_SUCCESS_CODE)) {
                        String msg = response == null ? "" : response.getTxnReturnCode() + " : " + response.getTxnReturnMsg();
                        throw new AnyException("请求异常 " + msg);
                    }
                    List<CustAcctIdBalanceQuerySubResponse> subResponseList = response.getCustAcctIdBalanceQuerySubResponseList();
                    if (CollectionUtils.isEmpty(subResponseList)) {
                        throw new AnyException("子账户信息异常");
                    }
                    String acctAvailBal = subResponseList.get(0).getAcctAvailBal();
                    String cashAmt = subResponseList.get(0).getCashAmt();
                    balance = balance.add(StringUtils.isBlank(acctAvailBal) ? BigDecimal.ZERO : new BigDecimal(acctAvailBal));
                    withdrawalAmount = withdrawalAmount.add(StringUtils.isBlank(cashAmt) ? BigDecimal.ZERO : new BigDecimal(cashAmt));
                } catch (Exception e) {
                    logger.error("查询子账户" + request.getSubAcctNo() + "资金余额失败", e);
                }
            }
        }
        UserAccountBalanceResponse result = new UserAccountBalanceResponse();
        result.setAcctAvailBal(balance);
        result.setCashAmt(withdrawalAmount);

        return result;
    }
}
