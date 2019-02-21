package com.dllg.ebpay.service.user;

import com.dllg.ebpay.model.form.UserForm;
import com.dllg.ebpay.model.form.UserLogForm;
import com.dllg.ebpay.model.generator.Company;
import com.dllg.ebpay.model.generator.Resource;
import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.model.request.LoginRequest;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.model.request.UserRequest;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.model.AuthUser;
import org.springframework.web.multipart.MultipartFile;

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
