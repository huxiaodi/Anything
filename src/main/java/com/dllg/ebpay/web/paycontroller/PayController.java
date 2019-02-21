package com.dllg.ebpay.web.paycontroller;

import com.dllg.common.DlebException;
import com.dllg.ebpay.model.form.BusinessPayForm;
import com.dllg.ebpay.model.form.PayApplicationForm;
import com.dllg.ebpay.model.form.RefundForm;
import com.dllg.ebpay.model.generator.ApplicationTrade;
import com.dllg.ebpay.model.response.PayResponse;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.ebpay.service.trading.RefundService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.dllg.ebpay.service.pay.PayService;
import com.ecc.emp.data.KeyedCollection;
import com.github.pagehelper.PageInfo;
import com.sdb.payclient.core.PayclientInterfaceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.acl.AllPermissionsImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pay")
public class PayController extends BaseController {
    Logger logger = LoggerFactory.getLogger(PayController.class);
    @Autowired
    private PayService payService;
    @Autowired
    private AccountSubService accountSubService;
    @Autowired
    private RefundService refundService;

    @RequestMapping(value = "/getPayInfo")
    public ApiResult getPayInfo(String money,String fee, String remark){
        try {

            PayResponse payResponse = payService.payMoney(money,fee,remark);
            return success(payResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return error("获取数据失败！");
        }
    }
    @RequestMapping(value = "/getPayInfoForBusiness")
    public ApiResult getPayInfoForBusiness(@RequestBody BusinessPayForm businessPayForm){
        try {
            //保存支付记录
//            String accountSubNo = "3284000000011528";
//            businessPayForm.setAccountSubNo(accountSubNo);
            PayResponse payResponse = payService.payMoneyForBusiness(businessPayForm);
            return success(payResponse);
        }catch (DlebException e){
            logger.error(e.getMessage(),e);
            return error(e.getMessage());
        }
        catch (Exception e) {
            logger.error("获取数据失败",e);
            return error("获取数据失败！");
        }
    }

    //业务系统支付回调
    @RequestMapping(value = "/returnbackForBusiness")
    public ApiResult returnbackForBusiness(HttpServletRequest request){
        try {
            PayclientInterfaceUtil util = new PayclientInterfaceUtil ();
            //获取银行返回签名源数据，解析到特定的业务对象中
            String orig = request.getParameter("orig");
            orig = com.sdb.payclient.core.PayclientInterfaceUtil.Base64Decode(orig, "GBK");
            //KeyedCollection origKcoll = new KeyedCollection("output ");
            KeyedCollection output = util.parseOrigData(orig);

            payService.returnbackToBusiness(output.getDataValue("orderId").toString(),output.getDataValue("status").toString());
            return success("");
        } catch (Exception e) {
            e.printStackTrace();
            return error("获取数据失败！");
        }
    }


    //e宝支付回调
    @RequestMapping(value = "/returnback")
    public ApiResult returnback(HttpServletRequest request){
        try {
            String orig = request.getParameter("orig");
            KeyedCollection output = payService.parsing(orig);
            payService.returnback(output.getDataValue("orderId").toString(),output.getDataValue("status").toString());
            return success("");
        } catch (Exception e) {
            e.printStackTrace();
            return error("获取数据失败！");
        }
    }

    //单笔查询交易状态
    @RequestMapping(value = "/queryStatus")
    public ApiResult getOrderStatus(String orderId){
        try {
            Map<String, String> orderStatus = payService.getOrderStatus(orderId);
            return success(orderStatus);
        } catch (Exception e) {
            e.printStackTrace();
            return error("获取数据失败！");
        }
    }

    /**
     * 付款申请列表
     *
     * @param
     * @return
     */
    @PostMapping("/getPayApplicationList")
    public ApiResult getPayApplicationList(@RequestBody PayApplicationForm payApplicationForm) {
        List list = payService.getPayApplicationList(payApplicationForm);
        return success(new PageInfo<>(list));
    }

    /**
     * 获取付款申请详情
     * @param applicationTradeId
     * @return
     */
    @GetMapping("/getPayApplicationDetail")
    public ApiResult getPayApplicationDetail(String applicationTradeId) {
        return success(payService.getPayApplicationDetail(applicationTradeId));
    }

    /**
     * 付款申请审核
     * @param map
     * @return
     */
    @PostMapping("/payApplicationAudit")
    public ApiResult payApplicationAudit(@RequestBody Map<String ,String > map){
        payService.payApplicationAudit(map);
        return success();
    }

    /**
     * 付款申请付款
     * @param map
     * @return
     */
    @PostMapping("/payApplicationPay")
    public ApiResult payApplicationPay(@RequestBody Map<String ,String > map){
        return success(payService.payApplicationPay(map));
    }

    /**
     * 获取付款申请详情
     * @param
     * @return
     */
    @GetMapping("/getObjecSource")
    public ApiResult getObjecSource() {
        return success(payService.getObjecSource());
    }




}
