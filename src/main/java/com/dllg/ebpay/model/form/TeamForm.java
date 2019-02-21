package com.dllg.ebpay.model.form;

import com.dllg.framework.model.BaseForm;

import java.util.Date;

public class TeamForm extends BaseForm {

    private String teamName;
    private String  teamId;
    private String  teamRemark;
    private String[] system;
    private String[] source;

    public String[] getSystem() {
        return system;
    }

    public void setSystem(String[] system) {
        this.system = system;
    }

    public String[] getSource() {
        return source;
    }

    public void setSource(String[] source) {
        this.source = source;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamRemark() {
        return teamRemark;
    }

    public void setTeamRemark(String teamRemark) {
        this.teamRemark = teamRemark;
    }
}
