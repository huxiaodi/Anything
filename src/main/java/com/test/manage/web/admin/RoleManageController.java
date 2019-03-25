package com.test.manage.web.admin;

import com.test.manage.model.generator.User;
import com.test.manage.model.request.RolePageParams;
import com.test.manage.model.request.RoleParams;
import com.test.manage.service.role.RoleService;
import com.test.framework.controller.BaseController;
import com.test.framework.model.ApiResult;
import com.test.framework.model.AuthUser;
import com.test.framework.security.Auth;
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

}
