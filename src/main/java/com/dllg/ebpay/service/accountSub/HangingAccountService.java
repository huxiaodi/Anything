package com.dllg.ebpay.service.accountSub;

import com.dllg.ebpay.model.dto.AccountSubTradeDto;
import com.dllg.ebpay.model.dto.UserAccountInfoDto;
import com.dllg.ebpay.model.form.AdjustAccountSubForm;
import com.dllg.ebpay.model.form.HangingAccountListFrom;
import com.dllg.ebpay.model.form.InSubAcctForm;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 10:30 2018/4/12
 */
public interface HangingAccountService {
        /**
         * 调用平安 查询普通转账充值明细 接口获取数据 更新交易单
         * @param hangingAccountListFrom
         * @return   挂账和充值的流水记录
         * @throws Exception
         */
        List<AccountSubTradeDto> getHangingAccountList (HangingAccountListFrom hangingAccountListFrom) throws Exception;


        public void getHangingAccountListByBank() throws Exception;

        /**
         * 查询子账户的充值记录
         * @throws Exception
         */
        public void getHangingAccountListBySubAccount() throws Exception;
        /**
         * 查询出 流水记录表中 充值和挂账类型单据的  子账户
         * @return
         * @throws Exception
         */
        List<Map<String,String>> getHangingAccountSub() throws Exception;

        /**
         * 查询出所有的子账户
         * @param
         * @return
         */
        List<UserAccountInfoDto> getAllAccountSub();

        /**
         * 调账方法
         * @param form
         * @throws Exception
         */
        void adjustAccountSub(AdjustAccountSubForm form)  throws  Exception;

        /**
         * 判断是否可以调账
         * @param accountSubTradeId
         * @return
         */
        boolean checkAccountSubTrade(String  accountSubTradeId);


        /**
         * 获取交易单的信息
         * @param accountSubTradeId
         * @return
         */
        AccountSubTradeDto getHangingTradeDetailById(String  accountSubTradeId);


}

