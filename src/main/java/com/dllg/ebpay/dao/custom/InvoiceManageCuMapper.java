package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.form.FpMainForm;
import com.dllg.ebpay.model.generator.FpDetail;
import com.dllg.ebpay.model.generator.FpMain;

import java.util.List;
import java.util.Map;

public interface InvoiceManageCuMapper {

    List<Map<String, Object>> getFpMainList(FpMainForm fpMainForm);
    List<Map<String, Object>> getFpDetailList(FpMainForm fpMainForm);
    void add(FpMain fpMain);
    void addDetail(FpDetail fpDetail);

    void update(FpMain fpMain);
    void updateDetail(FpDetail fpDetail);

    void delete(FpMain fpMain);
    void deleteDetail(FpDetail fpDetail);


}
