package com.test.manage.dao.custom;

import com.test.manage.dao.generator.UserMapper;
import com.test.manage.model.form.UserForm;
import com.test.manage.model.generator.Resource;
import com.test.manage.model.request.QueryParams;

import java.util.List;
import java.util.Map;

public interface UserCuMapper extends UserMapper {

    String getUserAccountName(String userId);


    /**
     * 管理端 用户列表
     */
    List manageList(QueryParams queryParams);

    List<Map<String, Object>> getUserMemberList(UserForm userForm);

    List<Resource> getResource(String userId);
}
