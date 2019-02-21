package com.dllg.ebpay.service.accountSub.impl;

import com.alibaba.fastjson.JSON;
import com.dllg.common.CommonUtil;
import com.dllg.ebpay.dao.custom.HangingAccountCuMapper;
import com.dllg.ebpay.dao.generator.AccountMapper;
import com.dllg.ebpay.dao.generator.AccountSubMapper;
import com.dllg.ebpay.dao.generator.AccountSubTradeMapper;
import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.dto.AccountSubTradeDto;
import com.dllg.ebpay.model.dto.UserAccountInfoDto;
import com.dllg.ebpay.model.form.AdjustAccountSubForm;
import com.dllg.ebpay.model.form.HangingAccountListFrom;
import com.dllg.ebpay.model.form.InSubAcctForm;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.service.accountSub.HangingAccountService;
import com.dllg.ebpay.service.role.RoleService;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.security.Auth;
import com.dllg.framework.utils.SecurityUtils;
import com.dllg.pabank.model.request.BankWithdrawCashDetailsQueryRequest;
import com.dllg.pabank.model.request.CommonTransferRechargeQueryRequest;
import com.dllg.pabank.model.request.RegisterBillRequest;
import com.dllg.pabank.model.response.BankWithdrawCashDetailsQueryResponse;
import com.dllg.pabank.model.response.CommonTransferRechargeQueryResponse;
import com.dllg.pabank.model.response.RegisterBillResponse;
import com.dllg.pabank.service.PABankService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 10:31 2018/4/12
 */
@Service
@Transactional
public class HangingAccountServiceImpl implements HangingAccountService{

    private final static Logger logger = LoggerFactory.getLogger(HangingAccountServiceImpl.class);

    @Autowired
    private PABankService paBankService ;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccountMapper  accountMapper;

    @Autowired
    private HangingAccountCuMapper hangingAccountCuMapper;

    @Autowired
    private AccountSubMapper accountSubMapper;

    @Autowired
    private AccountSubTradeMapper accountSubTradeMapper;

    @Autowired
    private Auth auth;

