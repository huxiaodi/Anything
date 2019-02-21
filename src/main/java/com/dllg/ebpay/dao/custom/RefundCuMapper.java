package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.form.RefundForm;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RefundCuMapper {

    /**
     * 退款申请列表
     */
    List<Map<String, Object>> RefundList(RefundForm refundForm);

    Map<String ,String> getRefundDetail(String refundId);

}
