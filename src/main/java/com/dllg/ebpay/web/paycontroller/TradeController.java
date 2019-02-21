package com.dllg.ebpay.web.paycontroller;


import com.dllg.ebpay.model.dto.UserAccountInfoDto;
import com.dllg.ebpay.model.form.RefundForm;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.account.UserAccountService;
import com.dllg.ebpay.service.pay.AccountSubTradeService;
import com.dllg.ebpay.service.trading.RefundService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.security.Auth;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 资金流水
 */
@RestController
@RequestMapping("trade")
public class TradeController extends BaseController {

    private final static Logger logger = LogManager.getLogger(TradeController.class);

    @Autowired
    private AccountSubTradeService accountSubTradeService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private RefundService refundService;
    @Autowired
    private Auth auth;

    /**
     * 交易记录
     */
    @PostMapping("/record/list")
    public ApiResult recordList(@RequestBody(required = false) QueryParams queryParams) {
        List<UserAccountInfoDto> userAccountList = userAccountService.getUserAccountList();
        List<String> collect = userAccountList.stream().map(UserAccountInfoDto::getSubAcctNo).collect(Collectors.toList());
        queryParams.setNos(collect);
        if(StringUtils.isNotBlank(queryParams.getNo())){
            if(!queryParams.getNos().contains(queryParams.getNo())){
                return error(ApiCodeEnum.FAILURE,"参数错误");
            }
        }
        queryParams.startPage();
        List list = accountSubTradeService.getTradeRecordList(queryParams);
        for(int i=0 ; i<list.size(); i++){
            Map map = (Map)list.get(i);
            String no = (String)map.get("account_sub_no"); //发起账户
            if(queryParams.getNos().contains(no)){
                map.put("account_sub_trade_tran_payment_type",1);//转出
            }else{
                // 实际转入的金额 = 总金额 - 手续费
                map.replace("account_sub_trade_tran_amt", new BigDecimal(map.get("account_sub_trade_tran_amt").toString()).subtract(new BigDecimal(map.get("account_sub_trade_tran_fee").toString())));
                map.put("account_sub_trade_tran_payment_type",2);//转入
            }
        }
        return success(new PageInfo<>(list));
    }
    /**
     * 业务
     */
    @PostMapping("/business/list")
    public ApiResult businessList(@RequestBody(required = false) QueryParams queryParams) {
        List<UserAccountInfoDto> userAccountList = userAccountService.getUserAccountList();
        List<String> collect = userAccountList.stream().map(UserAccountInfoDto::getSubAcctNo).collect(Collectors.toList());
        queryParams.setNos(collect);
        if(StringUtils.isNotBlank(queryParams.getNo())){
            if(!queryParams.getNos().contains(queryParams.getNo())){
                return error(ApiCodeEnum.FAILURE,"参数错误");
            }
        }
        queryParams.startPage();
        List list = accountSubTradeService.getTradeBusinessList(queryParams);
        for(int i=0 ; i<list.size(); i++){
            Map map = (Map)list.get(i);
            String no = (String)map.get("account_sub_no"); //发起账户
            if(queryParams.getNos().contains(no)){
                map.put("account_sub_trade_tran_payment_type",1);//转出
            }else{
                // 实际转入的金额 = 总金额 - 手续费
                map.replace("account_sub_trade_tran_amt", new BigDecimal(map.get("account_sub_trade_tran_amt").toString()).subtract(new BigDecimal(map.get("account_sub_trade_tran_fee").toString())));
                map.put("account_sub_trade_tran_payment_type",2);//转入
            }
        }
        return success(new PageInfo<>(list));
    }

    /**
     * 前台主界面, 日收入, 日支出
     */
    @PostMapping("/time/statistics")
    public ApiResult time() {
        QueryParams queryParams = new  QueryParams();
        List<UserAccountInfoDto> userAccountList = userAccountService.getUserAccountList();
        List<String> collect = userAccountList.stream().map(UserAccountInfoDto::getSubAcctNo).collect(Collectors.toList());
        queryParams.setNos(collect);
        Map dayPay = accountSubTradeService.dayPay(queryParams);
        Map dayIncome = accountSubTradeService.dayIncome(queryParams);
        Map monthPay = accountSubTradeService.monthPay(queryParams);
        Map monthIncome = accountSubTradeService.monthIncome(queryParams);

        dayPay.putAll(dayIncome);
        dayPay.putAll(monthPay);
        dayPay.putAll(monthIncome);
        return success(dayPay);
    }

    @GetMapping("/accountSubTrade/{id}")
    public ApiResult get(@PathVariable(value = "id") String id) {
        return success(accountSubTradeService.getOrder(id));
    }

    /**
     * 退款申请列表
     *
     * @param refundForm
     * @return
     */
    @PostMapping("/getRefund")
    public ApiResult getRefund(@RequestBody RefundForm refundForm) {
        List list = refundService.getRefund(refundForm);
        return success(new PageInfo<>(list));
    }

    @GetMapping("/getRefundDetail")
    public ApiResult getRefundDetail(String refundId){
        if(refundId == null || "".equals(refundId)){
            return error("获取详情失败，请重新尝试");
        }
        return success(refundService.getRefundDetail(refundId));
    }

    @PostMapping("/refundAudit")
    public ApiResult refundAudit(@RequestBody Map<String ,String > map){
        refundService.refundAudit(map);
        return success();
    }

}
