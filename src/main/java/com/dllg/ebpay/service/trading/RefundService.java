package com.dllg.ebpay.service.trading;

import com.dllg.common.DlebException;
import com.dllg.ebpay.model.form.RefundForm;
import com.dllg.ebpay.model.form.UserForm;
import com.dllg.ebpay.model.generator.Company;
import com.dllg.ebpay.model.generator.Resource;
import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.model.request.LoginRequest;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.model.request.UserRequest;
import com.dllg.framework.model.ApiResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface RefundService {

    List getRefund(RefundForm refundForm);

    Map<String ,String> getRefundDetail(String refundId);

    void applicationfordrawback(RefundForm refundForm) throws DlebException;
    void refundAudit(Map<String ,String > map);

    void drawBack(String refundTradeBankNo) throws Exception;

    void send(String refundTradeBankNo);
    Map<String, Object> queryRefundStatus(String orderNo) throws Exception;
}
