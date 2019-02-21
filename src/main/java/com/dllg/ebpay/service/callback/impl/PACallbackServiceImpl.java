package com.dllg.ebpay.service.callback.impl;

import com.dllg.ebpay.dao.generator.AccountSubTradeMapper;
import com.dllg.ebpay.model.generator.AccountSubTrade;
import com.dllg.ebpay.service.callback.PACallbackService;
import com.dllg.ebpay.service.pay.AccountSubTradeService;
import com.dllg.ebpay.service.pay.TransferService;
import com.dllg.ebpay.service.pay.impl.WithdrawalService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PACallbackServiceImpl implements PACallbackService {

    private final static Logger logger = LogManager.getLogger(PACallbackServiceImpl.class);

    @Autowired
    private TransferService transferService;

    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private AccountSubTradeService accountSubTradeService;

    @Autowired
    private AccountSubTradeMapper accountSubTradeMapper;

    @Override
    public void executeTrade(String orderid, String flag, String sign) {
        AccountSubTrade order = accountSubTradeService.getOrder(orderid);
        // 验密失败，删除
        if (order == null) {
            logger.info("转账验密回调已完成，单据被删除");
            return;
        }
        if (order.getAccountSubTradeTranStatus() != -1) {
            logger.info("转账验密回调已完成，单据已被修改");
            return;
        }
        if (StringUtils.isBlank(flag) || !"1".equals(flag)) {
            // 删除掉刚才创建的单据
            accountSubTradeMapper.deleteByPrimaryKey(orderid);
            return;
        }

        // 更新状态
        AccountSubTrade trade = new AccountSubTrade();
        trade.setAccountSubTradeId(orderid);
        trade.setAccountSubTradeTranStatus(6);
        accountSubTradeMapper.updateByPrimaryKeySelective(trade);

        // 转账
        if (order.getAccountSubTradeType() == 2) {
            transferService.sendTransferRequest(order, sign);
        } else if (order.getAccountSubTradeType() == 4) {
            // 提现是否成功需要后续调度查询
            withdrawalService.sendTransferRequest(order,sign);
        }

    }
}
