package com.test.manage.model.dto;

import java.util.List;

public class Menu {
    private String resourceId;
    private String parentResourceId;
    private String resourceName;
    private String resourceEnName;
    private String resourceValue;
    private String resourceIcon;
    private Integer resourceType;
    private Integer resourceRank;
    private Integer resourceIndex;
    private Integer resourceStatus;

    private List<Menu> children;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getParentResourceId() {
        return parentResourceId;
    }

    public void setParentResourceId(String parentResourceId) {
        this.parentResourceId = parentResourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceEnName() {
        return resourceEnName;
    }

    public void setResourceEnName(String resourceEnName) {
        this.resourceEnName = resourceEnName;
    }

    public String getResourceValue() {
        return resourceValue;
    }

    public void setResourceValue(String resourceValue) {
        this.resourceValue = resourceValue;
    }

    public String getResourceIcon() {
        return resourceIcon;
    }

    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getResourceRank() {
        return resourceRank;
    }

    public void setResourceRank(Integer resourceRank) {
        this.resourceRank = resourceRank;
    }

    public Integer getResourceIndex() {
        return resourceIndex;
    }

    public void setResourceIndex(Integer resourceIndex) {
        this.resourceIndex = resourceIndex;
    }

    public Integer getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(Integer resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
