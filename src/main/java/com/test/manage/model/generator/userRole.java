package com.test.manage.model.generator;

import java.util.Date;

/**
 *   用户和角色关系表
 *   user_role
 * @mbg.generated do_not_delete_during_merge
 */
public class userRole extends userRoleKey {
    /**
     *   user_role.user_role_create_time
     * @mbg.generated
     */
    private Date userRoleCreateTime;

    /**
     *   user_role.user_role_create_user_id
     * @mbg.generated
     */
    private String userRoleCreateUserId;

    /**
     *   user_role.user_role_update_time
     * @mbg.generated
     */
    private Date userRoleUpdateTime;

    /**
     *   user_role.user_role_update_user_id
     * @mbg.generated
     */
    private String userRoleUpdateUserId;

    /**
     * @return the value of user_role.user_role_create_time
     * @mbg.generated
     */
    public Date getUserRoleCreateTime() {
        return userRoleCreateTime;
    }

    /**
     * @param userRoleCreateTime the value for user_role.user_role_create_time
     * @mbg.generated
     */
    public void setUserRoleCreateTime(Date userRoleCreateTime) {
        this.userRoleCreateTime = userRoleCreateTime;
    }

    /**
     * @return the value of user_role.user_role_create_user_id
     * @mbg.generated
     */
    public String getUserRoleCreateUserId() {
        return userRoleCreateUserId;
    }

    /**
     * @param userRoleCreateUserId the value for user_role.user_role_create_user_id
     * @mbg.generated
     */
    public void setUserRoleCreateUserId(String userRoleCreateUserId) {
        this.userRoleCreateUserId = userRoleCreateUserId == null ? null : userRoleCreateUserId.trim();
    }

    /**
     * @return the value of user_role.user_role_update_time
     * @mbg.generated
     */
    public Date getUserRoleUpdateTime() {
        return userRoleUpdateTime;
    }

    /**
     * @param userRoleUpdateTime the value for user_role.user_role_update_time
     * @mbg.generated
     */
    public void setUserRoleUpdateTime(Date userRoleUpdateTime) {
        this.userRoleUpdateTime = userRoleUpdateTime;
    }

    /**
     * @return the value of user_role.user_role_update_user_id
     * @mbg.generated
     */
    public String getUserRoleUpdateUserId() {
        return userRoleUpdateUserId;
    }

    /**
     * @param userRoleUpdateUserId the value for user_role.user_role_update_user_id
     * @mbg.generated
     */
    public void setUserRoleUpdateUserId(String userRoleUpdateUserId) {
        this.userRoleUpdateUserId = userRoleUpdateUserId == null ? null : userRoleUpdateUserId.trim();
    }
}