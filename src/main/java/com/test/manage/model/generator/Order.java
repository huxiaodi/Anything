package com.test.manage.model.generator;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   订单表
 *   goods_order
 * @mbg.generated do_not_delete_during_merge
 */
public class Order {
    /**
     *   订单ID
     *   goods_order.order_id
     * @mbg.generated
     */
    private String orderId;

    /**
     *   goods_order.order_no
     * @mbg.generated
     */
    private String orderNo;

    /**
     *   下单方id
     *   goods_order.order_company_id
     * @mbg.generated
     */
    private String orderCompanyId;

    /**
     *   接单方id
     *   goods_order.order_receive_company_id
     * @mbg.generated
     */
    private String orderReceiveCompanyId;

    /**
     *   0：不指定 1：指定
     *   goods_order.order_assign
     * @mbg.generated
     */
    private Boolean orderAssign;

    /**
     *   1：已下单 2：已接单3：已提货 4：已发货 5：已签收
     *   goods_order.order_status
     * @mbg.generated
     */
    private Integer orderStatus;

    /**
     *   物流单号
     *   goods_order.order_logistics_no
     * @mbg.generated
     */
    private String orderLogisticsNo;

    /**
     *   货物名称
     *   goods_order.order_goods_name
     * @mbg.generated
     */
    private String orderGoodsName;

    /**
     *   货物重量
     *   goods_order.order_goods_weight
     * @mbg.generated
     */
    private BigDecimal orderGoodsWeight;

    /**
     *   货物体积
     *   goods_order.order_goods_cube
     * @mbg.generated
     */
    private BigDecimal orderGoodsCube;

    /**
     *   费用
     *   goods_order.order_money
     * @mbg.generated
     */
    private BigDecimal orderMoney;

    /**
     *   发布时间
     *   goods_order.order_issue_time
     * @mbg.generated
     */
    private Date orderIssueTime;

    /**
     *   预计提货时间
     *   goods_order.order_pickup_time
     * @mbg.generated
     */
    private Date orderPickupTime;

    /**
     *   实际提货时间
     *   goods_order.order_final_pickup_time
     * @mbg.generated
     */
    private Date orderFinalPickupTime;

    /**
     *   到货时间
     *   goods_order.order_receive_time
     * @mbg.generated
     */
    private Date orderReceiveTime;

    /**
     *   发货地省份
     *   goods_order.order_pickup_province
     * @mbg.generated
     */
    private String orderPickupProvince;

    /**
     *   发货地城市
     *   goods_order.order_pickup_city
     * @mbg.generated
     */
    private String orderPickupCity;

    /**
     *   发货地区县
     *   goods_order.order_pickup_district
     * @mbg.generated
     */
    private String orderPickupDistrict;

    /**
     *   发货地区县
     *   goods_order.order_pickup_address
     * @mbg.generated
     */
    private String orderPickupAddress;

    /**
     *   收货地省份
     *   goods_order.order_receive_province
     * @mbg.generated
     */
    private String orderReceiveProvince;

    /**
     *   收货地城市
     *   goods_order.order_receive_city
     * @mbg.generated
     */
    private String orderReceiveCity;

    /**
     *   收货地区县
     *   goods_order.order_receive_district
     * @mbg.generated
     */
    private String orderReceiveDistrict;

    /**
     *   收货地区县
     *   goods_order.order_receive_address
     * @mbg.generated
     */
    private String orderReceiveAddress;

    /**
     *   备注
     *   goods_order.order_remark
     * @mbg.generated
     */
    private String orderRemark;

    /**
     *   下单人id
     *   goods_order.order_create_user_id
     * @mbg.generated
     */
    private String orderCreateUserId;

    /**
     *   接单人Id
     *   goods_order.order_receive_user_id
     * @mbg.generated
     */
    private String orderReceiveUserId;

    /**
     *   是否删除 0-否,1-是
     *   goods_order.order_is_delete
     * @mbg.generated
     */
    private Boolean orderIsDelete;

    /**
     *   订单创建时间
     *   goods_order.order_create_time
     * @mbg.generated
     */
    private Date orderCreateTime;

