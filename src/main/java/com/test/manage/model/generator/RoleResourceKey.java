package com.test.manage.model.generator;

/**
 *   角色资源关系表
 *   role_resource
 * @mbg.generated do_not_delete_during_merge
 */
public class RoleResourceKey {
    /**
     *   角色id
     *   role_resource.role_resource_role_id
     * @mbg.generated
     */
    private String roleResourceRoleId;

    /**
     *   资源id
     *   role_resource.role_resource_resource_id
     * @mbg.generated
     */
    private String roleResourceResourceId;

    /**
     * 角色id
     * @return the value of role_resource.role_resource_role_id
     * @mbg.generated
     */
    public String getRoleResourceRoleId() {
        return roleResourceRoleId;
    }

    /**
     * 角色id
     * @param roleResourceRoleId the value for role_resource.role_resource_role_id
     * @mbg.generated
     */
    public void setRoleResourceRoleId(String roleResourceRoleId) {
        this.roleResourceRoleId = roleResourceRoleId == null ? null : roleResourceRoleId.trim();
    }

    /**
     * 资源id
     * @return the value of role_resource.role_resource_resource_id
     * @mbg.generated
     */
    public String getRoleResourceResourceId() {
        return roleResourceResourceId;
    }

    /**
     * 资源id
     * @param roleResourceResourceId the value for role_resource.role_resource_resource_id
     * @mbg.generated
     */
    public void setRoleResourceResourceId(String roleResourceResourceId) {
        this.roleResourceResourceId = roleResourceResourceId == null ? null : roleResourceResourceId.trim();
    }
}