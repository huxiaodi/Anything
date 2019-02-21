package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.dao.generator.AccountSubTradeMapper;
import com.dllg.ebpay.model.dto.AccountSubTradeDto;
import com.dllg.ebpay.model.dto.InHandTradeDto;
import com.dllg.ebpay.model.form.ServiceChargeForm;
import com.dllg.ebpay.model.generator.AccountSub;
import com.dllg.ebpay.model.request.QueryParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 子账户流水记录
 */
@Repository
public interface ModifyAccountPhoneCuMapper{

    List getAccountSub(@Param("accountSub") String accountSub);

    void setAccountSubPhone(AccountSub accountSub);

}