    @Override
    public List<AccountSubTradeDto> getHangingAccountList(HangingAccountListFrom hangingAccountListFrom) throws Exception{
        getHangingAccountListByBank();
        hangingAccountListFrom.startPage();
        List<AccountSubTradeDto> accountSubTradeList = hangingAccountCuMapper.getHangingAccountList(hangingAccountListFrom);
        return accountSubTradeList;
    }
    @Override
    public void getHangingAccountListByBank() throws Exception{
        List<AccountSubTradeDto> accountSubTradeList = hangingAccountCuMapper.getHangingAccountList(null); // 查询出总的数据
        for(int i= 1 ;;i++){
            CommonTransferRechargeQueryRequest commonTransferRechargeQueryRequest = new CommonTransferRechargeQueryRequest();
            // 得到资金总账户号码
            AccountExample accountExample = new AccountExample();
            accountExample.createCriteria().andAccountTypeEqualTo(1).andAccountIsDeleteEqualTo(false);
            Account account =accountMapper.selectByExample(accountExample).stream().findFirst().orElse(null);
            if(account != null){
                commonTransferRechargeQueryRequest.setFundSummaryAcctNo(account.getAccountFundSummaryAccountNo());
            }
            // 设置参数
            commonTransferRechargeQueryRequest.setEndDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            commonTransferRechargeQueryRequest.setFunctionFlag("0");
            // 设置查询页数
            commonTransferRechargeQueryRequest.setPageNum(String.valueOf(i));
            commonTransferRechargeQueryRequest.setReservedMsg("");
            commonTransferRechargeQueryRequest.setStartDate("20100412");
            // 发送请求
            CommonTransferRechargeQueryResponse custAcctIdBalanceQueryResponse = paBankService.commonTransferRechargeQuery(commonTransferRechargeQueryRequest);
            System.out.println(JSON.toJSONString(custAcctIdBalanceQueryResponse));
            // 请求成功
            if(custAcctIdBalanceQueryResponse != null && "000000".equals(custAcctIdBalanceQueryResponse.getTxnReturnCode())){
                String  tranItemArray = custAcctIdBalanceQueryResponse.getTranItemArray(); // 得到当前页面的条数
                List list = JSON.parseArray(tranItemArray,HashMap.class);//<HashMap<String,String>>
                boolean breakInfo = false;
                for(int j= 0 ;j<list.size();j++){

                    HashMap<String ,String> map = (HashMap)list.get(j);

                    //只处理挂账数据
                    if("03".equals(map.get("InAcctType"))){
                        if("262015000010".equals(map.get("InAcctNo")) && "跨行支付本金清算".equals(map.get("Remark"))){
                            continue;
                        }
                        boolean insertInfo = false;
                        // 判断该条数据已经存在了数据库中
                        if(accountSubTradeList != null && accountSubTradeList.size()>0){
                            List<String> stringList = accountSubTradeList.stream().map(e ->e.getAccountSubTradeNo()).collect(Collectors.toList());
                            if(!stringList.contains(map.get("FrontSeqNo"))){
                                insertInfo= true;
                            }
                        }else{
                            insertInfo= true;
                        }
                        // 根据是否存在 决定是否插入
                        if(insertInfo){ // 不存在
                            AccountSubTrade accountSubTrade = new AccountSubTrade();
                            accountSubTrade.setAccountSubTradeId(CommonUtil.uuid());

                            accountSubTrade.setAccountSubTradeTranType("01");
                            accountSubTrade.setAccountSubTradeTranPaymentType(2);
                            accountSubTrade.setAccountSubTradeCcy("RMB");
                            accountSubTrade.setAccountSubTradeCreateTime(new Date());

                            accountSubTrade.setAccountSubTradeTranDate(new SimpleDateFormat("yyyyMMdd").parse(map.get("AccountingDate")));// '交易时间',
                            accountSubTrade.setAccountSubTradeNo(map.get("FrontSeqNo"));
                            accountSubTrade.setAccountSubTradeTranAmt(new BigDecimal(map.get("TranAmt")));//交易金额（元
                            accountSubTrade.setAccountSubTradeBankAccount(map.get("InAcctName")); // 账户名称（挂账）
                            accountSubTrade.setAccountSubTradeBankNum(map.get("InAcctNo")); // '银行卡号(挂账)',

                            //accountSubTrade.setAccountSubTradeAccountingDate();//会计日期
                            accountSubTrade.setAccountSubTradeBankName(map.get("BankName"));// account_sub_trade_bank_name 银行名称（付款账户银行名称
                            accountSubTrade.setAccountSubTradeBankRemark(map.get("Remark"));//  `account_sub_trade_remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
                            accountSubTrade.setAccountSubTradeAdjustTime(null);
                            if("03".equals(map.get("InAcctType"))){ // 03：资金挂账
                                accountSubTrade.setAccountSubTradeType(7); //挂账
                                accountSubTrade.setAccountSubTradeTranStatus(7); // 交易状态
                                accountSubTrade.setAccountSubTradeInAccountSubId("");
                                accountSubTrade.setAccountSubTradeAccountSubId("");
                            }

                            accountSubTradeMapper.insertSelective(accountSubTrade);
                        }else{
                            // 存在 说明之后的数据 都是已经有的
                            breakInfo = true;
                            break; // 跳出循环
                        }
                    }

                }
                if(breakInfo){
                    break;
                }
                if(!"0".equals(custAcctIdBalanceQueryResponse.getEndFlag())){  // 最后一页 结束循环
                    break;
                }
            } else{ // 最后一也 跳出for循环
                break;
            }

        }
    }

