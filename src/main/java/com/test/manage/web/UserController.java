package com.test.manage.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.test.manage.model.generator.Company;
import com.test.manage.model.generator.Resource;
import com.test.manage.model.generator.User;
import com.test.manage.model.request.LoginRequest;
import com.test.manage.model.request.UserRequest;
import com.test.manage.service.user.UserService;
import com.test.framework.model.ApiResult;
import com.test.framework.model.AuthUser;
import com.test.framework.security.Auth;
import com.test.framework.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.test.framework.utils.ApiResultUtil.error;
import static com.test.framework.utils.ApiResultUtil.success;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FileUploadUtils fileUploadUtils;
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
     * 更改统一信用照片
     *
     * @param font
     * @param companyId
     */
    @PostMapping("/upload")
    public void upload(MultipartFile font, String companyId) {
        String userHead = fileUploadUtils.upload(font, companyId, FileUploadUtils.FOLDER_USER);
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setCompanyUnifiedSocialImg(userHead);
        userService.upload(company);
    }

    /**
     * 更改组织机构代码证
     *
     * @param organization
     * @param companyId
     */
    @PostMapping("/uploadOrganization")
    public void uploadOrganization(MultipartFile organization, String companyId) {
        String userHead = fileUploadUtils.upload(organization, companyId, FileUploadUtils.FOLDER_USER);
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setCompanyOrganizationCodeImg(userHead);
        userService.upload(company);
    }

    /**
     * 更改营业执照
     *
     * @param license
     * @param companyId
     */
    @PostMapping("/uploadLicense")
    public void uploadLicense(MultipartFile license, String companyId) {
        String userHead = fileUploadUtils.upload(license, companyId, FileUploadUtils.FOLDER_USER);
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setCompanyLicenseNoImg(userHead);
        userService.upload(company);
    }

    /**
     * 更改税务登记证
     *
     * @param tax
     * @param companyId
     */
    @PostMapping("/uploadTax")
    public void uploadTax(MultipartFile tax, String companyId) {
        String userHead = fileUploadUtils.upload(tax, companyId, FileUploadUtils.FOLDER_USER);
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setCompanyTaxNoImg(userHead);
        userService.upload(company);
    }

    /**
     * 更改证件照（正面）
     *
     * @param font
     * @param userId
     */
    @PostMapping("/uploadFont")
    public void uploadFont(MultipartFile font, String userId) {
        String userHead = fileUploadUtils.upload(font, userId, FileUploadUtils.FOLDER_USER);
        User user = new User();
        user.setUserId(userId);
        user.setUserIdCardFontImg(userHead);
        userService.upload(user);
    }

    /**
     * 更改证件照（反面）
     *
     * @param back
     * @param userId
     */
    @PostMapping("/uploadBack")
    public void uploadBack(MultipartFile back, String userId) {
        String userHead = fileUploadUtils.upload(back, userId, FileUploadUtils.FOLDER_USER);
        User user = new User();
        user.setUserId(userId);
        user.setUserIdCardBackImg(userHead);
        userService.upload(user);
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
        } else if (userType == 2) {
            mobile = company.getCompanyContactTel();
        }
        return userService.save(font, back, companyOrganizationCodeImg, companyLicenseNoImg, companyTaxNoImg, user, company, mobile);
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

    /**
     *
     * @return
     */
    @GetMapping("/checkAdjustPsw")
    public ApiResult checkAdjustPsw(){
        return success(userService.checkAdjustPsw(auth.getCurrentUser().getUserId()));
    }
}
