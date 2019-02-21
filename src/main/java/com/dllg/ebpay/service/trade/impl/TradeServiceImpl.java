package com.dllg.ebpay.service.trade.impl;

import com.dllg.common.Constants;
import com.dllg.ebpay.dao.custom.AccountSubTradeCuMapper;
import com.dllg.ebpay.model.dto.InHandTradeDto;
import com.dllg.ebpay.model.generator.AccountSubTrade;
import com.dllg.ebpay.service.pay.impl.TransferServiceImpl;
import com.dllg.ebpay.service.trade.TradeService;
import com.dllg.framework.exception.AnyException;
import com.dllg.pabank.model.request.SingleTransactionStatusQueryRequest;
import com.dllg.pabank.model.response.SingleTransactionStatusQueryResponse;
import com.dllg.pabank.service.PABankService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TradeServiceImpl implements TradeService {

    private final static Logger logger = LogManager.getLogger(TransferServiceImpl.class);

    @Autowired
    private AccountSubTradeCuMapper accountSubTradeCuMapper;

    @Autowired
    private PABankService paBankService;

    @Override
    public void getPaAccountTradeResult() {
        logger.info("-------------- 平安银行交易结果查询 -----------------");
        // 1 查询所有处理中的单据
        List<InHandTradeDto> inHandTrade = accountSubTradeCuMapper.getInHandTrade();
        // 2 查询银行单笔交易状态
        if (inHandTrade.isEmpty()) {
            return;
        }
        for (InHandTradeDto trade : inHandTrade) {
            try {
                SingleTransactionStatusQueryRequest request = new SingleTransactionStatusQueryRequest();
                request.setFundSummaryAcctNo(trade.getAccountFundSummaryAccountNo());
                request.setFunctionFlag(getFunctionFlag(trade.getAccountSubTradeType()));
                request.setTranNetSeqNo(trade.getAccountSubTradeBankNo());
                request.setSubAcctNo(trade.getAccountSubNo());

                SingleTransactionStatusQueryResponse response = paBankService.singleTransactionStatusQuery(request);
                if (response == null) {
                    throw new AnyException(trade.getAccountSubTradeBankNo() + ":查询结果请求异常");
                }
                if (!response.getTxnReturnCode().equals(Constants.PA_TRADE_SUCCESS_CODE)) {
                    logger.error("{}:查询结果请求被拒：{}  {}",trade.getAccountSubTradeBankNo(),response.getTxnReturnCode(), response.getTxnReturnMsg());
                    continue;
                }
                // 3 更新状态
                String status = response.getTranStatus();
                if (Objects.equals(status, "0") || Objects.equals(status, "1")) {

                    AccountSubTrade subTrade = new AccountSubTrade();
                    subTrade.setAccountSubTradeId(trade.getAccountSubTradeId());
                    subTrade.setAccountSubTradeTranStatus(Integer.valueOf(status));
                    subTrade.setAccountSubFailureReason(Objects.equals(status, "1") ? response.getFailMsg() : null);
                    accountSubTradeCuMapper.updateByPrimaryKeySelective(subTrade);
                }
            } catch (Exception e) {
                logger.error("{}：查询失败 --> {}",trade.getAccountSubTradeBankNo(),e.getMessage());
            }
        }

    }

    // 1-会员交易，2-转账，3-充值，4-提现，5-服务费，6-退款，7-挂账
    private String getFunctionFlag(String type) {
        if ("1".equals(type) || "2".equals(type)) {
            return "2";
        } else if ("3".equals(type)) {
            return "4";
        } else if ("4".equals(type)) {
            return "3";
        } else {
            return null;
        }

    }
}
