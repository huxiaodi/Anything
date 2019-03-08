package com.test.manage.model.generator;

/**
 *   省市区
 *   area
 * @mbg.generated do_not_delete_during_merge
 */
public class Area {
    /**
     *   地区编码
     *   area.area_id
     * @mbg.generated
     */
    private String areaId;

    /**
     *   区域名称
     *   area.area_name
     * @mbg.generated
     */
    private String areaName;

    /**
     *   父节点id
     *   area.area_parent_id
     * @mbg.generated
     */
    private String areaParentId;

    /**
     *   当前层级，省-1，市-2，区-3
     *   area.area_level
     * @mbg.generated
     */
    private String areaLevel;

    /**
     * 地区编码
     * @return the value of area.area_id
     * @mbg.generated
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 地区编码
     * @param areaId the value for area.area_id
     * @mbg.generated
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * 区域名称
     * @return the value of area.area_name
     * @mbg.generated
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 区域名称
     * @param areaName the value for area.area_name
     * @mbg.generated
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 父节点id
     * @return the value of area.area_parent_id
     * @mbg.generated
     */
    public String getAreaParentId() {
        return areaParentId;
    }

    /**
     * 父节点id
     * @param areaParentId the value for area.area_parent_id
     * @mbg.generated
     */
    public void setAreaParentId(String areaParentId) {
        this.areaParentId = areaParentId == null ? null : areaParentId.trim();
    }

    /**
     * 当前层级，省-1，市-2，区-3
     * @return the value of area.area_level
     * @mbg.generated
     */
    public String getAreaLevel() {
        return areaLevel;
    }

    /**
     * 当前层级，省-1，市-2，区-3
     * @param areaLevel the value for area.area_level
     * @mbg.generated
     */
    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel == null ? null : areaLevel.trim();
    }
}