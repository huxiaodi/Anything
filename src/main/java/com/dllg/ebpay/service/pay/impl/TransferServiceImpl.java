package com.dllg.ebpay.service.pay.impl;

import com.dllg.common.CommonUtil;
import com.dllg.common.Constants;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.custom.AccountSubCuMapper;
import com.dllg.ebpay.dao.generator.AccountSubTradeMapper;
import com.dllg.ebpay.dao.generator.DictMapper;
import com.dllg.ebpay.model.dto.UserAccountInfoDto;
import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.model.generator.AccountSubTrade;
import com.dllg.ebpay.model.generator.BankCard;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.generator.DictExample;
import com.dllg.ebpay.model.request.TransferRequest;
import com.dllg.ebpay.service.account.UserAccountService;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.ebpay.service.bankCard.LocalBankCardService;
import com.dllg.ebpay.service.msgCode.MsgCodeService;
import com.dllg.ebpay.service.pay.TransferService;
import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.security.Auth;
import com.dllg.pabank.model.request.MemberTransactionsInspectionRequest;
import com.dllg.pabank.model.response.MemberTransactionsInspectionResponse;
import com.dllg.pabank.service.PABankService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
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
public class TransferServiceImpl implements TransferService {

    private final static Logger logger = LogManager.getLogger(TransferServiceImpl.class);

    @Autowired
    private AccountSubTradeMapper accountSubTradeMapper;

    @Autowired
    private AccountSubCuMapper accountSubMapper;

    @Autowired
    private Auth auth;

    @Autowired
    private PABankService paBankService;

    @Autowired
    private AccountSubService accountSubService;

    @Autowired
    private EbpayProperties ebpayProperties;

    @Autowired
    private MsgCodeService msgCodeService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private LocalBankCardService localBankCardService;

    @Autowired
    private DictMapper dictMapper;


    @Transactional
    @Override
    public PaymentCodeForm transfer(TransferRequest request) {

        UserAccountInfoDto outAccount = accountSubMapper.getAccountInfoByNo(request.getOutSubAcctNo());
        if (outAccount == null) {
            throw new AnyException(ApiCodeEnum.OUT_ACCOUNT_NOT_FOUND);
        }

        UserAccountInfoDto inAccount = accountSubMapper.getAccountInfoByNo(request.getInSubAcctNo());
        if (inAccount == null) {
            throw new AnyException(ApiCodeEnum.IN_ACCOUNT_NOT_FOUND);
        }

        if (StringUtils.isBlank(outAccount.getFundSummaryAcctNo())) {
            throw new AnyException(ApiCodeEnum.ACCOUNT_NOT_FOUND);
        }

        request.setFunctionFlag("6");
        request.setCcy("RMB");
        request.setFundSummaryAcctNo(outAccount.getFundSummaryAcctNo());
        request.setOrderNo("LS" + new Date().getTime());
        request.setOutMemberCode(outAccount.getUserId());
        request.setInMemberCode(inAccount.getUserId());
        request.setAccountType(outAccount.getAccountType());
        request.setTranType("01");

        // 数据校验
        Map<String, String> validate = CommonUtil.validate(request);
        if (!validate.isEmpty()) {
            throw new AnyException(validate.values().toString());
        }

        // 检查转出账号是否有绑定银行卡
        BankCard bankCard = new BankCard();
        bankCard.setBankCardAccountSubId(request.getOutSubAcctNo());
        List<BankCard> bankCradList = localBankCardService.getBundBankCradList(bankCard);
        if (bankCradList.isEmpty()) {
            throw new AnyException("转出子账户没有绑定银行卡，请先绑定");
        }

        // 必须等校验完成后再格式化数据，舍入模式为：直接删除多余小数位
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        request.setTranAmt(decimalFormat.format(new BigDecimal(request.getTranAmt())));
        request.setTranFee(decimalFormat.format(new BigDecimal(request.getTranFee())));

        // 1 流水数据
        AccountSubTrade trade = saveTradeRecord(request, inAccount, outAccount);

        MemberTransactionsInspectionRequest send = new MemberTransactionsInspectionRequest();
        BeanUtils.copyProperties(request, send);

        return accountSubService.paymentCodeManage(trade.getAccountSubTradeId(), outAccount.getSubAcctNo(), "V", send, " ", ebpayProperties.getTradeCallbackUrl(), null);
    }

