package com.dllg.ebpay.service.api;

import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.model.request.ApiRegistRequest;
import com.dllg.ebpay.model.request.RegistRequest;
import com.dllg.framework.model.ApiResult;
import com.dllg.pabank.model.request.MemberTransactionsInspectionRequest;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface RegistService {

    List<User> checkUserMobile(String mobile);

    List<Company> checkCompanyContactTel(String mobile);

    Map<String, Object> getPaymentMessage(String id);

    ApiResult<Object> subBind(RegistRequest registRequest);

    List<User> checkPersonalRegist(String userIdCard);

    public MemberTransactionsInspectionRequest getMemberTransactionsInspectionRequest(AccountSubTrade accountSubTrade, BusinessTrade businessTrade);

    List<Company> checkCompanyRegist(String companyUnifiedSocialCreditLdentifier);

    List<Company> checkCompanyRegist(RegistRequest registRequest);

    List<AccountSubBind> checkIsBind(String accountSubBindRelationId);

    List<Map<String, String>> getAccount(RegistRequest registRequest);

    ApiResult checkBindMobile(RegistRequest registRequest);

    ApiResult save(ApiRegistRequest apiRegistRequest, String mobile) throws Exception;

    Boolean checkUserName(String userName);
}
