package com.test.manage.web.admin;

import com.test.manage.model.form.UserLogForm;
import com.test.manage.service.user.UserLogService;
import com.test.framework.controller.BaseController;
import com.test.framework.model.ApiResult;
import com.test.framework.security.Auth;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