    /**
     * 订单ID
     * @return the value of goods_order.order_id
     * @mbg.generated
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 订单ID
     * @param orderId the value for goods_order.order_id
     * @mbg.generated
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * @return the value of goods_order.order_no
     * @mbg.generated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo the value for goods_order.order_no
     * @mbg.generated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 下单方id
     * @return the value of goods_order.order_company_id
     * @mbg.generated
     */
    public String getOrderCompanyId() {
        return orderCompanyId;
    }

    /**
     * 下单方id
     * @param orderCompanyId the value for goods_order.order_company_id
     * @mbg.generated
     */
    public void setOrderCompanyId(String orderCompanyId) {
        this.orderCompanyId = orderCompanyId == null ? null : orderCompanyId.trim();
    }

    /**
     * 接单方id
     * @return the value of goods_order.order_receive_company_id
     * @mbg.generated
     */
    public String getOrderReceiveCompanyId() {
        return orderReceiveCompanyId;
    }

    /**
     * 接单方id
     * @param orderReceiveCompanyId the value for goods_order.order_receive_company_id
     * @mbg.generated
     */
    public void setOrderReceiveCompanyId(String orderReceiveCompanyId) {
        this.orderReceiveCompanyId = orderReceiveCompanyId == null ? null : orderReceiveCompanyId.trim();
    }

    /**
     * 0：不指定 1：指定
     * @return the value of goods_order.order_assign
     * @mbg.generated
     */
    public Boolean getOrderAssign() {
        return orderAssign;
    }

    /**
     * 0：不指定 1：指定
     * @param orderAssign the value for goods_order.order_assign
     * @mbg.generated
     */
    public void setOrderAssign(Boolean orderAssign) {
        this.orderAssign = orderAssign;
    }

    /**
     * 1：已下单 2：已接单3：已提货 4：已发货 5：已签收
     * @return the value of goods_order.order_status
     * @mbg.generated
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 1：已下单 2：已接单3：已提货 4：已发货 5：已签收
     * @param orderStatus the value for goods_order.order_status
     * @mbg.generated
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 物流单号
     * @return the value of goods_order.order_logistics_no
     * @mbg.generated
     */
    public String getOrderLogisticsNo() {
        return orderLogisticsNo;
    }

    /**
     * 物流单号
     * @param orderLogisticsNo the value for goods_order.order_logistics_no
     * @mbg.generated
     */
    public void setOrderLogisticsNo(String orderLogisticsNo) {
        this.orderLogisticsNo = orderLogisticsNo == null ? null : orderLogisticsNo.trim();
    }

    /**
     * 货物名称
     * @return the value of goods_order.order_goods_name
     * @mbg.generated
     */
    public String getOrderGoodsName() {
        return orderGoodsName;
    }

    /**
     * 货物名称
     * @param orderGoodsName the value for goods_order.order_goods_name
     * @mbg.generated
     */
    public void setOrderGoodsName(String orderGoodsName) {
        this.orderGoodsName = orderGoodsName == null ? null : orderGoodsName.trim();
    }

    /**
     * 货物重量
     * @return the value of goods_order.order_goods_weight
     * @mbg.generated
     */
    public BigDecimal getOrderGoodsWeight() {
        return orderGoodsWeight;
    }

    /**
     * 货物重量
     * @param orderGoodsWeight the value for goods_order.order_goods_weight
     * @mbg.generated
     */
    public void setOrderGoodsWeight(BigDecimal orderGoodsWeight) {
        this.orderGoodsWeight = orderGoodsWeight;
    }

    /**
     * 货物体积
     * @return the value of goods_order.order_goods_cube
     * @mbg.generated
     */
    public BigDecimal getOrderGoodsCube() {
        return orderGoodsCube;
    }

    /**
     * 货物体积
     * @param orderGoodsCube the value for goods_order.order_goods_cube
     * @mbg.generated
     */
    public void setOrderGoodsCube(BigDecimal orderGoodsCube) {
        this.orderGoodsCube = orderGoodsCube;
    }

