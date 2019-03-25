package com.test.manage.service.role;

import com.test.manage.model.generator.Resource;
import com.test.manage.model.generator.Role;
import com.test.manage.model.generator.User;
import com.test.manage.model.request.RolePageParams;
import com.test.manage.model.request.RoleParams;
import com.test.framework.model.ApiResult;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface RoleService {
    /**
     * 管理端 角色列表
     */
    List<Map<String, Object>> manageList(RolePageParams rolePageParams);

    List<Map<String, Object>> manageListFront(RolePageParams rolePageParams);

    void deleteRole(String roleId);

    void addRole(RoleParams roleParams);

    void addRoleFront(RoleParams roleParams);

    void updateRole(RoleParams roleParams);

    List<Role> getAllRole();

    ApiResult checkMobile(RoleParams roleParams);
    List<Resource> allRoleResources();
    List<Resource> frontRoleResources();
    public void addFrontUserRole(User user);
}
