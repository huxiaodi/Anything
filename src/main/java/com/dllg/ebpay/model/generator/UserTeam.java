package com.dllg.ebpay.model.generator;

import java.util.Date;

/**
 *   用户和数据组关系表
 *   user_team
 * @mbg.generated do_not_delete_during_merge
 */
public class UserTeam extends UserTeamKey {
    /**
     *   user_team.user_team_create_time
     * @mbg.generated
     */
    private Date userTeamCreateTime;

    /**
     *   user_team.user_team_create_user_id
     * @mbg.generated
     */
    private String userTeamCreateUserId;

    /**
     *   user_team.user_team_update_time
     * @mbg.generated
     */
    private Date userTeamUpdateTime;

    /**
     *   user_team.user_team_update_user_id
     * @mbg.generated
     */
    private String userTeamUpdateUserId;

    /**
     * @return the value of user_team.user_team_create_time
     * @mbg.generated
     */
    public Date getUserTeamCreateTime() {
        return userTeamCreateTime;
    }

    /**
     * @param userTeamCreateTime the value for user_team.user_team_create_time
     * @mbg.generated
     */
    public void setUserTeamCreateTime(Date userTeamCreateTime) {
        this.userTeamCreateTime = userTeamCreateTime;
    }

    /**
     * @return the value of user_team.user_team_create_user_id
     * @mbg.generated
     */
    public String getUserTeamCreateUserId() {
        return userTeamCreateUserId;
    }

    /**
     * @param userTeamCreateUserId the value for user_team.user_team_create_user_id
     * @mbg.generated
     */
    public void setUserTeamCreateUserId(String userTeamCreateUserId) {
        this.userTeamCreateUserId = userTeamCreateUserId == null ? null : userTeamCreateUserId.trim();
    }

    /**
     * @return the value of user_team.user_team_update_time
     * @mbg.generated
     */
    public Date getUserTeamUpdateTime() {
        return userTeamUpdateTime;
    }

    /**
     * @param userTeamUpdateTime the value for user_team.user_team_update_time
     * @mbg.generated
     */
    public void setUserTeamUpdateTime(Date userTeamUpdateTime) {
        this.userTeamUpdateTime = userTeamUpdateTime;
    }

    /**
     * @return the value of user_team.user_team_update_user_id
     * @mbg.generated
     */
    public String getUserTeamUpdateUserId() {
        return userTeamUpdateUserId;
    }

    /**
     * @param userTeamUpdateUserId the value for user_team.user_team_update_user_id
     * @mbg.generated
     */
    public void setUserTeamUpdateUserId(String userTeamUpdateUserId) {
        this.userTeamUpdateUserId = userTeamUpdateUserId == null ? null : userTeamUpdateUserId.trim();
    }
}