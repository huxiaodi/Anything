package com.dllg.ebpay.service.bankCard.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dllg.common.CommonUtil;
import com.dllg.ebpay.dao.custom.ModifyAccountPhoneCuMapper;
import com.dllg.ebpay.dao.generator.*;
import com.dllg.ebpay.model.form.BindBankCardForm;
import com.dllg.ebpay.model.form.ConfirmBankCardForm;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.service.bankCard.BankCardService;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.pabank.model.request.*;
import com.dllg.pabank.model.response.*;
import com.dllg.pabank.service.PABankService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 16:40 2018/3/28
 */
@Service
@Transactional
public class BankCardServiceImple  implements BankCardService{
    @Autowired
    private Auth auth;
    @Autowired
    private AccountSubMapper accountSubMapper;
    @Autowired
    private PubPayBankMapper pubPayBankMapper;
    @Autowired
    private SuperInternetBankMapper superInternetBankMapper;
    @Autowired
    private PABankService paBankService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private UserMapper  userMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private BankCardMapper bankCardMapper;
    @Autowired
    private PubPayNodeMapper pubPayNodeMapper;
    @Autowired
    private PubPayCityMapper pubPayCityMapper;
    @Autowired
    private ZjjzCnapsBankinfoMapper zjjzCnapsBankinfoMapper;
    @Autowired
    private ModifyAccountPhoneCuMapper modifyAccountPhoneCuMapper;

    @Override
    public List<PubPayBank> getBankCardBankList() {
        PubPayBankExample pubPayBankExample = new PubPayBankExample();
        AuthUser authUser = auth.getCurrentUser();
        if(authUser.getUserType().equals(1)){
            pubPayBankExample.createCriteria().andBankTargetEqualTo(1);
        }
        return pubPayBankMapper.selectByExample(pubPayBankExample);
    }

    @Override
    public List<SuperInternetBank> getBankCardBankListSuper() {
        SuperInternetBankExample superInternetBankExample = new SuperInternetBankExample();
        AuthUser authUser = auth.getCurrentUser();
        if(authUser.getUserType().equals(1)){
            superInternetBankExample.createCriteria().andBankTargetEqualTo(1);
        }
        return superInternetBankMapper.selectByExample(superInternetBankExample);
    }

    @Override
    public BankCard checkBankCardNo(String bankCardNo) {
        BankCardExample bankCardExample = new BankCardExample();
        bankCardExample.createCriteria().andBankCardNoEqualTo(bankCardNo).andBankCardStatusNotIn(Arrays.asList(3)).andBankCardIsDeleteEqualTo(false);
        BankCard bankCard =bankCardMapper.selectByExample(bankCardExample).stream().findFirst().orElse(null);
        return bankCard;
    }

