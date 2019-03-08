package com.test.manage.service.user;

import com.test.manage.model.form.UserLogForm;
import com.test.framework.model.AuthUser;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface UserLogService {

    List<Map<String, Object>> getLogList(UserLogForm userLogForm);

    List<Map<String, Object>> getUserLogList(UserLogForm userLogForm);

    void addUser(AuthUser authUser);

}
