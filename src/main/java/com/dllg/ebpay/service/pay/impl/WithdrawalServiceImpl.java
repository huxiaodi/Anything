package com.dllg.ebpay.service.pay.impl;

import com.dllg.common.CommonUtil;
import com.dllg.common.Constants;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.custom.AccountSubCuMapper;
import com.dllg.ebpay.dao.custom.BankCardCuMapper;
import com.dllg.ebpay.dao.generator.AccountSubTradeMapper;
import com.dllg.ebpay.model.dto.UserAccountInfoDto;
import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.model.generator.AccountSubTrade;
import com.dllg.ebpay.model.generator.BankCard;
import com.dllg.ebpay.model.request.WithdrawalRequest;
import com.dllg.ebpay.model.response.WithdrawalBankCardResponse;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.ebpay.service.bankCard.LocalBankCardService;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.security.Auth;
import com.dllg.pabank.model.request.MemberWithdrawSprtFeePwdVerifyRequest;
import com.dllg.pabank.model.response.MemberWithdrawSprtFeePwdVerifyResponse;
import com.dllg.pabank.service.PABankService;
import com.dllg.pabank.web.PABankController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    private final static Logger logger = LogManager.getLogger(PABankController.class);

    @Autowired
    private AccountSubTradeMapper accountSubTradeMapper;

    @Autowired
    private BankCardCuMapper bankCardCuMapper;

    @Autowired
    private Auth auth;

    @Autowired
    private AccountSubService accountSubService;

    @Autowired
    private AccountSubCuMapper accountSubCuMapper;

    @Autowired
    private EbpayProperties ebpayProperties;

    @Autowired
    private PABankService paBankService;

    @Autowired
    private LocalBankCardService localBankCardService;

    @Override
    public List<WithdrawalBankCardResponse> getBindList(String accountNo) {
        return bankCardCuMapper.getBindCardBySubNo(accountNo);
    }

    @Transactional
    @Override
    public PaymentCodeForm withdrawal(WithdrawalRequest request) {
        UserAccountInfoDto subAccount = accountSubCuMapper.getAccountInfoByNo(request.getSubAcctNo());

        request.setFundSummaryAcctNo(subAccount.getFundSummaryAcctNo());
        request.setTranNetMemberCode(subAccount.getUserId());
        request.setTakeCashAcctName(subAccount.getUserAccountName());
        request.setCcy("RMB");

        // 数据校验
        Map<String, String> validate = CommonUtil.validate(request);
        if (!validate.isEmpty()) {
            throw new AnyException(validate.values().toString());
        }

        // 检查转出账号是否有绑定银行卡
        BankCard bankCard = new BankCard();
        bankCard.setBankCardAccountSubId(request.getSubAcctNo());
        List<BankCard> bankCradList = localBankCardService.getBundBankCradList(bankCard);
        if (bankCradList.isEmpty()) {
            throw new AnyException("提现子账户没有绑定银行卡，请先绑定");
        }

        // 必须等校验完成后再格式化数据，舍入模式为：直接删除多余小数位
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        request.setApplyTakeCashAmt(decimalFormat.format(new BigDecimal(request.getApplyTakeCashAmt())));
        request.setTakeCashCommission(decimalFormat.format(new BigDecimal(request.getTakeCashCommission())));


        // 流水数据
        AccountSubTrade trade = saveTradeRecord(request,subAccount);
        MemberWithdrawSprtFeePwdVerifyRequest send = new MemberWithdrawSprtFeePwdVerifyRequest();
        BeanUtils.copyProperties(request,send);
        // 平安表单内容
        return accountSubService.paymentCodeManage(trade.getAccountSubTradeId(), request.getSubAcctNo(), "V", send, " ", ebpayProperties.getTradeCallbackUrl(),null);
    }

    private AccountSubTrade saveTradeRecord(WithdrawalRequest request,UserAccountInfoDto subAccount) {
        String uuid = CommonUtil.uuid();
        Date now = new Date();

        AccountSubTrade trade = new AccountSubTrade();
        trade.setAccountSubTradeId(uuid);
        trade.setAccountSubTradeAccountSubId(subAccount.getSubAcctId());
        trade.setAccountSubTradeNo("TX-LS" + new Date().getTime());
        trade.setAccountSubTradeType(4);
        trade.setAccountSubTradeTranAmt(new BigDecimal(request.getApplyTakeCashAmt()));
        trade.setAccountSubTradeTranFee(new BigDecimal(request.getTakeCashCommission()));
        trade.setAccountSubTradeTranDate(now);
        trade.setAccountSubTradeTranType("01");
        trade.setAccountSubTradeTranStatus(-1);
        trade.setAccountSubTradeTranPaymentType(1);
        trade.setAccountSubTradeCcy(request.getCcy());
        trade.setAccountSubTradeBankName(subAccount.getAccountType() == 1 ? "平安银行" : "其他银行");
        trade.setAccountSubTradeRemark(request.getRemark());
        trade.setAccountSubTradeTakeCashAccountNo(request.getTakeCashAcctNo());
        trade.setAccountSubTradeTakeCashAccountName(request.getTakeCashAcctName());

        trade.setAccountSubTradeCreateUserId(auth.getCurrentUser().getUserId());
        trade.setAccountSubTradeCreateTime(now);

        accountSubTradeMapper.insertSelective(trade);

        return trade;
    }

    @Override
    public void sendTransferRequest(AccountSubTrade request, String sign) {
        UserAccountInfoDto subAccount = accountSubCuMapper.getAccountById(request.getAccountSubTradeAccountSubId());

        AccountSubTrade trade = new AccountSubTrade();
        trade.setAccountSubTradeId(request.getAccountSubTradeId());
        trade.setAccountSubTradeTranDate(new Date());

        MemberWithdrawSprtFeePwdVerifyRequest send = new MemberWithdrawSprtFeePwdVerifyRequest();
        send.setFundSummaryAcctNo(subAccount.getFundSummaryAcctNo());
        send.setSubAcctNo(subAccount.getSubAcctNo());
        send.setTranNetMemberCode(subAccount.getUserId());
        send.setSubAcctName(subAccount.getUserAccountName());
        send.setTakeCashAcctNo(request.getAccountSubTradeTakeCashAccountNo());
        send.setTakeCashAcctName(request.getAccountSubTradeTakeCashAccountName());
        send.setCcy(request.getAccountSubTradeCcy());
        send.setApplyTakeCashAmt(request.getAccountSubTradeTranAmt().toString());
        send.setTakeCashCommission(request.getAccountSubTradeTranFee().toString());
        send.setRemark(request.getAccountSubTradeRemark());
        send.setWebSign(sign);
        try {
            MemberWithdrawSprtFeePwdVerifyResponse response = paBankService.memberWithdrawSprtFeePwdVerify(send);
            if (response == null) {
                throw new AnyException("请求异常");
            }
            if (!response.getTxnReturnCode().equals(Constants.PA_TRADE_SUCCESS_CODE)) {
                logger.error("提现请求被拒：{}  {}", response.getTxnReturnCode(), response.getTxnReturnMsg());
                throw new AnyException("提现失败");
            }
            trade.setAccountSubTradeBankNo(response.getCnsmrSeqNo());
            accountSubTradeMapper.updateByPrimaryKeySelective(trade);
        } catch (Exception e) {
            logger.error("提现请求失败：" + e);
            trade.setAccountSubTradeTranStatus(1);
            accountSubTradeMapper.updateByPrimaryKeySelective(trade);

        }
    }
}
