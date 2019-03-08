package com.test.manage.dao.custom;

import com.test.manage.dao.generator.UserMapper;
import com.test.manage.model.form.UserLogForm;
import com.test.manage.model.generator.UserLog;

import java.util.List;
import java.util.Map;

public interface UserLogCuMapper extends UserMapper {


    List<Map<String, Object>> getLogList(UserLogForm userLogForm);
    List<Map<String, Object>> getUserLogList(UserLogForm userLogForm);
    void addLog(UserLog userLog);

}
