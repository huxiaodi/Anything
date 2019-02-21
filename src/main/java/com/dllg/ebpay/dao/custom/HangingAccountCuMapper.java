package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.dto.AccountSubTradeDto;
import com.dllg.ebpay.model.dto.UserAccountInfoDto;
import com.dllg.ebpay.model.form.HangingAccountListFrom;
import com.dllg.ebpay.model.form.InSubAcctForm;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 10:34 2018/4/12
 */
public interface HangingAccountCuMapper {
    List<AccountSubTradeDto> getHangingAccountList(HangingAccountListFrom hangingAccountListFrom);

    List<Map<String ,String>>  getHangingAccountSub();

    List<UserAccountInfoDto>  getAllAccountSub();

    AccountSubTradeDto getHangingTradeDetailById(String accountSubTradeId);

}