    @Override
    public void sendTransferRequest(AccountSubTrade request, String sign) {

        UserAccountInfoDto outAccount = accountSubMapper.getAccountById(request.getAccountSubTradeAccountSubId());
        UserAccountInfoDto inAccount = accountSubMapper.getAccountById(request.getAccountSubTradeInAccountSubId());

        AccountSubTrade trade = new AccountSubTrade();
        trade.setAccountSubTradeId(request.getAccountSubTradeId());
        trade.setAccountSubTradeTranDate(new Date());

        MemberTransactionsInspectionRequest send = new MemberTransactionsInspectionRequest();
        send.setFunctionFlag(request.getAccountSubTradeFeatureIdentifier().toString());
        send.setOutSubAcctNo(outAccount.getSubAcctNo());
        send.setFundSummaryAcctNo(outAccount.getFundSummaryAcctNo());
        send.setOutMemberCode(outAccount.getUserId());
        send.setOutSubAcctName(outAccount.getUserAccountName());
        send.setInSubAcctNo(inAccount.getSubAcctNo());
        send.setInMemberCode(inAccount.getUserId());
        send.setInSubAcctName(inAccount.getUserAccountName());
        send.setTranAmt(request.getAccountSubTradeTranAmt().toString());
        send.setTranFee(request.getAccountSubTradeTranFee().toString());
        send.setTranType(request.getAccountSubTradeTranType());
        send.setCcy(request.getAccountSubTradeCcy());
        send.setOrderNo(request.getAccountSubTradeNo());
        send.setRemark(request.getAccountSubTradeRemark());
        send.setWebSign(sign);
        try {
            MemberTransactionsInspectionResponse response = paBankService.memberTransactionsInspection(send);
            if (response == null) {
                throw new AnyException("请求异常");
            }
            if (!response.getTxnReturnCode().equals(Constants.PA_TRADE_SUCCESS_CODE)) {
                logger.error("转账请求被拒：{}  {}", response.getTxnReturnCode(), response.getTxnReturnMsg());
                throw new AnyException("转账失败");
            }
            trade.setAccountSubTradeBankNo(response.getCnsmrSeqNo());
            trade.setAccountSubTradeTranStatus(0);
            accountSubTradeMapper.updateByPrimaryKeySelective(trade);

            Thread thread = new Thread() {
                @Override
                public void run() {
                    if (BooleanUtils.isTrue(request.getAccountSubTradeIsSendSms())) {
                        String phone = accountSubService.getPhone(inAccount.getSubAcctId());
                        if (StringUtils.isNotBlank(phone)) {
                            msgCodeService.sendMsg(phone, getSMSContent(request.getAccountSubTradeTranAmt()));
                        }
                    }
                }
            };
            thread.start();

        } catch (Exception e) {
            logger.error("转账请求失败：" + e);
            trade.setAccountSubTradeTranStatus(1);
            accountSubTradeMapper.updateByPrimaryKeySelective(trade);
        }
    }

    @Override
    public Dict getPercent() {
        DictExample dictExample = new DictExample();
        dictExample.createCriteria().andDictTypeEqualTo("费率字典").andDictKeyEqualTo("转账手续费");
        return dictMapper.selectByExample(dictExample).stream().findFirst().orElse(null);
    }

    private String getSMSContent(BigDecimal accountSubTradeTranAmt) {
        return "收到转账" + accountSubTradeTranAmt + "元";
    }

    private AccountSubTrade saveTradeRecord(TransferRequest request, UserAccountInfoDto inAccount, UserAccountInfoDto outAccount) {

        String uuid = CommonUtil.uuid();
        Date now = new Date();

        request.setTradeId(uuid);

        AccountSubTrade trade = new AccountSubTrade();
        trade.setAccountSubTradeId(uuid);
        trade.setAccountSubTradeAccountSubId(outAccount.getSubAcctId());
        trade.setAccountSubTradeNo(request.getOrderNo());
        trade.setAccountSubTradeType(2);
        trade.setAccountSubTradeFeatureIdentifier(Integer.valueOf(request.getFunctionFlag()));
        trade.setAccountSubTradeTranAmt(new BigDecimal(request.getTranAmt()));
        trade.setAccountSubTradeTranFee(new BigDecimal(request.getTranFee()));
        trade.setAccountSubTradeTranDate(now);
        trade.setAccountSubTradeTranType("01");
        trade.setAccountSubTradeTranStatus(-1);
        trade.setAccountSubTradeTranPaymentType(1);
        trade.setAccountSubTradeCcy(request.getCcy());
        trade.setAccountSubTradeBankName(request.getAccountType() == 1 ? "平安银行" : "其他银行");
        trade.setAccountSubTradeRemark(request.getRemark());
        trade.setAccountSubTradeInAccountSubId(inAccount.getSubAcctId());
        trade.setAccountSubTradeIsSendSms(request.getSendSMS());

        trade.setAccountSubTradeCreateUserId(auth.getCurrentUser().getUserId());
        trade.setAccountSubTradeCreateTime(now);

        accountSubTradeMapper.insertSelective(trade);
        return trade;
    }

}
