package com.dllg.ebpay.api;

import com.dllg.common.CommonUtil;
import com.dllg.ebpay.model.request.ApiPaymentApplicationDisposeRequest;
import com.dllg.ebpay.model.request.ApiPaymentDisposeRequest;
import com.dllg.ebpay.model.request.ApiQueryDataResquest;
import com.dllg.ebpay.service.account.UserAccountService;
import com.dllg.ebpay.service.api.ApiPayService;
import com.dllg.ebpay.service.api.RegistService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 14:58 2018/4/2
 */
@RestController
@RequestMapping("/api/pay")
@Api(value = "/api/pay",description = "用户业务平台调用的支付相关接口")
public class ApiPayController extends BaseController {

    private static final Logger logger = LogManager.getLogger(ApiPayController.class);
    @Autowired
    private RegistService registService;
    @Autowired
    private ApiPayService apiPayService;
    @Autowired
    private UserAccountService userAccountService;

    @ApiOperation("支付请求")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body", dataType = "ApiPaymentDisposeRequest", name = "apiPaymentDisposeRequest", value = "支付请求信息", required = true) })
    @PostMapping("/paymentDispose")
    public ApiResult paymentDispose(@RequestBody ApiPaymentDisposeRequest apiPaymentDisposeRequest)throws Exception {
        Map<String, String> validateMap = CommonUtil.validate(apiPaymentDisposeRequest);
        if (validateMap != null && !validateMap.isEmpty()) {
            System.out.println(validateMap);
            return error(ApiCodeEnum.FAILURE, validateMap.toString());
        }


        String accountSubNo = apiPaymentDisposeRequest.getAccountSubNo();

        Map<String, BigDecimal> balanceByAccountSub = userAccountService.getBalanceByAccountSub(accountSubNo);
        BigDecimal balance = balanceByAccountSub.get("acctAvailBal");
        if(balance.compareTo(apiPaymentDisposeRequest.getTranAmt()) < 0 ){
            return error(ApiCodeEnum.FAILURE, "账户余额不足！");
        }
        return success(apiPayService.paymentDispose(apiPaymentDisposeRequest));
    }

    @ApiOperation("德玲支付申请请求")
    @PostMapping("/paymentApplicationDispose")
    public ApiResult paymentApplicationDispose(@RequestBody ApiPaymentApplicationDisposeRequest apiPaymentApplicationDisposeRequest)throws Exception {
        Map<String, String> validateMap = CommonUtil.validate(apiPaymentApplicationDisposeRequest);
        if (validateMap != null && !validateMap.isEmpty()) {
            System.out.println(validateMap);
            return error(ApiCodeEnum.FAILURE, validateMap.toString());
        }
        BigDecimal bigDecimal = apiPaymentApplicationDisposeRequest.getTranAmt();
        if (!(bigDecimal.compareTo(BigDecimal.ZERO) == 1)) {
           return error("交易金额必须大于0");
        }
        apiPayService.paymentApplicationDispose(apiPaymentApplicationDisposeRequest);
        return success();
    }


    @ApiOperation(value = "接收平安返回的参数接口", notes = "根据平安返回的验证信息，验证通过就对该业务端发起转账操作，并将平安交易的同步返回的结果在返回给业务平台")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "orderid", value = "订单号", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "P2PCode", value = "参数", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "flag", value = "参数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "orig", value = "参数", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sign", value = "参数", required = true)
            }
    )
    @PostMapping("/paymentCallBack")
    public void paymentCallBack(String orderid, String P2PCode, String flag, String orig, String sign) {
        logger.info("平安业务平台回调------------");
        apiPayService.paymentCallBack(orderid, flag, sign);
    }

    @ApiOperation("提供给业务平台的查询接口")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body", dataType = "ApiQueryDataResquest", name = "apiQueryDataResquest", value = "查询接口参数", required = true) })
    @PostMapping("/queryData")
    public ApiResult queryData(@RequestBody ApiQueryDataResquest apiQueryDataResquest) {
        Map<String, String> validateMap = CommonUtil.validate(apiQueryDataResquest);
        if (validateMap != null && !validateMap.isEmpty()) {
            System.out.println(validateMap);
            return error(ApiCodeEnum.FAILURE, validateMap);
        }
        return success(apiPayService.queryData(apiQueryDataResquest));
    }

    @ApiOperation(value = "获取支付页面数据", notes = "根据传入的单号，获取该单号的相关支付信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "id", value = "单号", required = true)
        }
    )
    @GetMapping("/payment")
    public ApiResult payment(String id) {
        Map<String, Object> stringObjectMap = registService.getPaymentMessage(id);
        return success(stringObjectMap);
    }

    @ApiOperation(value = "获取子账户余额", notes = "获取子账户的余额")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "accountSubNo", value = "子账户号码", required = true)
    }
    )
    @PostMapping("/getBalanceByAccountSub")
    public ApiResult getBalanceByAccountSub(String accountSubNo){
        try {
            Map<String, BigDecimal> balanceByAccountSub = userAccountService.getBalanceByAccountSub(accountSubNo);
            BigDecimal balance = balanceByAccountSub.get("acctAvailBal");
            return success(balance);
        } catch (Exception e) {
            e.printStackTrace();
            return error("获取余额失败");
        }
    }

}
