package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.form.PayApplicationForm;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 15:03 2018/8/7
 */
public interface ApplicationTradeCuMapper {
    List<Map<String, String>> getPayApplicationList(PayApplicationForm payApplicationForm);

    List<String> getObjecSource(String relationId);
}
