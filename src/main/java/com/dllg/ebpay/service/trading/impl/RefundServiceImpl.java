package com.dllg.ebpay.service.trading.impl;

import com.dllg.common.CommonUtil;
import com.dllg.common.DlebException;
import com.dllg.common.HttpInteractionUtils;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.custom.RefundCuMapper;
import com.dllg.ebpay.dao.generator.AccountSubMapper;
import com.dllg.ebpay.dao.generator.AccountSubTradeMapper;
import com.dllg.ebpay.dao.generator.RefundMapper;
import com.dllg.ebpay.model.form.RefundForm;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.model.generator.Refund;
import com.dllg.ebpay.service.pay.PayService;
import com.dllg.ebpay.service.dict.DictService;
import com.dllg.ebpay.service.trading.RefundService;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.pabank.model.request.MemberTransactionRefundRequest;
import com.dllg.pabank.model.response.MemberTransactionRefundResponse;
import com.dllg.pabank.service.PABankService;
import com.ecc.emp.data.KeyedCollection;
import com.github.pagehelper.PageHelper;
import org.omg.CORBA.Any;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RefundServiceImpl implements RefundService{

    @Autowired
    private RefundCuMapper refundCuMapper;
    @Autowired
    private AccountSubMapper accountSubMapper;
    @Autowired
    private Auth auth;
    @Autowired
    private DictService dictService;
    @Autowired
    private RefundMapper refundMapper;
    @Autowired
    private AccountSubTradeMapper accountSubTradeMapper;
    @Autowired
    private PABankService paBankService;
    @Autowired
    private EbpayProperties ebpayProperties;
    @Autowired
    private PayService payService;
    @Autowired
    private HttpInteractionUtils httpInteractionUtils;

    @Override
    public List getRefund(RefundForm refundForm) {
        AuthUser authUser = auth.getCurrentUser();
        Integer userType = authUser.getUserType();
        String userId = authUser.getUserId();
        if(userType == 2){
            userId = authUser.getUserCompanyId();
        }
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubRelationIdEqualTo(userId);
        AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        if(accountSub == null){
            throw new AnyException("账号异常，获取数据失败！");
        }
        refundForm.setAccountSubId(accountSub.getAccountSubId());
        PageHelper.startPage(refundForm);
        return refundCuMapper.RefundList(refundForm);
    }

    @Override
    public Map<String, String> getRefundDetail(String refundId) {
        return refundCuMapper.getRefundDetail(refundId);
    }

    @Override
    public void applicationfordrawback(RefundForm refundForm) throws DlebException {
        //判断退款申请是否重复
        RefundExample refundExample = new RefundExample();
        refundExample.createCriteria().andRefundTradeBankNoEqualTo(refundForm.getRefundTradeBankNo()).andRefundStatusNotEqualTo("5");
        Refund refund = refundMapper.selectByExample(refundExample).stream().findFirst().orElse(null);
        if(refund == null){
            //判断流水号是否存在
            AccountSubTradeExample accountSubTradeExample = new AccountSubTradeExample();
            accountSubTradeExample.createCriteria().andAccountSubTradeBankNoEqualTo(refundForm.getRefundTradeBankNo()).andAccountSubTradeTypeEqualTo(1);
            List<AccountSubTrade> accountSubTrades = accountSubTradeMapper.selectByExample(accountSubTradeExample);
            if(accountSubTrades == null || accountSubTrades.size() == 0){
                throw new DlebException("流水号不存在！");
            }
            refund = new Refund();
            BeanUtils.copyProperties(refundForm,refund);
            refund.setRefundId(CommonUtil.uuid());
            refund.setRefundStatus("0");
            refundMapper.insertSelective(refund);
        }else{
            throw new DlebException("退款申请已存在！");
        }
    }


    public void drawBack(String refundTradeBankNo) throws Exception{
        RefundExample refundExample = new RefundExample();
        refundExample.createCriteria().andRefundTradeBankNoEqualTo(refundTradeBankNo);
        List<Refund> refunds = refundMapper.selectByExample(refundExample);
        if(refunds != null && refunds.size() > 0) {
            Refund refund = refunds.get(0);
            AccountSubTradeExample accountSubTradeExample = new AccountSubTradeExample();
            accountSubTradeExample.createCriteria().andAccountSubTradeBankNoEqualTo(refund.getRefundTradeBankNo());
            AccountSubTrade accountSubTrade = accountSubTradeMapper.selectByExample(accountSubTradeExample).stream().findFirst().orElse(null);
            if("0".equals(accountSubTrade.getAccountIsDeal())){
                drawBackForTopUp(refund,accountSubTrade);
            }else{
                drawBackForDeal(refund,accountSubTrade);
            }
        }
    }


    /**
     * 会员间交易退款
     * @param
     */
    public void drawBackForDeal(Refund refund,AccountSubTrade accountSubTrade) throws Exception{
        if(!"3".equals(refund.getRefundStatus())){
            throw new DlebException("退款申请状态错误，无法退款！");
        }
        AccountSub outAccount = accountSubMapper.selectByPrimaryKey(accountSubTrade.getAccountSubTradeAccountSubId());
        AccountSub inAccount = accountSubMapper.selectByPrimaryKey(accountSubTrade.getAccountSubTradeInAccountSubId());
        MemberTransactionRefundRequest memberTransactionRefundRequest = new MemberTransactionRefundRequest();
        memberTransactionRefundRequest.setFunctionFlag("2");
        memberTransactionRefundRequest.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());
        memberTransactionRefundRequest.setOldTranSeqNo(accountSubTrade.getAccountSubTradeBankNo());
        memberTransactionRefundRequest.setOldOutSubAcctNo(outAccount.getAccountSubNo());
        memberTransactionRefundRequest.setOldOutMemberCode(outAccount.getAccountSubRelationId());
        memberTransactionRefundRequest.setOldInSubAcctNo(inAccount.getAccountSubNo());
        memberTransactionRefundRequest.setOldInMemberCode(inAccount.getAccountSubRelationId());
        memberTransactionRefundRequest.setOldOrderNo(accountSubTrade.getAccountSubTradeNo());
        memberTransactionRefundRequest.setReturnAmt(accountSubTrade.getAccountSubTradeTranAmt().toPlainString());
        memberTransactionRefundRequest.setReturnCommission("0");
        MemberTransactionRefundResponse memberTransactionRefundResponse = paBankService.memberTransactionRefund(memberTransactionRefundRequest);
        if("000000".equals(memberTransactionRefundResponse.getTxnReturnCode())){
            refund.setRefundStatus("4");
            refund.setRefundBankNo(memberTransactionRefundResponse.getFrontSeqNo());
            refundMapper.updateByPrimaryKeySelective(refund);
        }else{
            refund.setRefundStatus("6");
            refund.setRefundBankNo(memberTransactionRefundResponse.getFrontSeqNo());
            refund.setRefundFalseReason(memberTransactionRefundResponse.getTxnReturnMsg());
            refundMapper.updateByPrimaryKeySelective(refund);
            throw new DlebException(memberTransactionRefundResponse.getTxnReturnMsg());
        }
    }
    //回调对接平台
    public void send(String refundTradeBankNo){
        RefundExample refundExample = new RefundExample();
        refundExample.createCriteria().andRefundTradeBankNoEqualTo(refundTradeBankNo);
        List<Refund> refunds = refundMapper.selectByExample(refundExample);
        if(refunds != null && refunds.size() > 0) {
            Refund refund = refunds.get(0);
            Map<String, Object> map = new HashMap<>();
            map.put("refundTradeBankNo",refund.getRefundTradeBankNo());
            map.put("remark",refund.getRefundRemark());
            if("4".equals(refund.getRefundStatus())){
                map.put("remark",refund.getRefundRemark());
                map.put("status","01");
            }else if("5".equals(refund.getRefundStatus())){
                map.put("remark",refund.getRefundRemark());
                map.put("status","03");
                map.put("reason",refund.getRefundFalseReason());
            }else if("6".equals(refund.getRefundStatus())){
                map.put("remark",refund.getRefundRemark());
                map.put("status","02");
                map.put("reason",refund.getRefundFalseReason());
            }else{
                return;
            }
            MappingJacksonValue value = new MappingJacksonValue(map);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<MappingJacksonValue> httpEntity = new HttpEntity<MappingJacksonValue>(value, httpHeaders);

            boolean isSend = true;
            int sendCount = 1;
            while(isSend && sendCount < 3){
                String post = httpInteractionUtils.post(refund.getRefundBackUrl(), httpEntity, String.class, new HashMap<>());
                sendCount++;
                if("success".equals(post.toLowerCase())){
                    isSend = false;
                }
            }
        }
    }

    /**
     * 支付退款
     * @param
     */
    public void drawBackForTopUp (Refund refund,AccountSubTrade accountSubTrade) throws Exception{
        payService.sf0005(refund,accountSubTrade);
    }
     @Override
    public void refundAudit(Map<String, String> map) {
        String refundId = map.get("refundId");
        String type = map.get("type"); // 审核类型
        Integer isPass = Integer.valueOf(map.get("isPass"));
        Refund refund = refundMapper.selectByPrimaryKey(refundId);
        if(refund == null){
            throw new AnyException("参数错误，请稍后再试");
        }
        if(!refund.getRefundStatus().equals(type)){
            throw new AnyException("单据状态已发生变化，请刷新后再次尝试");
        }
        refund.setRefundFalseReason(map.get("reason"));
        refund.setRefundUpdateData(new Date());
        if (0 == isPass){
            refund.setRefundStatus("5");
            send(refund.getRefundTradeBankNo());
        } else {
            if("0".equals(refund.getRefundStatus())){
                refund.setRefundStatus("1");
            }else if ("1".equals(refund.getRefundStatus())){
                List<Dict> list =  dictService.getDictByType("退款额度字典");
                if(list == null || list.size() != 1){
                    throw new AnyException("退款额度字典设定有误");
                }
                BigDecimal decimal = new BigDecimal(list.get(0).getDictValue());
                if (refund.getRefundAmount().compareTo(decimal) >= 1) {
                    refund.setRefundStatus("2");
                } else {
                    refund.setRefundStatus("3");
                }
            }else if ("2".equals(refund.getRefundStatus())){
                refund.setRefundStatus("3");
            }
        }
        refundMapper.updateByPrimaryKeySelective(refund);
    }

    @Override
    public Map<String, Object> queryRefundStatus (String orderNo) throws  Exception{
        RefundExample refundExample = new RefundExample();
        refundExample.createCriteria().andRefundOrderNoEqualTo(orderNo);
        Refund refund = refundMapper.selectByExample(refundExample).stream().findFirst().orElse(null);
        Map<String, Object> map = new HashMap<>();
        if(refund != null){
            if("0".equals(refund.getRefundStatus()) || "1".equals(refund.getRefundStatus())
                    ||"2".equals(refund.getRefundStatus()) || "3".equals(refund.getRefundStatus())){
                map.put("status","04");
            }else if("4".equals(refund.getRefundStatus()) ){
                map.put("status","01");
            }else if("5".equals(refund.getRefundStatus())){
                map.put("status","03");
            }else if("6".equals(refund.getRefundStatus())){
                map.put("status","02");
            }
            map.put("orderNo",orderNo);
            map.put("reason",refund.getRefundFalseReason());
        }else{
            throw new DlebException("单号不存在！");
        }
        return map;
    }
}
