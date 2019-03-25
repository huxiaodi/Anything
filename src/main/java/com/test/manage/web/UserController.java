package com.test.manage.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.j2objc.annotations.AutoreleasePool;
import com.test.manage.model.generator.Company;
import com.test.manage.model.generator.Resource;
import com.test.manage.model.generator.User;
import com.test.manage.model.request.LoginRequest;
import com.test.manage.model.request.UserRequest;
import com.test.manage.service.notice.NoticeService;
import com.test.manage.service.user.UserService;
import com.test.framework.model.ApiResult;
import com.test.framework.model.AuthUser;
import com.test.framework.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.test.framework.utils.ApiResultUtil.error;
import static com.test.framework.utils.ApiResultUtil.success;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private Auth auth;

    /**
     * 用户登录
     *
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public ApiResult login(@RequestBody @Validated LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping("/loginOut")
    public ApiResult loginOut(String token) {
        userService.loginOut(token);
        return success("登出成功");
    }

    @GetMapping("/getResource")
    public ApiResult getResource() {
        try {
            AuthUser authUser = auth.getCurrentUser();
            List<Resource> list = userService.getResource(authUser.getUserId());
            return success(list);
        } catch (Exception e) {
            return error("获取资源失败" + e);
        }
    }

    /**
     * 获取企业信息
     *
     * @param user
     * @return
     */
    @PostMapping("/getCompanyInformation")
    public ApiResult getCompanyInformation(@RequestBody User user) {
        Company company = userService.getCompanyInformation(user.getUserCompanyId());
        return success(company);
    }

    /**
     * 获取个人用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/getPersonalInformation")
    public ApiResult getPersonalInformation(@RequestBody User user) {
        User us = userService.getPersonalInformation(user.getUserId());
        return success(us);
    }




    /**
     * 判断用户名是否已注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register/checkUserName")
    public ApiResult checkUserName(@RequestBody User user) {
        Boolean b = userService.checkUserName(user);
        if (b) {
            return success("");
        } else {
            return error("该用户名已注册过");
        }
    }


    /**
     * 重置登录密码
     *
     * @param userRequest
     * @return
     */
    @PostMapping("/login/resetPassword")
    public ApiResult resetPassword(@RequestBody @Validated UserRequest userRequest) {
        if (userService.checkMobile(userRequest)) {
            return userService.resetPassword(userRequest);
        } else {
            return error("预留手机号错误");
        }
    }

    /**
     * 更换联系人
     *
     * @param userRequest
     * @return
     */
    @PostMapping("/changeMobile")
    public ApiResult changeMobile(@RequestBody UserRequest userRequest) {
        Integer i = userService.changeMobile(userRequest);
        if (i == 2) {
            return success("联系人修改成功");
        }
        return error("联系人修改失败");
    }

    /**
     * 修改登录密码
     *
     * @param userRequest
     * @return
     */
    @PostMapping("/changePassword")
    public ApiResult changePassword(@RequestBody UserRequest userRequest) {
        return userService.changePassword(userRequest);
    }



    /**
     * 用户注册
     *
     * @return
     */
    @RequestMapping("/register")
    @Transactional
    public ApiResult register(MultipartFile font, MultipartFile back, MultipartFile companyOrganizationCodeImg, MultipartFile companyLicenseNoImg, MultipartFile companyTaxNoImg, String userStr, String userVerificationCode) throws Exception {
        User user = JSONObject.parseObject(userStr, new TypeReference<User>() {
        });
        Company company = JSONObject.parseObject(userStr, new TypeReference<Company>() {
        });
        Integer userType = user.getUserType();
        String mobile = null;
        // 0-后台用户,1-个人,2-企业
        if (userType == 1) {
            mobile = user.getUserMobile();
        } else if (userType == 2 || userType == 3) {
            mobile = company.getCompanyContactTel();
        }
        return userService.save(user, company, mobile);
    }

    /**
     * 修改企业信息
     */
    @RequestMapping("/changeCompanyInformation")
    @Transactional
    public void changeCompanyInformation(String userStr) throws Exception {
        Company company = JSONObject.parseObject(userStr, new TypeReference<Company>() {
        });
        userService.changeCompanyInformation(company);
    }

    /**
     * 修改个人用户信息
     */
    @RequestMapping("/changePersonalInformation")
    @Transactional
    public void changePersonalInformation(String userStr) throws Exception {
        User user = JSONObject.parseObject(userStr, new TypeReference<User>() {
        });
        userService.changePersonalInformation(user);
    }


    @GetMapping("/getUserInfo")
    public ApiResult getUserInfo(){
        Map<String ,Object> map = new HashMap<>();
        map.put("personal",userService.getPersonalInformation(auth.getCurrentUser().getUserId()));
        map.put("company",userService.getCompanyInformation(auth.getCurrentUser().getUserCompanyId()));
        map.put("notices",noticeService.getNoticeList());
        return success(map);
    }

}