    @Override
    public BankCard sumitBankCard(BindBankCardForm bindBankCardForm) throws Exception {
        String message ="",msgCn="";
        String bankCnsmrSeqNo = "";
        AuthUser authUser = auth.getCurrentUser();
        User user =userMapper.selectByPrimaryKey(authUser.getUserId());
        Company company = null;
        AccountSub accountSub = accountSubMapper.selectByPrimaryKey(bindBankCardForm.getAccountSubId());
        Account account =accountMapper.selectByPrimaryKey(accountSub.getAccountSubAccountId());


        if(authUser.getUserType().equals(1)){ //个人 用银联鉴权  BindRelateAcctUnionPay
            BindRelateAcctUnionPayRequest bindRelateAcctUnionPayRequest = new BindRelateAcctUnionPayRequest();
            bindRelateAcctUnionPayRequest.setFundSummaryAcctNo(account.getAccountFundSummaryAccountNo());
            bindRelateAcctUnionPayRequest.setSubAcctNo(accountSub.getAccountSubNo());
            if(bindBankCardForm.getBankCardTransactionAmountType() != null && bindBankCardForm.getBankCardTransactionAmountType().equals(1)){ // >5万 大小额 有支行
                bindRelateAcctUnionPayRequest.setAcctOpenBranchName(bindBankCardForm.getBankCardBankBranchName()); //
                bindRelateAcctUnionPayRequest.setCnapsBranchId(bindBankCardForm.getBankCardBankBranch());

            }else if(bindBankCardForm.getBankCardTransactionAmountType() != null && bindBankCardForm.getBankCardTransactionAmountType().equals(2)){  // <5万 超级网银 无支行
                bindRelateAcctUnionPayRequest.setAcctOpenBranchName(bindBankCardForm.getAccountName()); //
                bindRelateAcctUnionPayRequest.setEiconBankBranchId(bindBankCardForm.getBankCardBank());
            }
            if(bindBankCardForm.getBankCardBankName().contains("平安银行")){
                bindRelateAcctUnionPayRequest.setBankType("1");
            }else{
                bindRelateAcctUnionPayRequest.setBankType("2");
            }
            bindRelateAcctUnionPayRequest.setMemberAcctNo(bindBankCardForm.getBankCardNo()); // 银行账号
            bindRelateAcctUnionPayRequest.setMemberGlobalId(user.getUserCertificateNumber());  // 会员证件号码
            bindRelateAcctUnionPayRequest.setMemberGlobalType("1"); // 会员证件类型  个人固定身份证传1
            bindRelateAcctUnionPayRequest.setReservedMsg("");  // 保留域
            bindRelateAcctUnionPayRequest.setTranNetMemberCode(authUser.getUserId()); // 交易网会员代码
            bindRelateAcctUnionPayRequest.setMemberName(authUser.getUserCnName()); // 会员名称
            bindRelateAcctUnionPayRequest.setMobile(bindBankCardForm.getMobilePhone());
            BindRelateAcctUnionPayResponse custAcctIdBalanceQueryResponse = paBankService.bindRelateAcctUnionPay(bindRelateAcctUnionPayRequest);
            System.out.println(JSON.toJSON(custAcctIdBalanceQueryResponse));
            if("000000".equals(custAcctIdBalanceQueryResponse.getTxnReturnCode())){
                message= "success";
            }
            if("ERR145".equals(custAcctIdBalanceQueryResponse.getTxnReturnCode())  || "ERR135".equals(custAcctIdBalanceQueryResponse.getTxnReturnCode()) ){
                message = "verification";
            }
            msgCn = custAcctIdBalanceQueryResponse.getTxnReturnMsg();  //返回接口消息
        }else if(authUser.getUserType().equals(2)){ // 企业用 小额鉴权 BindRelateAcctSmallAmount

            company =companyMapper.selectByPrimaryKey(user.getUserCompanyId());
            BindRelateAcctSmallAmountRequest bindRelateAcctSmallAmountRequest = new BindRelateAcctSmallAmountRequest();
            bindRelateAcctSmallAmountRequest.setFundSummaryAcctNo(account.getAccountFundSummaryAccountNo());
            bindRelateAcctSmallAmountRequest.setSubAcctNo(accountSub.getAccountSubNo());
            if(bindBankCardForm.getBankCardTransactionAmountType() != null && bindBankCardForm.getBankCardTransactionAmountType().equals(1)){ // >5万 大小额 有支行
                bindRelateAcctSmallAmountRequest.setAcctOpenBranchName(bindBankCardForm.getBankCardBankBranchName()); //
                bindRelateAcctSmallAmountRequest.setCnapsBranchId(bindBankCardForm.getBankCardBankBranch());
            }else if(bindBankCardForm.getBankCardTransactionAmountType() != null && bindBankCardForm.getBankCardTransactionAmountType().equals(2)){ // <5万 超级网银 无支行
                bindRelateAcctSmallAmountRequest.setAcctOpenBranchName(bindBankCardForm.getBankCardBankName()); //
                bindRelateAcctSmallAmountRequest.setEiconBankBranchId(bindBankCardForm.getBankCardBank());
            }
            if(bindBankCardForm.getBankCardBankName().contains("平安银行")){
                bindRelateAcctSmallAmountRequest.setBankType("1");
            }else{
                bindRelateAcctSmallAmountRequest.setBankType("2");
            }
            bindRelateAcctSmallAmountRequest.setMemberAcctNo(bindBankCardForm.getBankCardNo()); // 银行账号

            if(company.getCompanyIsThreeInOne()==true){ //三证合一传统一信用代码
                bindRelateAcctSmallAmountRequest.setMemberGlobalType("5"); // 统一信用代码
                bindRelateAcctSmallAmountRequest.setMemberGlobalId(company.getCompanyUnifiedSocialCreditLdentifier());

            }else { //传企业营业执照
                bindRelateAcctSmallAmountRequest.setMemberGlobalType("68"); // 营业执照
                bindRelateAcctSmallAmountRequest.setMemberGlobalId(company.getCompanyLicenseNo());
            }

            bindRelateAcctSmallAmountRequest.setMobile(bindBankCardForm.getMobilePhone());
            bindRelateAcctSmallAmountRequest.setReservedMsg("");  // 保留域
            bindRelateAcctSmallAmountRequest.setTranNetMemberCode(authUser.getUserCompanyId()); // 交易网会员代码
            bindRelateAcctSmallAmountRequest.setMemberName(authUser.getUserCompanyName()); // 会员名称
            BindRelateAcctSmallAmountResponse bindRelateAcctSmallAmountResponse = paBankService.bindRelateAcctSmallAmount(bindRelateAcctSmallAmountRequest);
            System.out.println(JSON.toJSON(bindRelateAcctSmallAmountResponse));
            if("000000".equals(bindRelateAcctSmallAmountResponse.getTxnReturnCode())){
                message= "success";
            }
            if("ERR145".equals(bindRelateAcctSmallAmountResponse.getTxnReturnCode())  || "ERR135".equals(bindRelateAcctSmallAmountResponse.getTxnReturnCode())){
                message= "verification";
            }
            bankCnsmrSeqNo = bindRelateAcctSmallAmountResponse.getCnsmrSeqNo();
            msgCn = bindRelateAcctSmallAmountResponse.getTxnReturnMsg(); //返回接口消息
        }
        //保存银行卡信息到本地
        if("success".equals(message)){
            BankCard bankCard = new BankCard();
            BeanUtils.copyProperties(bindBankCardForm,bankCard);
            bankCard.setBankCardId(CommonUtil.uuid());
            bankCard.setBankCardAccountSubId(bindBankCardForm.getAccountSubId());
            bankCard.setBankCardMasterType(user.getUserType()== 1 ? 2 : 1); //如果为个人用户 则插入2  其他插入 1
            bankCard.setBankCardReserveMobile(user.getUserType()== 1 ? user.getUserMobile() : company.getCompanyContactTel());
            bankCard.setBankCardDocumentType(user.getUserCertificateType());
            bankCard.setBankCardDocumentNo(user.getUserCertificateNumber());
            bankCard.setBankCardStatus(1);
            bankCard.setBankCardCreateUserId(user.getUserId());
            bankCard.setBankCardCreateTime(new Date());
            bankCard.setBankCardIsDelete(false);
            bankCard.setBankCardReserveBankAccountName(bindBankCardForm.getAccountName());
            bankCard.setBankCardBankBranch(bindBankCardForm.getBankCardBankBranchName());
            bankCard.setBankCardBank(bindBankCardForm.getBankCardBankName());
            bankCard.setBankLogo(bindBankCardForm.getBankLogo());
            bankCard.setBankCnsmrSeqNo(bankCnsmrSeqNo);

            BankCardExample bankCardExample = new BankCardExample();
            bankCardExample.createCriteria().andBankCardNoEqualTo(bankCard.getBankCardNo())
                    .andBankCardAccountSubIdEqualTo(bankCard.getBankCardAccountSubId())
                    .andBankCardStatusIn(Arrays.asList(4)).andBankCardIsDeleteEqualTo(false);
            bankCardMapper.deleteByExample(bankCardExample);
            bankCardMapper.insert(bankCard);

            //把新的银行卡预留手机号更新到子账户中(account_transaction_phone - 子账户交易手机号)字段中

            AccountSub ac = new AccountSub();
                       ac.setAccountSubNo(auth.getCurrentUser().getAccountSubNo());      //子账号号码
                       ac.setAccountTransactionPhone(bindBankCardForm.getMobilePhone()); //子账户交易手机号
                       ac.setAccountSubCreateUserId(user.getUserId());                   //创建人id
            modifyAccountPhoneCuMapper.setAccountSubPhone(ac);

            return bankCard;
        }
        if("verification".equals(message)){
            BankCardExample bankCardExample = new BankCardExample();
            bankCardExample.createCriteria().andBankCardAccountSubIdEqualTo(bindBankCardForm.getAccountSubId()).andBankCardNoEqualTo(bindBankCardForm.getBankCardNo()).andBankCardStatusEqualTo(1).andBankCardIsDeleteEqualTo(false);
            BankCard bankCard = bankCardMapper.selectByExample(bankCardExample).stream().findFirst().orElse(null);
            return bankCard;
        }
        BankCard bc = new BankCard();
        bc.setBankCardBank(msgCn);        //返回调用结果信息
        bc.setBankCardBankBranch("error");
        return bc;
    }


