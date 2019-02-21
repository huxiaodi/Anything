package com.dllg.ebpay.model.generator;

/**
 *   用户和数据组关系表
 *   user_team
 * @mbg.generated do_not_delete_during_merge
 */
public class UserTeamKey {
    /**
     *   user_team.user_team_user_id
     * @mbg.generated
     */
    private String userTeamUserId;

    /**
     *   user_team.user_team_team_id
     * @mbg.generated
     */
    private String userTeamTeamId;

    /**
     * @return the value of user_team.user_team_user_id
     * @mbg.generated
     */
    public String getUserTeamUserId() {
        return userTeamUserId;
    }

    /**
     * @param userTeamUserId the value for user_team.user_team_user_id
     * @mbg.generated
     */
    public void setUserTeamUserId(String userTeamUserId) {
        this.userTeamUserId = userTeamUserId == null ? null : userTeamUserId.trim();
    }

    /**
     * @return the value of user_team.user_team_team_id
     * @mbg.generated
     */
    public String getUserTeamTeamId() {
        return userTeamTeamId;
    }

    /**
     * @param userTeamTeamId the value for user_team.user_team_team_id
     * @mbg.generated
     */
    public void setUserTeamTeamId(String userTeamTeamId) {
        this.userTeamTeamId = userTeamTeamId == null ? null : userTeamTeamId.trim();
    }
}