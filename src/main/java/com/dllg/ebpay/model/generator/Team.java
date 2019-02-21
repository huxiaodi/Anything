package com.dllg.ebpay.model.generator;

import java.util.Date;

/**
 *   项目组字典
 *   team
 * @mbg.generated do_not_delete_during_merge
 */
public class Team {
    /**
     *   主键ID
     *   team.team_id
     * @mbg.generated
     */
    private String teamId;

    /**
     *   企业id
     *   team.team_company_id
     * @mbg.generated
     */
    private String teamCompanyId;

    /**
     *   分组名称
     *   team.team_name
     * @mbg.generated
     */
    private String teamName;

    /**
     *   分组类型，0默认组，不可以删除，1自建组
     *   team.team_type
     * @mbg.generated
     */
    private Integer teamType;

    /**
     *   备注
     *   team.team_remark
     * @mbg.generated
     */
    private String teamRemark;

    /**
     *   创建时间
     *   team.team_create_time
     * @mbg.generated
     */
    private Date teamCreateTime;

    /**
     *   创建人
     *   team.team_create_user_id
     * @mbg.generated
     */
    private String teamCreateUserId;

    /**
     *   修改时间
     *   team.team_update_time
     * @mbg.generated
     */
    private Date teamUpdateTime;

    /**
     *   修改人
     *   team.team_update_user_id
     * @mbg.generated
     */
    private String teamUpdateUserId;

    /**
     *   是否删除 0-否,1-是
     *   team.team_is_delete
     * @mbg.generated
     */
    private Boolean teamIsDelete;

    private String teamCreateUser;
    private String teamUpdateUser;

    /**
     * 主键ID
     * @return the value of team.team_id
     * @mbg.generated
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * 主键ID
     * @param teamId the value for team.team_id
     * @mbg.generated
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    /**
     * 企业id
     * @return the value of team.team_company_id
     * @mbg.generated
     */
    public String getTeamCompanyId() {
        return teamCompanyId;
    }

    /**
     * 企业id
     * @param teamCompanyId the value for team.team_company_id
     * @mbg.generated
     */
    public void setTeamCompanyId(String teamCompanyId) {
        this.teamCompanyId = teamCompanyId == null ? null : teamCompanyId.trim();
    }

    /**
     * 分组名称
     * @return the value of team.team_name
     * @mbg.generated
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * 分组名称
     * @param teamName the value for team.team_name
     * @mbg.generated
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    /**
     * 分组类型，0默认组，不可以删除，1自建组
     * @return the value of team.team_type
     * @mbg.generated
     */
    public Integer getTeamType() {
        return teamType;
    }

    /**
     * 分组类型，0默认组，不可以删除，1自建组
     * @param teamType the value for team.team_type
     * @mbg.generated
     */
    public void setTeamType(Integer teamType) {
        this.teamType = teamType;
    }

    /**
     * 备注
     * @return the value of team.team_remark
     * @mbg.generated
     */
    public String getTeamRemark() {
        return teamRemark;
    }

    /**
     * 备注
     * @param teamRemark the value for team.team_remark
     * @mbg.generated
     */
    public void setTeamRemark(String teamRemark) {
        this.teamRemark = teamRemark == null ? null : teamRemark.trim();
    }

    /**
     * 创建时间
     * @return the value of team.team_create_time
     * @mbg.generated
     */
    public Date getTeamCreateTime() {
        return teamCreateTime;
    }

    /**
     * 创建时间
     * @param teamCreateTime the value for team.team_create_time
     * @mbg.generated
     */
    public void setTeamCreateTime(Date teamCreateTime) {
        this.teamCreateTime = teamCreateTime;
    }

    /**
     * 创建人
     * @return the value of team.team_create_user_id
     * @mbg.generated
     */
    public String getTeamCreateUserId() {
        return teamCreateUserId;
    }

    /**
     * 创建人
     * @param teamCreateUserId the value for team.team_create_user_id
     * @mbg.generated
     */
    public void setTeamCreateUserId(String teamCreateUserId) {
        this.teamCreateUserId = teamCreateUserId == null ? null : teamCreateUserId.trim();
    }

    /**
     * 修改时间
     * @return the value of team.team_update_time
     * @mbg.generated
     */
    public Date getTeamUpdateTime() {
        return teamUpdateTime;
    }

    /**
     * 修改时间
     * @param teamUpdateTime the value for team.team_update_time
     * @mbg.generated
     */
    public void setTeamUpdateTime(Date teamUpdateTime) {
        this.teamUpdateTime = teamUpdateTime;
    }

    /**
     * 修改人
     * @return the value of team.team_update_user_id
     * @mbg.generated
     */
    public String getTeamUpdateUserId() {
        return teamUpdateUserId;
    }

    /**
     * 修改人
     * @param teamUpdateUserId the value for team.team_update_user_id
     * @mbg.generated
     */
    public void setTeamUpdateUserId(String teamUpdateUserId) {
        this.teamUpdateUserId = teamUpdateUserId == null ? null : teamUpdateUserId.trim();
    }

    /**
     * 是否删除 0-否,1-是
     * @return the value of team.team_is_delete
     * @mbg.generated
     */
    public Boolean getTeamIsDelete() {
        return teamIsDelete;
    }

    /**
     * 是否删除 0-否,1-是
     * @param teamIsDelete the value for team.team_is_delete
     * @mbg.generated
     */
    public void setTeamIsDelete(Boolean teamIsDelete) {
        this.teamIsDelete = teamIsDelete;
    }

    public String getTeamCreateUser() {
        return teamCreateUser;
    }

    public void setTeamCreateUser(String teamCreateUser) {
        this.teamCreateUser = teamCreateUser;
    }

    public String getTeamUpdateUser() {
        return teamUpdateUser;
    }

    public void setTeamUpdateUser(String teamUpdateUser) {
        this.teamUpdateUser = teamUpdateUser;
    }
}