package com.dllg.ebpay.web;

import com.dllg.common.CommonUtil;
import com.dllg.common.DlebException;
import com.dllg.ebpay.model.form.RefundForm;
import com.dllg.ebpay.service.trading.RefundService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.model.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.acl.AllPermissionsImpl;

import java.util.Map;

@RestController
@RequestMapping("/refund")
public class RefundController extends BaseController{

    @Autowired
    private RefundService refundService;

    /**
     * 退款请求
     * @param refundForm
     * @return
     */
    @PostMapping("/applicationfordrawback")
    public ApiResult applicationfordrawback(RefundForm refundForm){
        Map<String, String> validateMap = CommonUtil.validate(refundForm);
        if (validateMap != null && !validateMap.isEmpty()) {
            System.out.println(validateMap);
            return error(ApiCodeEnum.FAILURE, validateMap.toString());
        }
        try {
            refundService.applicationfordrawback(refundForm);
            return success();
        } catch (DlebException e) {
            e.printStackTrace();
            return error("");
        }
    }

    //退款
    @PostMapping("/drawBack")
    public ApiResult drawBack(String refundTradeBankNo){
        try {
            refundService.drawBack(refundTradeBankNo);
            refundService.send(refundTradeBankNo);
            return success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            refundService.send(refundTradeBankNo);
            return error(e.getMessage());
        }

    }

    //三方退款查询
    @GetMapping("/queryRefundStatus")
    public ApiResult queryRefundStatus(String orderNo){
        try {
            Map<String, Object> stringObjectMap = refundService.queryRefundStatus(orderNo);
            return success(stringObjectMap);
        } catch (Exception e) {
            e.printStackTrace();
            return error(e.getMessage());
        }
    }
}
