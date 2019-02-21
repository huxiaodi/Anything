package com.dllg.ebpay.model.generator;

import java.util.Date;

/**
 *   请求json data
 *   json_data
 * @mbg.generated do_not_delete_during_merge
 */
public class JsonData {
    /**
     *   id
     *   json_data.json_data_id
     * @mbg.generated
     */
    private String jsonDataId;

    /**
     *   关联id
     *   json_data.json_data_relation_id
     * @mbg.generated
     */
    private String jsonDataRelationId;

    /**
     *   平安方法名
     *   json_data.json_data_name
     * @mbg.generated
     */
    private String jsonDataName;

    /**
     *   请求json
     *   json_data.json_data_request
     * @mbg.generated
     */
    private String jsonDataRequest;

    /**
     *   返回json
     *   json_data.json_data_response
     * @mbg.generated
     */
    private String jsonDataResponse;

    /**
     *   创建时间
     *   json_data.json_data_create_time
     * @mbg.generated
     */
    private Date jsonDataCreateTime;

    /**
     *   修改时间
     *   json_data.json_data_update_time
     * @mbg.generated
     */
    private Date jsonDataUpdateTime;

    /**
     *   状态，0-创建，1-成功，2-失败
     *   json_data.json_data_status
     * @mbg.generated
     */
    private Integer jsonDataStatus;

    /**
     * id
     * @return the value of json_data.json_data_id
     * @mbg.generated
     */
    public String getJsonDataId() {
        return jsonDataId;
    }

    /**
     * id
     * @param jsonDataId the value for json_data.json_data_id
     * @mbg.generated
     */
    public void setJsonDataId(String jsonDataId) {
        this.jsonDataId = jsonDataId == null ? null : jsonDataId.trim();
    }

    /**
     * 关联id
     * @return the value of json_data.json_data_relation_id
     * @mbg.generated
     */
    public String getJsonDataRelationId() {
        return jsonDataRelationId;
    }

    /**
     * 关联id
     * @param jsonDataRelationId the value for json_data.json_data_relation_id
     * @mbg.generated
     */
    public void setJsonDataRelationId(String jsonDataRelationId) {
        this.jsonDataRelationId = jsonDataRelationId == null ? null : jsonDataRelationId.trim();
    }

    /**
     * 平安方法名
     * @return the value of json_data.json_data_name
     * @mbg.generated
     */
    public String getJsonDataName() {
        return jsonDataName;
    }

    /**
     * 平安方法名
     * @param jsonDataName the value for json_data.json_data_name
     * @mbg.generated
     */
    public void setJsonDataName(String jsonDataName) {
        this.jsonDataName = jsonDataName == null ? null : jsonDataName.trim();
    }

    /**
     * 请求json
     * @return the value of json_data.json_data_request
     * @mbg.generated
     */
    public String getJsonDataRequest() {
        return jsonDataRequest;
    }

    /**
     * 请求json
     * @param jsonDataRequest the value for json_data.json_data_request
     * @mbg.generated
     */
    public void setJsonDataRequest(String jsonDataRequest) {
        this.jsonDataRequest = jsonDataRequest == null ? null : jsonDataRequest.trim();
    }

    /**
     * 返回json
     * @return the value of json_data.json_data_response
     * @mbg.generated
     */
    public String getJsonDataResponse() {
        return jsonDataResponse;
    }

    /**
     * 返回json
     * @param jsonDataResponse the value for json_data.json_data_response
     * @mbg.generated
     */
    public void setJsonDataResponse(String jsonDataResponse) {
        this.jsonDataResponse = jsonDataResponse == null ? null : jsonDataResponse.trim();
    }

    /**
     * 创建时间
     * @return the value of json_data.json_data_create_time
     * @mbg.generated
     */
    public Date getJsonDataCreateTime() {
        return jsonDataCreateTime;
    }

    /**
     * 创建时间
     * @param jsonDataCreateTime the value for json_data.json_data_create_time
     * @mbg.generated
     */
    public void setJsonDataCreateTime(Date jsonDataCreateTime) {
        this.jsonDataCreateTime = jsonDataCreateTime;
    }

    /**
     * 修改时间
     * @return the value of json_data.json_data_update_time
     * @mbg.generated
     */
    public Date getJsonDataUpdateTime() {
        return jsonDataUpdateTime;
    }

    /**
     * 修改时间
     * @param jsonDataUpdateTime the value for json_data.json_data_update_time
     * @mbg.generated
     */
    public void setJsonDataUpdateTime(Date jsonDataUpdateTime) {
        this.jsonDataUpdateTime = jsonDataUpdateTime;
    }

    /**
     * 状态，0-创建，1-成功，2-失败
     * @return the value of json_data.json_data_status
     * @mbg.generated
     */
    public Integer getJsonDataStatus() {
        return jsonDataStatus;
    }

    /**
     * 状态，0-创建，1-成功，2-失败
     * @param jsonDataStatus the value for json_data.json_data_status
     * @mbg.generated
     */
    public void setJsonDataStatus(Integer jsonDataStatus) {
        this.jsonDataStatus = jsonDataStatus;
    }
}