    /**
     * 费用
     * @return the value of goods_order.order_money
     * @mbg.generated
     */
    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    /**
     * 费用
     * @param orderMoney the value for goods_order.order_money
     * @mbg.generated
     */
    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    /**
     * 发布时间
     * @return the value of goods_order.order_issue_time
     * @mbg.generated
     */
    public Date getOrderIssueTime() {
        return orderIssueTime;
    }

    /**
     * 发布时间
     * @param orderIssueTime the value for goods_order.order_issue_time
     * @mbg.generated
     */
    public void setOrderIssueTime(Date orderIssueTime) {
        this.orderIssueTime = orderIssueTime;
    }

    /**
     * 预计提货时间
     * @return the value of goods_order.order_pickup_time
     * @mbg.generated
     */
    public Date getOrderPickupTime() {
        return orderPickupTime;
    }

    /**
     * 预计提货时间
     * @param orderPickupTime the value for goods_order.order_pickup_time
     * @mbg.generated
     */
    public void setOrderPickupTime(Date orderPickupTime) {
        this.orderPickupTime = orderPickupTime;
    }

    /**
     * 实际提货时间
     * @return the value of goods_order.order_final_pickup_time
     * @mbg.generated
     */
    public Date getOrderFinalPickupTime() {
        return orderFinalPickupTime;
    }

    /**
     * 实际提货时间
     * @param orderFinalPickupTime the value for goods_order.order_final_pickup_time
     * @mbg.generated
     */
    public void setOrderFinalPickupTime(Date orderFinalPickupTime) {
        this.orderFinalPickupTime = orderFinalPickupTime;
    }

    /**
     * 到货时间
     * @return the value of goods_order.order_receive_time
     * @mbg.generated
     */
    public Date getOrderReceiveTime() {
        return orderReceiveTime;
    }

    /**
     * 到货时间
     * @param orderReceiveTime the value for goods_order.order_receive_time
     * @mbg.generated
     */
    public void setOrderReceiveTime(Date orderReceiveTime) {
        this.orderReceiveTime = orderReceiveTime;
    }

    /**
     * 发货地省份
     * @return the value of goods_order.order_pickup_province
     * @mbg.generated
     */
    public String getOrderPickupProvince() {
        return orderPickupProvince;
    }

    /**
     * 发货地省份
     * @param orderPickupProvince the value for goods_order.order_pickup_province
     * @mbg.generated
     */
    public void setOrderPickupProvince(String orderPickupProvince) {
        this.orderPickupProvince = orderPickupProvince == null ? null : orderPickupProvince.trim();
    }

    /**
     * 发货地城市
     * @return the value of goods_order.order_pickup_city
     * @mbg.generated
     */
    public String getOrderPickupCity() {
        return orderPickupCity;
    }

    /**
     * 发货地城市
     * @param orderPickupCity the value for goods_order.order_pickup_city
     * @mbg.generated
     */
    public void setOrderPickupCity(String orderPickupCity) {
        this.orderPickupCity = orderPickupCity == null ? null : orderPickupCity.trim();
    }

    /**
     * 发货地区县
     * @return the value of goods_order.order_pickup_district
     * @mbg.generated
     */
    public String getOrderPickupDistrict() {
        return orderPickupDistrict;
    }

    /**
     * 发货地区县
     * @param orderPickupDistrict the value for goods_order.order_pickup_district
     * @mbg.generated
     */
    public void setOrderPickupDistrict(String orderPickupDistrict) {
        this.orderPickupDistrict = orderPickupDistrict == null ? null : orderPickupDistrict.trim();
    }

    /**
     * 发货地区县
     * @return the value of goods_order.order_pickup_address
     * @mbg.generated
     */
    public String getOrderPickupAddress() {
        return orderPickupAddress;
    }

    /**
     * 发货地区县
     * @param orderPickupAddress the value for goods_order.order_pickup_address
     * @mbg.generated
     */
    public void setOrderPickupAddress(String orderPickupAddress) {
        this.orderPickupAddress = orderPickupAddress == null ? null : orderPickupAddress.trim();
    }