    @Override
    public BankCard submitconfirmData(ConfirmBankCardForm confirmBankCardForm) throws Exception {
        String message ="",msgCn="";
        AuthUser authUser =auth.getCurrentUser();
        BankCard bankCard = bankCardMapper.selectByPrimaryKey(confirmBankCardForm.getBankCardId());
        AccountSub accountSub = accountSubMapper.selectByPrimaryKey(confirmBankCardForm.getAccountSubId());
        Account account =accountMapper.selectByPrimaryKey(accountSub.getAccountSubAccountId());
        if(authUser.getUserType().equals(2)){ // 企业 大小额
            CheckAmountRequest checkAmountRequest = new CheckAmountRequest();
            checkAmountRequest.setFundSummaryAcctNo(account.getAccountFundSummaryAccountNo());
            checkAmountRequest.setSubAcctNo(accountSub.getAccountSubNo());
            checkAmountRequest.setTranNetMemberCode(authUser.getUserCompanyId()); // 交易网会员代码
            checkAmountRequest.setTakeCashAcctNo(bankCard.getBankCardNo()); // 提现账号
            checkAmountRequest.setAuthAmt(confirmBankCardForm.getAuthentication()); // 鉴权金额 todo
            checkAmountRequest.setCcy("RMB");
            checkAmountRequest.setReservedMsg("");
            CheckAmountResponse checkAmountResponse = paBankService.checkAmount(checkAmountRequest);
            if("000000".equals(checkAmountResponse.getTxnReturnCode())){
                message = "success";
            }
            msgCn = checkAmountResponse.getTxnReturnMsg(); //返回接口消息
        }else{ // 个人 银联
            BindRelateAccReUnionPayRequest bindRelateAccReUnionPayRequest = new BindRelateAccReUnionPayRequest();
            bindRelateAccReUnionPayRequest.setFundSummaryAcctNo(account.getAccountFundSummaryAccountNo());
            bindRelateAccReUnionPayRequest.setSubAcctNo(accountSub.getAccountSubNo());
            bindRelateAccReUnionPayRequest.setTranNetMemberCode(authUser.getUserId()); // 交易网会员代码
            bindRelateAccReUnionPayRequest.setMemberAcctNo(confirmBankCardForm.getBankCardNo()); // 会员账号 todo
            bindRelateAccReUnionPayRequest.setMessageCheckCode(confirmBankCardForm.getVerification()); // 短信验证码  todo
            bindRelateAccReUnionPayRequest.setReservedMsg("");
            BindRelateAccReUnionPayResponse bindRelateAccReUnionPayResponse = paBankService.bindRelateAccReUnionPay(bindRelateAccReUnionPayRequest);
            if("000000".equals(bindRelateAccReUnionPayResponse.getTxnReturnCode())){
                message = "success";
            }
            msgCn = bindRelateAccReUnionPayResponse.getTxnReturnMsg(); //返回接口消息
        }
        if("success".equals(message)){
            bankCard.setBankCardBindBankCardDate(new Date());
            bankCard.setBankCardStatus(2);
            bankCardMapper.updateByPrimaryKeySelective(bankCard);

            BankCard bc = new BankCard();
            bc.setBankCardBank(msgCn);        //返回调用结果信息
            bc.setBankCardBankBranch("success");
            return bc;
        }

        BankCard bc = new BankCard();
        bc.setBankCardBank(msgCn);        //返回调用结果信息
        bc.setBankCardBankBranch("error");

        return bc;
    }