    @Override
    public void getHangingAccountListBySubAccount() throws Exception{
//        logger.info("-------------- 平安银行子账户交易结果查询 -----------------");
//        //查询所有子账户
//        AccountSubExample accountSubExample = new AccountSubExample();
//        accountSubExample.createCriteria();
//        List<AccountSub> accountSubs = accountSubMapper.selectByExample(accountSubExample);
//        if(accountSubs != null && accountSubs.size() > 0){
//            for(AccountSub accountSub : accountSubs){
//                String endFlag = "0";
//                //调用接口查询子账户充值记录
//                while("0".equals(endFlag)){
//                    Integer i = 0;
//                    BankWithdrawCashDetailsQueryRequest bankWithdrawCashDetailsQueryRequest = new BankWithdrawCashDetailsQueryRequest();
//                    bankWithdrawCashDetailsQueryRequest.setFunctionFlag("2");
//                    // 得到资金总账户号码
//                    AccountExample accountExample = new AccountExample();
//                    accountExample.createCriteria().andAccountTypeEqualTo(1).andAccountIsDeleteEqualTo(false);
//                    Account account =accountMapper.selectByExample(accountExample).stream().findFirst().orElse(null);
//                    if(account != null){
//                        bankWithdrawCashDetailsQueryRequest.setFundSummaryAcctNo(account.getAccountFundSummaryAccountNo());
//                    }
//                    bankWithdrawCashDetailsQueryRequest.setSubAcctNo(accountSub.getAccountSubNo());
//                    bankWithdrawCashDetailsQueryRequest.setQueryFlag("3");
//                    bankWithdrawCashDetailsQueryRequest.setQueryFlag("3");
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.set(2018,4,10);
//                    bankWithdrawCashDetailsQueryRequest.setBeginDate(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()));
//                    bankWithdrawCashDetailsQueryRequest.setEndDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
//                    bankWithdrawCashDetailsQueryRequest.setPageNum((++i).toString());
//                    BankWithdrawCashDetailsQueryResponse bankWithdrawCashDetailsQueryResponse = paBankService.bankWithdrawCashDetailsQuery(bankWithdrawCashDetailsQueryRequest);
//                    if(bankWithdrawCashDetailsQueryResponse != null && "000000".equals(bankWithdrawCashDetailsQueryResponse.getTxnReturnCode())){
//                        String  tranItemArray = bankWithdrawCashDetailsQueryResponse.getTranItemArray(); // 得到当前页面的条数
//                        List<HashMap> list = JSON.parseArray(tranItemArray,HashMap.class);//<HashMap<String,String>>
//                        endFlag = bankWithdrawCashDetailsQueryResponse.getEndFlag();
//                        for(Map<String, String> map : list){
//                            //查询是否已同步
//                            AccountSubTradeExample accountSubTradeExample = new AccountSubTradeExample();
//                            accountSubTradeExample.createCriteria().andAccountSubTradeNoEqualTo(map.get("FrontSeqNo"));
//                            List<AccountSubTrade> accountSubTrades = accountSubTradeMapper.selectByExample(accountSubTradeExample);
//                            if(accountSubTrades == null || accountSubTrades.size() == 0){
//                                AccountSubTrade accountSubTrade = new AccountSubTrade();
//                                accountSubTrade.setAccountSubTradeId(CommonUtil.uuid());
//
//                                accountSubTrade.setAccountSubTradeTranType("01");
//                                accountSubTrade.setAccountSubTradeTranPaymentType(2);
//                                accountSubTrade.setAccountSubTradeCcy("RMB");
//                                accountSubTrade.setAccountSubTradeCreateTime(new Date());
//
//                                accountSubTrade.setAccountSubTradeTranDate(new SimpleDateFormat("yyyyMMdd:HHmmss").parse(map.get("TranDate")+":"+map.get("TranTime")));// '交易时间',
//                                accountSubTrade.setAccountSubTradeNo(map.get("FrontSeqNo"));
//                                accountSubTrade.setAccountSubTradeTranAmt(new BigDecimal(map.get("TranAmt")));//交易金额（元
////                                accountSubTrade.setAccountSubTradeBankAccount(map.get("InAcctName")); // 账户名称（挂账）
////                                accountSubTrade.setAccountSubTradeBankNum(map.get("InAcctNo")); // '银行卡号(挂账)',
//
//                                //accountSubTrade.setAccountSubTradeAccountingDate();//会计日期
////                                accountSubTrade.setAccountSubTradeBankName(map.get("BankName"));// account_sub_trade_bank_name 银行名称（付款账户银行名称
//                                accountSubTrade.setAccountSubTradeBankRemark(map.get("Remark"));//  `account_sub_trade_remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
//                                accountSubTrade.setAccountSubTradeAdjustTime(null);
//                                accountSubTrade.setAccountSubTradeAccountSubId("");
//                                accountSubTrade.setAccountSubTradeType(3); //挂账
//                                accountSubTrade.setAccountSubTradeTranStatus(0); // 交易状态
//                                accountSubTrade.setAccountSubTradeInAccountSubId(accountSub.getAccountSubId());
//                                accountSubTrade.setAccountSubTradeBankNo(map.get("FrontSeqNo"));
//                                accountSubTradeMapper.insertSelective(accountSubTrade);
//                            }else{
//                                continue;
//                            }
//                        }
//                    }else{
//                     break;
//                    }
//                }
//
//            }
//        }
    }

    @Override
    public List<Map<String,String>> getHangingAccountSub() throws Exception{
        getHangingAccountListByBank();
        List<Map<String,String>> hangingAccountNoList = hangingAccountCuMapper.getHangingAccountSub();
        return hangingAccountNoList;
    }

    @Override
    public List<UserAccountInfoDto> getAllAccountSub() {
        List<UserAccountInfoDto> accountSubList = hangingAccountCuMapper.getAllAccountSub();
        return accountSubList;
    }

