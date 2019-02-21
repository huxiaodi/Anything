package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.form.UserForm;
import com.dllg.ebpay.model.generator.Resource;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.model.response.OutSubAcctResponse;

import java.util.List;
import java.util.Map;

public interface UserCuMapper extends UserMapper {

    String getUserAccountName(String userId);

    List<OutSubAcctResponse> getOutSubAcctNameList(String keyword);

    /**
     * 管理端 用户列表
     */
    List manageList(QueryParams queryParams);

    List<Map<String, Object>> getUserMemberList(UserForm userForm);

    List<Resource> getResource(String userId);
}