    @Override
    public SmallAmountTransferQueryResponse querySmallAmount(String accountSubId, String bankCardNo) throws  Exception{
        Map<String, Object> map = new HashMap<>();
        BankCardExample bankCardExample = new BankCardExample();
        bankCardExample.createCriteria().andBankCardAccountSubIdEqualTo(accountSubId).andBankCardNoEqualTo(bankCardNo.replaceAll("\\s*","")).andBankCardStatusEqualTo(1);
        BankCard bankCard = bankCardMapper.selectByExample(bankCardExample).stream().findFirst().orElse(null);
        AccountSub accountSub = accountSubMapper.selectByPrimaryKey(bankCard.getBankCardAccountSubId());
        Account account =accountMapper.selectByPrimaryKey(accountSub.getAccountSubAccountId());
        SmallAmountTransferQueryRequest smallAmountTransferQueryRequest = new SmallAmountTransferQueryRequest();
        smallAmountTransferQueryRequest.setFundSummaryAcctNo(account.getAccountFundSummaryAccountNo());
        smallAmountTransferQueryRequest.setOldTranSeqNo(bankCard.getBankCnsmrSeqNo());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        smallAmountTransferQueryRequest.setTranDate(simpleDateFormat.format(bankCard.getBankCardCreateTime()));
        SmallAmountTransferQueryResponse smallAmountTransferQueryResponse = paBankService.smallAmountTransferQuery(smallAmountTransferQueryRequest);
        if("000000".equals(smallAmountTransferQueryResponse.getTxnReturnCode())){
            return smallAmountTransferQueryResponse;
        }else{
            return null;
        }

    }