    @Override
    public void adjustAccountSub(AdjustAccountSubForm form) throws  Exception{


        User user = userMapper.selectByPrimaryKey(auth.getCurrentUser().getUserId());
        if ("".equals(user.getUserPayPassword())) {
            throw new AnyException("调账密码未设置，请先设置调账密码");
        }
        if(!user.getUserPayPassword().equals(SecurityUtils.encrypt(form.getPassword()))){
            throw  new AnyException("输入的调账密码错误");
        }
        AccountSubTrade accountSubTrade = accountSubTradeMapper.selectByPrimaryKey(form.getAccountSubTradeId());
        if(accountSubTrade == null){
            throw  new AnyException("所选交易单有误");
        }
        if(!accountSubTrade.getAccountSubTradeType().equals(7)){
            throw  new AnyException("所选交易单不支持调账");
        }
        if(accountSubTrade.getAccountSubTradeTranStatus().equals(8)){
            throw  new AnyException("所选交易单已调账成功，不可再操作");
        }

        RegisterBillRequest registerBillRequest = new RegisterBillRequest();

        // 得到资金总账户号码
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andAccountTypeEqualTo(1).andAccountIsDeleteEqualTo(false);
        Account account =accountMapper.selectByExample(accountExample).stream().findFirst().orElse(null);
        if(account != null){
            registerBillRequest.setFundSummaryAcctNo(account.getAccountFundSummaryAccountNo());
        }

        // 组织参数
        AccountSub accountSub = accountSubMapper.selectByPrimaryKey(form.getAccountSubId());
        registerBillRequest.setSubAcctNo(accountSub.getAccountSubNo());
        registerBillRequest.setSubAcctName(accountSub.getAccountSubName());
        registerBillRequest.setTranNetMemberCode(accountSub.getAccountSubRelationId());
        registerBillRequest.setTranAmt(accountSubTrade.getAccountSubTradeTranAmt().toString()); //account_sub_trade_tran_amt
        registerBillRequest.setCcy("RMB");
        registerBillRequest.setRemark(form.getRemark());
        registerBillRequest.setReservedMsg("");
        //向平安银行发送调账请求
        RegisterBillResponse custAcctIdBalanceQueryResponse = paBankService.registerBill(registerBillRequest);
        accountSubTrade.setAccountSubTradeAdjustTime(new Date());
        accountSubTrade.setAccountSubTradeAdjustUserId(auth.getCurrentUser().getUserId());
        accountSubTrade.setAccountSubTradeInAccountSubId(form.getAccountSubId());
        accountSubTrade.setAccountSubTradeRemark(form.getRemark());
        if(custAcctIdBalanceQueryResponse !=null ){
            if("000000".equals(custAcctIdBalanceQueryResponse.getTxnReturnCode())){
                accountSubTrade.setAccountSubTradeTranStatus(8);
                accountSubTrade.setAccountSubTradeBankNo(custAcctIdBalanceQueryResponse.getFrontSeqNo());
                accountSubTradeMapper.updateByPrimaryKeySelective(accountSubTrade);
            }else{
                accountSubTrade.setAccountSubTradeTranStatus(9);
                accountSubTradeMapper.updateByPrimaryKeySelective(accountSubTrade);
                throw new AnyException(custAcctIdBalanceQueryResponse.getTxnReturnMsg());
            }
        }else{
            accountSubTrade.setAccountSubTradeTranStatus(9);
            accountSubTradeMapper.updateByPrimaryKeySelective(accountSubTrade);
            logger.error("time:"+new Date()+";请求平安调账接口返回为空："+accountSubTrade.getAccountSubTradeId());
            throw new AnyException("转成出错,请稍后在尝试");
        }


    }

    @Override
    public boolean checkAccountSubTrade(String accountSubTradeId) {

        AccountSubTrade  accountSubTrade = accountSubTradeMapper.selectByPrimaryKey(accountSubTradeId);
        if(accountSubTrade == null){
            throw  new AnyException("所选交易单有误");
        }
        if(!accountSubTrade.getAccountSubTradeType().equals(7)){
            throw  new AnyException("所选交易单不支持调账");
        }
        if(accountSubTrade.getAccountSubTradeTranStatus().equals(8)){
            throw  new AnyException("所选交易单已调账成功，不可再操作");
        }
        return true;
    }

    @Override
    public AccountSubTradeDto getHangingTradeDetailById(String accountSubTradeId) {

        return hangingAccountCuMapper.getHangingTradeDetailById(accountSubTradeId);
    }


}
