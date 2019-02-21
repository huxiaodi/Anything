package com.dllg.ebpay.service.schedule.impl;

import com.alibaba.fastjson.JSONArray;
import com.dllg.ebpay.dao.custom.AccountSubTradeCuMapper;
import com.dllg.ebpay.dao.generator.AccountSubMapper;
import com.dllg.ebpay.dao.generator.AccountSubTradeMapper;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.service.schedule.ScheduleService;
import com.dllg.pabank.model.request.BankWithdrawCashBackQueryRequest;
import com.dllg.pabank.model.response.BankWithdrawCashBackQueryResponse;
import com.dllg.pabank.model.response.BankWithdrawCashBackQuerySubResponse;
import com.dllg.pabank.service.PABankService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger logger = LogManager.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private AccountSubTradeCuMapper accountSubTradeCuMapper;
    @Autowired
    private PABankService paBankService;
    @Autowired
    private AccountSubTradeMapper accountSubTradeMapper;
    @Autowired
    private AccountSubMapper accountSubMapper;
    @Override
    public void clearInvalidDate() {
        // 清理无效的交易记录
        Integer integer = accountSubTradeCuMapper.deleteInvalidDate();
        logger.info("清理掉无效的交易记录{}条", integer == null ? 0 : integer);
    }

    @Override
    public void bankWithdrawCashBackQuery(){
        try {
            BankWithdrawCashBackQueryRequest bankWithdrawCashBackQueryRequest = new BankWithdrawCashBackQueryRequest();
            bankWithdrawCashBackQueryRequest.setFunctionFlag("1");
            bankWithdrawCashBackQueryRequest.setFundSummaryAcctNo("15000081987225");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -1);
            bankWithdrawCashBackQueryRequest.setEndDate("20180411");
            bankWithdrawCashBackQueryRequest.setStartDate("20180411");
            bankWithdrawCashBackQueryRequest.setReservedMsg("");
            BankWithdrawCashBackQueryResponse bankWithdrawCashBackQueryResponse = paBankService.bankWithdrawCashBackQuery(bankWithdrawCashBackQueryRequest);
            List<BankWithdrawCashBackQuerySubResponse> bankWithdrawCashBackQuerySubResponseList = JSONArray.parseArray(bankWithdrawCashBackQueryResponse.getTranItemArray(),BankWithdrawCashBackQuerySubResponse.class);
            for(BankWithdrawCashBackQuerySubResponse bankWithdrawCashBackQuerySubResponse : bankWithdrawCashBackQuerySubResponseList){
                String seqNo = bankWithdrawCashBackQuerySubResponse.getOldMarketSeqNo();
                if(seqNo == null || seqNo.isEmpty()){
                   return;
                }
                AccountSubTradeExample accountSubTradeExample = new AccountSubTradeExample();
                accountSubTradeExample.createCriteria().andAccountSubTradeBankNoEqualTo(seqNo);
                AccountSubTrade accountSubTrade = accountSubTradeMapper.selectByExample(accountSubTradeExample).stream().findFirst().orElse(null);
                if(10 == accountSubTrade.getAccountSubTradeTranStatus()){
                    return;
                }else{
                    AccountSub accountSub = accountSubMapper.selectByPrimaryKey(accountSubTrade.getAccountSubTradeAccountSubId());
                    String msg = "";
                    if(accountSub != null){
                        if(accountSub.getAccountSubNo().equals(bankWithdrawCashBackQuerySubResponse.getRejectInAcctTranSeqNo())){
                            msg = "金额已退回子账户!";
                        }else{
                            msg = "金额已退回平台，请联系管理员！";
                        }
                        accountSubTrade.setAccountSubTradeBackReason(bankWithdrawCashBackQuerySubResponse.getRejectBillReason()+ "!  " + msg);
                        accountSubTrade.setAccountSubTradeTranStatus(10);
                        accountSubTradeMapper.updateByPrimaryKey(accountSubTrade);
                    }else{
                        continue;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
