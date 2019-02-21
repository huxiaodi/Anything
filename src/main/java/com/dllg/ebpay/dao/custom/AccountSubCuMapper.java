package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.dao.generator.AccountSubMapper;
import com.dllg.ebpay.model.dto.AccountSubDto;
import com.dllg.ebpay.model.dto.UserAccountInfoDto;
import com.dllg.ebpay.model.form.InSubAcctForm;
import com.dllg.ebpay.model.request.QueryParams;

import java.util.List;
import java.util.Map;

public interface AccountSubCuMapper extends AccountSubMapper {

    List<AccountSubDto> getAccountSub(String userId);
    
    List<UserAccountInfoDto> getAccountList(String relationId);

    UserAccountInfoDto getAccountByNo(String accountNo);

    UserAccountInfoDto getAccountInfoByNo(String accountNo);

    UserAccountInfoDto getAccountById(String accountId);

    List<UserAccountInfoDto> getInSubAcct(InSubAcctForm form);
    List<UserAccountInfoDto> getInSubAcctInfo(UserAccountInfoDto userAccountInfoDto);

    /**
     * 管理端 账户列表
     */
    List<Map<String,Object>> manageList(QueryParams queryParams);

    String getPhone(String subAcctId);
}
