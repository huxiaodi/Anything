package com.test.manage.service.user;

import com.test.manage.model.form.UserForm;
import com.test.manage.model.generator.Company;
import com.test.manage.model.generator.Resource;
import com.test.manage.model.generator.User;
import com.test.manage.model.request.LoginRequest;
import com.test.manage.model.request.QueryParams;
import com.test.manage.model.request.UserRequest;
import com.test.framework.model.ApiResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface UserService {

    // 用户登录
    ApiResult login(LoginRequest loginRequest);

    // 检验手机号是否已经注册过
    Boolean checkMobile(String mobile);

    // 根据用户名查询用户
    User checkUser(String userName);

    // 重置登录密码
    ApiResult resetPassword(UserRequest userRequest);

    // 检验手机号与预留手机号是否一致
    boolean checkMobile(UserRequest userRequest);

    // 修改登录密码
    ApiResult changePassword(UserRequest userRequest);

    // 修改联系人
    Integer changeMobile(UserRequest userRequest);

    Company getCompanyInformation(String userCompanyId);

    void upload(Company company);

    void changeCompanyInformation(Company company);

    User getPersonalInformation(String userId);

    void changePersonalInformation(User user);

    void upload(User user);

    // 新用户注册开户
    ApiResult save(User user, Company company, String mobile) throws Exception;

    /**
     * 管理端 用户列表
     */
    List manageList(QueryParams queryParams);

    User selectByPrimaryKey(String id);

    void updateByPrimaryKeySelective(User user);

    Boolean checkUserName(User user);

    // 获取用户管理列表
    List<Map<String, Object>> getUserMemberList(UserForm userForm);

    // 后台管理添加用户
    void addUser(UserRequest userRequest);

    // 修改用户
    void modify(UserRequest userRequest);

    List<Resource> getResource(String userId);


    void loginOut(String token);
}
