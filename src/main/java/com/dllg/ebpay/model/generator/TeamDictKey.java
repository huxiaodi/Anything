package com.dllg.ebpay.model.generator;

/**
 *   team_dict
 * @mbg.generated do_not_delete_during_merge
 */
public class TeamDictKey {
    /**
     *   team_id
     *   team_dict.team_dict_team_id
     * @mbg.generated
     */
    private String teamDictTeamId;

    /**
     *   dict_id
     *   team_dict.team_dict_dict_id
     * @mbg.generated
     */
    private String teamDictDictId;

    /**
     * team_id
     * @return the value of team_dict.team_dict_team_id
     * @mbg.generated
     */
    public String getTeamDictTeamId() {
        return teamDictTeamId;
    }

    /**
     * team_id
     * @param teamDictTeamId the value for team_dict.team_dict_team_id
     * @mbg.generated
     */
    public void setTeamDictTeamId(String teamDictTeamId) {
        this.teamDictTeamId = teamDictTeamId == null ? null : teamDictTeamId.trim();
    }

    /**
     * dict_id
     * @return the value of team_dict.team_dict_dict_id
     * @mbg.generated
     */
    public String getTeamDictDictId() {
        return teamDictDictId;
    }

    /**
     * dict_id
     * @param teamDictDictId the value for team_dict.team_dict_dict_id
     * @mbg.generated
     */
    public void setTeamDictDictId(String teamDictDictId) {
        this.teamDictDictId = teamDictDictId == null ? null : teamDictDictId.trim();
    }
}