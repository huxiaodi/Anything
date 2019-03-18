package com.test.manage.model.generator;

import java.util.Date;

/**
 *   订单跟踪
 *   order_trace
 * @mbg.generated do_not_delete_during_merge
 */
public class OrderTrace {
    /**
     *   ID
     *   order_trace.order_trace_id
     * @mbg.generated
     */
    private String orderTraceId;

    /**
     *   跟踪信息
     *   order_trace.order_trace_content
     * @mbg.generated
     */
    private String orderTraceContent;

    /**
     *   追踪详情
     *   order_trace.order_trace_detail
     * @mbg.generated
     */
    private String orderTraceDetail;

    /**
     *   订单ID
     *   order_trace.order_trace_order_id
     * @mbg.generated
     */
    private String orderTraceOrderId;

    /**
     *   操作人ID
     *   order_trace.order_trace_create_user_id
     * @mbg.generated
     */
    private String orderTraceCreateUserId;

    /**
     *   创建时间
     *   order_trace.create_time
     * @mbg.generated
     */
    private Date createTime;

    /**
     * ID
     * @return the value of order_trace.order_trace_id
     * @mbg.generated
     */
    public String getOrderTraceId() {
        return orderTraceId;
    }

    /**
     * ID
     * @param orderTraceId the value for order_trace.order_trace_id
     * @mbg.generated
     */
    public void setOrderTraceId(String orderTraceId) {
        this.orderTraceId = orderTraceId == null ? null : orderTraceId.trim();
    }

    /**
     * 跟踪信息
     * @return the value of order_trace.order_trace_content
     * @mbg.generated
     */
    public String getOrderTraceContent() {
        return orderTraceContent;
    }

    /**
     * 跟踪信息
     * @param orderTraceContent the value for order_trace.order_trace_content
     * @mbg.generated
     */
    public void setOrderTraceContent(String orderTraceContent) {
        this.orderTraceContent = orderTraceContent == null ? null : orderTraceContent.trim();
    }

    /**
     * 追踪详情
     * @return the value of order_trace.order_trace_detail
     * @mbg.generated
     */
    public String getOrderTraceDetail() {
        return orderTraceDetail;
    }

    /**
     * 追踪详情
     * @param orderTraceDetail the value for order_trace.order_trace_detail
     * @mbg.generated
     */
    public void setOrderTraceDetail(String orderTraceDetail) {
        this.orderTraceDetail = orderTraceDetail == null ? null : orderTraceDetail.trim();
    }

    /**
     * 订单ID
     * @return the value of order_trace.order_trace_order_id
     * @mbg.generated
     */
    public String getOrderTraceOrderId() {
        return orderTraceOrderId;
    }

    /**
     * 订单ID
     * @param orderTraceOrderId the value for order_trace.order_trace_order_id
     * @mbg.generated
     */
    public void setOrderTraceOrderId(String orderTraceOrderId) {
        this.orderTraceOrderId = orderTraceOrderId == null ? null : orderTraceOrderId.trim();
    }

    /**
     * 操作人ID
     * @return the value of order_trace.order_trace_create_user_id
     * @mbg.generated
     */
    public String getOrderTraceCreateUserId() {
        return orderTraceCreateUserId;
    }

    /**
     * 操作人ID
     * @param orderTraceCreateUserId the value for order_trace.order_trace_create_user_id
     * @mbg.generated
     */
    public void setOrderTraceCreateUserId(String orderTraceCreateUserId) {
        this.orderTraceCreateUserId = orderTraceCreateUserId == null ? null : orderTraceCreateUserId.trim();
    }

    /**
     * 创建时间
     * @return the value of order_trace.create_time
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime the value for order_trace.create_time
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}