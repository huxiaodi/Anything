package com.test.manage.model.form;

import com.test.manage.model.generator.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 13:49 2019/3/12
 */
@Data
public class OrderForm {
    private String orderGoodsName; // 货物名称
    private BigDecimal orderGoodsWeight; // 货物重量
    private BigDecimal orderGoodsCube; // 货物体积
    private BigDecimal orderMoney; // 金额
    private String[] startingPlaceCode; // 起始地代码
    private String[] startingPlaceName; // 起始地名称
    private String orderPickupAddress; // 起始地详细地址
    private String[] endingPlaceCode; // 目的地代码
    private String[] endingPlaceName; // 目的地名称
    private String orderReceiveAddress; // 目的地详细地址
    private Date orderPickupTime; // 提货时间
    private Date orderIssueTime; // 发布时间
    private Boolean orderAssign; // 是否指定
    private String orderReceiveCompanyId; // 指定放id
    private String orderRemark; // 备注

}
