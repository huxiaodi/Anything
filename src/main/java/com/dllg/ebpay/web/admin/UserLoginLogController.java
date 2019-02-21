package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.form.UserForm;
import com.dllg.ebpay.model.form.UserLogForm;
import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.model.request.UserRequest;
import com.dllg.ebpay.service.resource.ResourceService;
import com.dllg.ebpay.service.role.RoleService;
import com.dllg.ebpay.service.team.TeamService;
import com.dllg.ebpay.service.user.UserLogService;
import com.dllg.ebpay.service.user.UserService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.security.Auth;
import com.dllg.framework.utils.FileUploadUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人会员管理
 */
@RestController
@RequestMapping("admin/userLog")
public class UserLoginLogController extends BaseController {

    @Autowired
    private UserLogService userLogService;
    @Autowired
    private Auth auth;


    /**
     * 获取会员登录日志
     *
     * @return
     */
    @PostMapping("/getLogList")
    public ApiResult getLogList(@RequestBody UserLogForm userLogForm) {
        List<Map<String, Object>> list = userLogService.getLogList(userLogForm);
        return success(new PageInfo<>(list));
    }

    /**
     * 获取用户登录日志
     * @param userLogForm
     * @return
     */
    @PostMapping("/getUserLogList")
    public ApiResult getUserLogList(@RequestBody UserLogForm userLogForm) {
        List<Map<String, Object>> list = userLogService.getUserLogList(userLogForm);
        return success(new PageInfo<>(list));
    }

}
