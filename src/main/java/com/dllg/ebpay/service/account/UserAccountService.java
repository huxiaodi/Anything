package com.dllg.ebpay.service.account;

import com.dllg.ebpay.model.dto.UserAccountInfoDto;
import com.dllg.ebpay.model.form.InSubAcctForm;
import com.dllg.ebpay.model.response.OutSubAcctResponse;
import com.dllg.ebpay.model.response.UserAccountBalanceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserAccountService {
    /**
     * 获取用户可用余额
     */
    UserAccountBalanceResponse getAvailableBalance();

    /**
     * 获取子账户可用余额
     */
    UserAccountBalanceResponse getAvailableBalance(String accountNo);

    String getUserAccountName();

    Map<String,BigDecimal> getBalanceByAccountSub(String accountSub) throws Exception;

    Map<String,BigDecimal> getBalanceByAccountSubId(String accountSubId) throws Exception;


    List<UserAccountInfoDto> getUserAccountList();

    List<UserAccountInfoDto> getUserAccountList(String userId);

    List<OutSubAcctResponse> getOutSubAcctNameList(String keyword);

    List<UserAccountInfoDto> getInSubAcct(InSubAcctForm form);
    List<UserAccountInfoDto> getInSubAcctInfo(UserAccountInfoDto userAccountInfoDto);

    String getUploadInfo(MultipartFile file);

}
