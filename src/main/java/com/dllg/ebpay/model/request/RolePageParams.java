package com.dllg.ebpay.model.request;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RolePageParams extends Page {
    private Date startTime;
    private Date endTime;
    private String keyword;
    private String roleId;
    private String roleName;
    private String roleEnName;
    private Integer roleIndex;
    private String roleCompanyId;
    List<String> resourceIds = new ArrayList<>();


    public String getRoleCompanyId() {
        return roleCompanyId;
    }

    public void setRoleCompanyId(String roleCompanyId) {
        this.roleCompanyId = roleCompanyId;
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleEnName() {
        return roleEnName;
    }

    public void setRoleEnName(String roleEnName) {
        this.roleEnName = roleEnName;
    }

    public Integer getRoleIndex() {
        return roleIndex;
    }

    public void setRoleIndex(Integer roleIndex) {
        this.roleIndex = roleIndex;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
