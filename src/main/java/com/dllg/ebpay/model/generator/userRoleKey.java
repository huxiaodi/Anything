package com.dllg.ebpay.model.generator;

/**
 *   用户和角色关系表
 *   user_role
 * @mbg.generated do_not_delete_during_merge
 */
public class userRoleKey {
    /**
     *   user_role.user_role_user_id
     * @mbg.generated
     */
    private String userRoleUserId;

    /**
     *   user_role.user_role_role_id
     * @mbg.generated
     */
    private String userRoleRoleId;

    /**
     * @return the value of user_role.user_role_user_id
     * @mbg.generated
     */
    public String getUserRoleUserId() {
        return userRoleUserId;
    }

    /**
     * @param userRoleUserId the value for user_role.user_role_user_id
     * @mbg.generated
     */
    public void setUserRoleUserId(String userRoleUserId) {
        this.userRoleUserId = userRoleUserId == null ? null : userRoleUserId.trim();
    }

    /**
     * @return the value of user_role.user_role_role_id
     * @mbg.generated
     */
    public String getUserRoleRoleId() {
        return userRoleRoleId;
    }

    /**
     * @param userRoleRoleId the value for user_role.user_role_role_id
     * @mbg.generated
     */
    public void setUserRoleRoleId(String userRoleRoleId) {
        this.userRoleRoleId = userRoleRoleId == null ? null : userRoleRoleId.trim();
    }
}