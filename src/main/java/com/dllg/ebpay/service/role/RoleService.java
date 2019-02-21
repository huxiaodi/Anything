package com.dllg.ebpay.service.role;

import com.dllg.ebpay.model.generator.Resource;
import com.dllg.ebpay.model.generator.Role;
import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.model.request.RolePageParams;
import com.dllg.ebpay.model.request.RoleParams;
import com.dllg.framework.model.ApiResult;

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

    ApiResult changeUserPayPassword(RoleParams roleParams);

    boolean checkSet(String userId);

    ApiResult setUserPayPassword(User user);

    ApiResult checkUserPayPassword(RoleParams roleParams);

    List<Resource> allRoleResources();
    List<Resource> frontRoleResources();

    public void addFrontUserRole(User user);
}