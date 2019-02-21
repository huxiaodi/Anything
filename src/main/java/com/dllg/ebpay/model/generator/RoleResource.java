package com.dllg.ebpay.model.generator;

import java.util.Date;

/**
 *   角色资源关系表
 *   role_resource
 * @mbg.generated do_not_delete_during_merge
 */
public class RoleResource extends RoleResourceKey {
    /**
     *   创建时间
     *   role_resource.role_resource_create_time
     * @mbg.generated
     */
    private Date roleResourceCreateTime;

    /**
     *   创建人
     *   role_resource.role_resource_create_user_id
     * @mbg.generated
     */
    private String roleResourceCreateUserId;

    /**
     *   修改时间
     *   role_resource.role_resource_update_time
     * @mbg.generated
     */
    private Date roleResourceUpdateTime;

    /**
     *   修改人
     *   role_resource.role_resource_update_user_id
     * @mbg.generated
     */
    private String roleResourceUpdateUserId;

    /**
     * 创建时间
     * @return the value of role_resource.role_resource_create_time
     * @mbg.generated
     */
    public Date getRoleResourceCreateTime() {
        return roleResourceCreateTime;
    }

    /**
     * 创建时间
     * @param roleResourceCreateTime the value for role_resource.role_resource_create_time
     * @mbg.generated
     */
    public void setRoleResourceCreateTime(Date roleResourceCreateTime) {
        this.roleResourceCreateTime = roleResourceCreateTime;
    }

    /**
     * 创建人
     * @return the value of role_resource.role_resource_create_user_id
     * @mbg.generated
     */
    public String getRoleResourceCreateUserId() {
        return roleResourceCreateUserId;
    }

    /**
     * 创建人
     * @param roleResourceCreateUserId the value for role_resource.role_resource_create_user_id
     * @mbg.generated
     */
    public void setRoleResourceCreateUserId(String roleResourceCreateUserId) {
        this.roleResourceCreateUserId = roleResourceCreateUserId == null ? null : roleResourceCreateUserId.trim();
    }

    /**
     * 修改时间
     * @return the value of role_resource.role_resource_update_time
     * @mbg.generated
     */
    public Date getRoleResourceUpdateTime() {
        return roleResourceUpdateTime;
    }

    /**
     * 修改时间
     * @param roleResourceUpdateTime the value for role_resource.role_resource_update_time
     * @mbg.generated
     */
    public void setRoleResourceUpdateTime(Date roleResourceUpdateTime) {
        this.roleResourceUpdateTime = roleResourceUpdateTime;
    }

    /**
     * 修改人
     * @return the value of role_resource.role_resource_update_user_id
     * @mbg.generated
     */
    public String getRoleResourceUpdateUserId() {
        return roleResourceUpdateUserId;
    }

    /**
     * 修改人
     * @param roleResourceUpdateUserId the value for role_resource.role_resource_update_user_id
     * @mbg.generated
     */
    public void setRoleResourceUpdateUserId(String roleResourceUpdateUserId) {
        this.roleResourceUpdateUserId = roleResourceUpdateUserId == null ? null : roleResourceUpdateUserId.trim();
    }
}