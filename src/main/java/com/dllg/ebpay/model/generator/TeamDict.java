package com.dllg.ebpay.model.generator;

import java.util.Date;

/**
 *   team_dict
 * @mbg.generated do_not_delete_during_merge
 */
public class TeamDict extends TeamDictKey {
    /**
     *   创建时间
     *   team_dict.team_dict_create_time
     * @mbg.generated
     */
    private Date teamDictCreateTime;

    /**
     *   创建人
     *   team_dict.team_dict_create_user_id
     * @mbg.generated
     */
    private String teamDictCreateUserId;

    /**
     * 创建时间
     * @return the value of team_dict.team_dict_create_time
     * @mbg.generated
     */
    public Date getTeamDictCreateTime() {
        return teamDictCreateTime;
    }

    /**
     * 创建时间
     * @param teamDictCreateTime the value for team_dict.team_dict_create_time
     * @mbg.generated
     */
    public void setTeamDictCreateTime(Date teamDictCreateTime) {
        this.teamDictCreateTime = teamDictCreateTime;
    }

    /**
     * 创建人
     * @return the value of team_dict.team_dict_create_user_id
     * @mbg.generated
     */
    public String getTeamDictCreateUserId() {
        return teamDictCreateUserId;
    }

    /**
     * 创建人
     * @param teamDictCreateUserId the value for team_dict.team_dict_create_user_id
     * @mbg.generated
     */
    public void setTeamDictCreateUserId(String teamDictCreateUserId) {
        this.teamDictCreateUserId = teamDictCreateUserId == null ? null : teamDictCreateUserId.trim();
    }
}