    /**
     * 收货地省份
     * @return the value of goods_order.order_receive_province
     * @mbg.generated
     */
    public String getOrderReceiveProvince() {
        return orderReceiveProvince;
    }

    /**
     * 收货地省份
     * @param orderReceiveProvince the value for goods_order.order_receive_province
     * @mbg.generated
     */
    public void setOrderReceiveProvince(String orderReceiveProvince) {
        this.orderReceiveProvince = orderReceiveProvince == null ? null : orderReceiveProvince.trim();
    }

    /**
     * 收货地城市
     * @return the value of goods_order.order_receive_city
     * @mbg.generated
     */
    public String getOrderReceiveCity() {
        return orderReceiveCity;
    }

    /**
     * 收货地城市
     * @param orderReceiveCity the value for goods_order.order_receive_city
     * @mbg.generated
     */
    public void setOrderReceiveCity(String orderReceiveCity) {
        this.orderReceiveCity = orderReceiveCity == null ? null : orderReceiveCity.trim();
    }

    /**
     * 收货地区县
     * @return the value of goods_order.order_receive_district
     * @mbg.generated
     */
    public String getOrderReceiveDistrict() {
        return orderReceiveDistrict;
    }

    /**
     * 收货地区县
     * @param orderReceiveDistrict the value for goods_order.order_receive_district
     * @mbg.generated
     */
    public void setOrderReceiveDistrict(String orderReceiveDistrict) {
        this.orderReceiveDistrict = orderReceiveDistrict == null ? null : orderReceiveDistrict.trim();
    }

    /**
     * 收货地区县
     * @return the value of goods_order.order_receive_address
     * @mbg.generated
     */
    public String getOrderReceiveAddress() {
        return orderReceiveAddress;
    }

    /**
     * 收货地区县
     * @param orderReceiveAddress the value for goods_order.order_receive_address
     * @mbg.generated
     */
    public void setOrderReceiveAddress(String orderReceiveAddress) {
        this.orderReceiveAddress = orderReceiveAddress == null ? null : orderReceiveAddress.trim();
    }

    /**
     * 备注
     * @return the value of goods_order.order_remark
     * @mbg.generated
     */
    public String getOrderRemark() {
        return orderRemark;
    }

    /**
     * 备注
     * @param orderRemark the value for goods_order.order_remark
     * @mbg.generated
     */
    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark == null ? null : orderRemark.trim();
    }

    /**
     * 下单人id
     * @return the value of goods_order.order_create_user_id
     * @mbg.generated
     */
    public String getOrderCreateUserId() {
        return orderCreateUserId;
    }

    /**
     * 下单人id
     * @param orderCreateUserId the value for goods_order.order_create_user_id
     * @mbg.generated
     */
    public void setOrderCreateUserId(String orderCreateUserId) {
        this.orderCreateUserId = orderCreateUserId == null ? null : orderCreateUserId.trim();
    }

    /**
     * 接单人Id
     * @return the value of goods_order.order_receive_user_id
     * @mbg.generated
     */
    public String getOrderReceiveUserId() {
        return orderReceiveUserId;
    }

    /**
     * 接单人Id
     * @param orderReceiveUserId the value for goods_order.order_receive_user_id
     * @mbg.generated
     */
    public void setOrderReceiveUserId(String orderReceiveUserId) {
        this.orderReceiveUserId = orderReceiveUserId == null ? null : orderReceiveUserId.trim();
    }

    /**
     * 是否删除 0-否,1-是
     * @return the value of goods_order.order_is_delete
     * @mbg.generated
     */
    public Boolean getOrderIsDelete() {
        return orderIsDelete;
    }

    /**
     * 是否删除 0-否,1-是
     * @param orderIsDelete the value for goods_order.order_is_delete
     * @mbg.generated
     */
    public void setOrderIsDelete(Boolean orderIsDelete) {
        this.orderIsDelete = orderIsDelete;
    }

    /**
     * 订单创建时间
     * @return the value of goods_order.order_create_time
     * @mbg.generated
     */
    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    /**
     * 订单创建时间
     * @param orderCreateTime the value for goods_order.order_create_time
     * @mbg.generated
     */
    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }
}