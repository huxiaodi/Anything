package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.generator.Resource;
import com.dllg.ebpay.model.generator.Role;
import com.dllg.ebpay.model.request.RolePageParams;

import java.util.List;
import java.util.Map;

public interface RoleCuMapper {
    /**
     * 管理端 角色列表
     */
    List<Map<String, Object>> manageList(RolePageParams rolePageParams);


    List<Map<String, Object>> manageListFront(RolePageParams rolePageParams);

    // 根据用户id查询角色
    List<Role> getRoleByUserId(String userId);

    List<Resource> allRoleResources();

    List<Resource> frontRoleResources();
}