    @Override
    public List<PubPayNode> getProvince() {
        return pubPayNodeMapper.selectByExample(null);
    }

    @Override
    public List<PubPayCity> getCity(PubPayNode pubPayNode) {
        PubPayCityExample pubPayCityExample =  new PubPayCityExample();
        pubPayCityExample.createCriteria().andCityNodecodeEqualTo(pubPayNode.getNodeNodecode()).andCityAreatypeEqualTo("2");
        return pubPayCityMapper.selectByExample(pubPayCityExample);
    }

    @Override
    public List<PubPayCity> getDistrict(PubPayCity pubPayCity) {
        PubPayCityExample pubPayCityExample =  new PubPayCityExample();
        pubPayCityExample.createCriteria().andCityTopareacode2EqualTo(pubPayCity.getCityAreacode()).andCityAreatypeIn(Arrays.asList("2","3"));
        return pubPayCityMapper.selectByExample(pubPayCityExample);
    }

    @Override
    public List<ZjjzCnapsBankinfo> getBankCardBankBranch(ZjjzCnapsBankinfo zjjzCnapsBankinfo) {
        ZjjzCnapsBankinfoExample zjjzCnapsBankinfoExample = new ZjjzCnapsBankinfoExample();
        zjjzCnapsBankinfoExample.createCriteria().andBankclscodeEqualTo(zjjzCnapsBankinfo.getBankclscode()).andCitycodeEqualTo(zjjzCnapsBankinfo.getCitycode().substring(0,4));
        return zjjzCnapsBankinfoMapper.selectByExample(zjjzCnapsBankinfoExample);
    }
}
