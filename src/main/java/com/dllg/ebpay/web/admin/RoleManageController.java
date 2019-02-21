package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.model.request.RolePageParams;
import com.dllg.ebpay.model.request.RoleParams;
import com.dllg.ebpay.service.dx.DXService;
import com.dllg.ebpay.service.msgCode.MsgCodeService;
import com.dllg.ebpay.service.role.RoleService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 */
@RestController
@RequestMapping("admin/roleManage")
public class RoleManageController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MsgCodeService msgCodeService;
    @Autowired
    private DXService dxService;
    @Autowired
    private Auth auth;

    /**
     * 角色列表
     */
    @PostMapping("/roleList")
    public ApiResult roleList(@RequestBody RolePageParams rolePageParams) {
        rolePageParams.startPage();
        List<Map<String, Object>> role = roleService.manageList(rolePageParams);
        return success(new PageInfo<>(role));
    }

    /**
     * 角色列表
     */
    @PostMapping("/roleListFront")
    public ApiResult roleListFront(@RequestBody RolePageParams rolePageParams) {
        rolePageParams.startPage();
        List<Map<String, Object>> role = roleService.manageListFront(rolePageParams);
        return success(new PageInfo<>(role));
    }

    /**
     * 所有权限资源
     */
    @GetMapping("/allRoleResources")
    public ApiResult allRoleResources() {
        return success(roleService.allRoleResources());
    }

    @GetMapping("/frontRoleResources")
    public ApiResult frontRoleResources() {
        return success(roleService.frontRoleResources());
    }

    /**
     * 删除角色
     */
    @PostMapping("/deleteRole")
    public ApiResult deleteRole(@RequestBody RoleParams roleParams) {
        try {
            roleService.deleteRole(roleParams.getRoleId());
            return success("角色删除成功");
        } catch (Exception e) {
            return error("角色删除失败");
        }
    }

    /**
     * 新增角色
     */
    @PostMapping("/addRole")
    public ApiResult addRole(@RequestBody RoleParams roleParams) {
        try {
            roleService.addRole(roleParams);
            return success("角色新增成功");
        } catch (Exception e) {
            return error("角色新增失败");
        }
    }
    @PostMapping("/addRoleFront")
    public ApiResult addRoleFront(@RequestBody RoleParams roleParams) {
        try {
            roleService.addRoleFront(roleParams);
            return success("角色新增成功");
        } catch (Exception e) {
            return error("角色新增失败");
        }
    }
    /**
     * 修改角色
     */
    @PostMapping("/updateRole")
    public ApiResult updateRole(@RequestBody RoleParams roleParams) {
        try {
            roleService.updateRole(roleParams);
            return success("角色修改成功");
        } catch (Exception e) {
            return error("角色修改失败");
        }
    }

    /**
     * 获取验证码
     */
    @PostMapping("/getVerification")
    public ApiResult getVerification(@RequestBody RoleParams roleParams) {
        AuthUser authUser = auth.getCurrentUser();
        roleParams.setUserId(authUser.getUserId());
        boolean b = dxService.checkToken(roleParams.getToken());
        if (b) {
            ApiResult apiResult = roleService.checkMobile(roleParams);
            ApiResult apiResult1 = roleService.checkUserPayPassword(roleParams);
            if (apiResult.getCode() == 1 && apiResult1.getCode() == 1) {
                msgCodeService.sendVerificationCode(roleParams.getMobile(), "您的验证码为：");
                return success("验证码发送成功");
            } else if (apiResult.getCode() != 1 && apiResult1.getCode() == 1) {
                return apiResult;
            } else if (apiResult1.getCode() != 1 && apiResult.getCode() == 1) {
                return apiResult1;
            } else {
                return error(apiResult.getMsg() + "," + apiResult1.getMsg());
            }
        } else {
            return error("图片验证失败");
        }
    }

    /**
     * 设置调账密码
     */
    @PostMapping("/setUserPayPassword")
    public ApiResult setUserPayPassword(@RequestBody User user) {
        try {
            AuthUser authUser = auth.getCurrentUser();
            boolean flag = roleService.checkSet(authUser.getUserId());
            if (flag) {
                user.setUserId(authUser.getUserId());
                return roleService.setUserPayPassword(user);
            } else {
                return error("已设置调账密码，请前往修改");
            }
        } catch (Exception e) {
            return error("设置失败" + e);
        }
    }

    /**
     * 判断是否已设置调账密码
     */
    @GetMapping("/checkUserPayPassword")
    public ApiResult checkUserPayPassword() {
        AuthUser authUser = auth.getCurrentUser();
        boolean flag = roleService.checkSet(authUser.getUserId());
        if (flag) {
            return success("未设置");
        } else {
            return error("已设置调账密码，请前往修改");
        }
    }

    /**
     * 修改调账密码
     */
    @PostMapping("/changeUserPayPassword")
    public ApiResult changeUserPayPassword(@RequestBody RoleParams roleParams) {
        AuthUser authUser = auth.getCurrentUser();
        roleParams.setUserId(authUser.getUserId());
        // 校对验证码
        boolean flag = msgCodeService.judgeCode(roleParams.getMobile(), roleParams.getUserVerificationCode());
        if (flag) {
            return roleService.changeUserPayPassword(roleParams);
        } else {
            return error("验证码错误");
        }
    }
}
