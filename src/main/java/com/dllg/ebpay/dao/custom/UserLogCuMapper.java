package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.form.UserForm;
import com.dllg.ebpay.model.form.UserLogForm;
import com.dllg.ebpay.model.generator.Resource;
import com.dllg.ebpay.model.generator.UserLog;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.model.response.OutSubAcctResponse;

import java.util.List;
import java.util.Map;

public interface UserLogCuMapper extends UserMapper {


    List<Map<String, Object>> getLogList(UserLogForm userLogForm);
    List<Map<String, Object>> getUserLogList(UserLogForm userLogForm);
    void addLog(UserLog userLog);

}
