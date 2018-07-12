package com.velvol.salary.domain;

import java.io.Serializable;
import java.util.Date;

public class SalaryOrderDetail  extends BaseModel {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 工资计算记录表id
     */
    private Integer recordId;

    /**
     * 
     */
    private String orderId;

    /**
     * 
     */
    private String waybillId;

    /**
     * 商家流水号
     */
    private String sellerNum;

    /**
     * 商家名称
     */
    private String sellerName;

    /**
     * 商家ID
     */
    private String sellerId;

    /**
     * 
     */
    private String city;

    /**
     * 
     */
    private String riderName;

    /**
     * 骑手ID
     */
    private String riderId;

    /**
     * 
     */
    private String stationName;

    /**
     * 
     */
    private String stataionId;

    /**
     * 商圈
     */
    private String tradeArea;

    /**
     * 预订单
     */
    private String preorder;

    /**
     * 状态
     */
    private String status;

    /**
     * 组织类型
     */
    private String organizationType;

    /**
     * 众包类型
     */
    private String epibolyType;

    /**
     * 配送时效
     */
    private Double deliveryDuration;

    /**
     * 等待时长
     */
    private String waitDuration;

    /**
     * 送达时长
     */
    private String arriveDuration;

    /**
     * 连击时长
     */
    private String hitDuration;

    /**
     * 导航距离
     */
    private Integer navDistance;

    /**
     * 折线距离
     */
    private Integer lineDistance;

    /**
     * 商家地址
     */
    private String sellerAddress;

    /**
     * 商家配送评分
     */
    private String sellerDeliveryScore;

    /**
     * 商家配送评价
     */
    private String sellerDeliveryApprise;

    /**
     * 订单原价
     */
    private String orderOriginalPrice;

    /**
     * 订单金额
     */
    private Double orderPrice;

    /**
     * 折扣金额
     */
    private String discountPrice;

    /**
     * 付商家款
     */
    private String paySeller;

    /**
     * 实际付款
     */
    private String actualPay;

    /**
     * 收用户款
     */
    private String userPay;

    /**
     * 实际收款
     */
    private String actualGather;

    /**
     * 配送费
     */
    private String deliveryPrice;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 期望送达时间
     */
    private Date hopeTime;

    /**
     * 商家推单时间
     */
    private Date sellerPushTime;

    /**
     * 调度时间
     */
    private Date dispatchTime;

    /**
     * 接单时间
     */
    private Date receiveTime;

    /**
     * 到店时间
     */
    private Date arriveShopTime;

    /**
     * 取货时间
     */
    private Date pickupTime;

    /**
     * 送达时间
     */
    private Date arriveTime;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 取消操作人
     */
    private String cancelOperator;

    /**
     * 申请退款原因
     */
    private String applyRefundReason;

    /**
     * 申请退款操作人
     */
    private String applyRefundOperator;

    /**
     * 
     */
    private String text1;

    /**
     * 
     */
    private String text2;

    /**
     * 
     */
    private String text3;

    /**
     * 
     */
    private String text4;

    /**
     * 
     */
    private String text5;

    /**
     * salary_order_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     * @return id 自增id
     */
    public Long getId() {
        return id;
    }

    /**
     * 自增id
     * @param id 自增id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 工资计算记录表id
     * @return record_id 工资计算记录表id
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * 工资计算记录表id
     * @param recordId 工资计算记录表id
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /**
     * 
     * @return order_id 
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 
     * @param orderId 
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 
     * @return waybill_id 
     */
    public String getWaybillId() {
        return waybillId;
    }

    /**
     * 
     * @param waybillId 
     */
    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId == null ? null : waybillId.trim();
    }

    /**
     * 商家流水号
     * @return seller_num 商家流水号
     */
    public String getSellerNum() {
        return sellerNum;
    }

    /**
     * 商家流水号
     * @param sellerNum 商家流水号
     */
    public void setSellerNum(String sellerNum) {
        this.sellerNum = sellerNum == null ? null : sellerNum.trim();
    }

    /**
     * 商家名称
     * @return seller_name 商家名称
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * 商家名称
     * @param sellerName 商家名称
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    /**
     * 商家ID
     * @return seller_id 商家ID
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * 商家ID
     * @param sellerId 商家ID
     */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    /**
     * 
     * @return city 
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city 
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 
     * @return rider_name 
     */
    public String getRiderName() {
        return riderName;
    }

    /**
     * 
     * @param riderName 
     */
    public void setRiderName(String riderName) {
        this.riderName = riderName == null ? null : riderName.trim();
    }

    /**
     * 骑手ID
     * @return rider_id 骑手ID
     */
    public String getRiderId() {
        return riderId;
    }

    /**
     * 骑手ID
     * @param riderId 骑手ID
     */
    public void setRiderId(String riderId) {
        this.riderId = riderId == null ? null : riderId.trim();
    }

    /**
     * 
     * @return station_name 
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * 
     * @param stationName 
     */
    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    /**
     * 
     * @return stataion_id 
     */
    public String getStataionId() {
        return stataionId;
    }

    /**
     * 
     * @param stataionId 
     */
    public void setStataionId(String stataionId) {
        this.stataionId = stataionId == null ? null : stataionId.trim();
    }

    /**
     * 商圈
     * @return trade_area 商圈
     */
    public String getTradeArea() {
        return tradeArea;
    }

    /**
     * 商圈
     * @param tradeArea 商圈
     */
    public void setTradeArea(String tradeArea) {
        this.tradeArea = tradeArea == null ? null : tradeArea.trim();
    }

    /**
     * 预订单
     * @return preorder 预订单
     */
    public String getPreorder() {
        return preorder;
    }

    /**
     * 预订单
     * @param preorder 预订单
     */
    public void setPreorder(String preorder) {
        this.preorder = preorder == null ? null : preorder.trim();
    }

    /**
     * 状态
     * @return status 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 组织类型
     * @return organization_type 组织类型
     */
    public String getOrganizationType() {
        return organizationType;
    }

    /**
     * 组织类型
     * @param organizationType 组织类型
     */
    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType == null ? null : organizationType.trim();
    }

    /**
     * 众包类型
     * @return epiboly_type 众包类型
     */
    public String getEpibolyType() {
        return epibolyType;
    }

    /**
     * 众包类型
     * @param epibolyType 众包类型
     */
    public void setEpibolyType(String epibolyType) {
        this.epibolyType = epibolyType == null ? null : epibolyType.trim();
    }

    /**
     * 配送时效
     * @return delivery_duration 配送时效
     */
    public Double getDeliveryDuration() {
        return deliveryDuration;
    }

    /**
     * 配送时效
     * @param deliveryDuration 配送时效
     */
    public void setDeliveryDuration(Double deliveryDuration) {
        this.deliveryDuration = deliveryDuration;
    }

    /**
     * 等待时长
     * @return wait_duration 等待时长
     */
    public String getWaitDuration() {
        return waitDuration;
    }

    /**
     * 等待时长
     * @param waitDuration 等待时长
     */
    public void setWaitDuration(String waitDuration) {
        this.waitDuration = waitDuration == null ? null : waitDuration.trim();
    }

    /**
     * 送达时长
     * @return arrive_duration 送达时长
     */
    public String getArriveDuration() {
        return arriveDuration;
    }

    /**
     * 送达时长
     * @param arriveDuration 送达时长
     */
    public void setArriveDuration(String arriveDuration) {
        this.arriveDuration = arriveDuration == null ? null : arriveDuration.trim();
    }

    /**
     * 连击时长
     * @return hit_duration 连击时长
     */
    public String getHitDuration() {
        return hitDuration;
    }

    /**
     * 连击时长
     * @param hitDuration 连击时长
     */
    public void setHitDuration(String hitDuration) {
        this.hitDuration = hitDuration == null ? null : hitDuration.trim();
    }

    /**
     * 导航距离
     * @return nav_distance 导航距离
     */
    public Integer getNavDistance() {
        return navDistance;
    }

    /**
     * 导航距离
     * @param navDistance 导航距离
     */
    public void setNavDistance(Integer navDistance) {
        this.navDistance = navDistance;
    }

    /**
     * 折线距离
     * @return line_distance 折线距离
     */
    public Integer getLineDistance() {
        return lineDistance;
    }

    /**
     * 折线距离
     * @param lineDistance 折线距离
     */
    public void setLineDistance(Integer lineDistance) {
        this.lineDistance = lineDistance;
    }

    /**
     * 商家地址
     * @return seller_address 商家地址
     */
    public String getSellerAddress() {
        return sellerAddress;
    }

    /**
     * 商家地址
     * @param sellerAddress 商家地址
     */
    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress == null ? null : sellerAddress.trim();
    }

    /**
     * 商家配送评分
     * @return seller_delivery_score 商家配送评分
     */
    public String getSellerDeliveryScore() {
        return sellerDeliveryScore;
    }

    /**
     * 商家配送评分
     * @param sellerDeliveryScore 商家配送评分
     */
    public void setSellerDeliveryScore(String sellerDeliveryScore) {
        this.sellerDeliveryScore = sellerDeliveryScore == null ? null : sellerDeliveryScore.trim();
    }

    /**
     * 商家配送评价
     * @return seller_delivery_apprise 商家配送评价
     */
    public String getSellerDeliveryApprise() {
        return sellerDeliveryApprise;
    }

    /**
     * 商家配送评价
     * @param sellerDeliveryApprise 商家配送评价
     */
    public void setSellerDeliveryApprise(String sellerDeliveryApprise) {
        this.sellerDeliveryApprise = sellerDeliveryApprise == null ? null : sellerDeliveryApprise.trim();
    }

    /**
     * 订单原价
     * @return order_original_price 订单原价
     */
    public String getOrderOriginalPrice() {
        return orderOriginalPrice;
    }

    /**
     * 订单原价
     * @param orderOriginalPrice 订单原价
     */
    public void setOrderOriginalPrice(String orderOriginalPrice) {
        this.orderOriginalPrice = orderOriginalPrice == null ? null : orderOriginalPrice.trim();
    }

    /**
     * 订单金额
     * @return order_price 订单金额
     */
    public Double getOrderPrice() {
        return orderPrice;
    }

    /**
     * 订单金额
     * @param orderPrice 订单金额
     */
    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * 折扣金额
     * @return discount_price 折扣金额
     */
    public String getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 折扣金额
     * @param discountPrice 折扣金额
     */
    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice == null ? null : discountPrice.trim();
    }

    /**
     * 付商家款
     * @return pay_seller 付商家款
     */
    public String getPaySeller() {
        return paySeller;
    }

    /**
     * 付商家款
     * @param paySeller 付商家款
     */
    public void setPaySeller(String paySeller) {
        this.paySeller = paySeller == null ? null : paySeller.trim();
    }

    /**
     * 实际付款
     * @return actual_pay 实际付款
     */
    public String getActualPay() {
        return actualPay;
    }

    /**
     * 实际付款
     * @param actualPay 实际付款
     */
    public void setActualPay(String actualPay) {
        this.actualPay = actualPay == null ? null : actualPay.trim();
    }

    /**
     * 收用户款
     * @return user_pay 收用户款
     */
    public String getUserPay() {
        return userPay;
    }

    /**
     * 收用户款
     * @param userPay 收用户款
     */
    public void setUserPay(String userPay) {
        this.userPay = userPay == null ? null : userPay.trim();
    }

    /**
     * 实际收款
     * @return actual_gather 实际收款
     */
    public String getActualGather() {
        return actualGather;
    }

    /**
     * 实际收款
     * @param actualGather 实际收款
     */
    public void setActualGather(String actualGather) {
        this.actualGather = actualGather == null ? null : actualGather.trim();
    }

    /**
     * 配送费
     * @return delivery_price 配送费
     */
    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    /**
     * 配送费
     * @param deliveryPrice 配送费
     */
    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice == null ? null : deliveryPrice.trim();
    }

    /**
     * 下单时间
     * @return order_time 下单时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 下单时间
     * @param orderTime 下单时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 支付时间
     * @return pay_time 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 支付时间
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 期望送达时间
     * @return hope_time 期望送达时间
     */
    public Date getHopeTime() {
        return hopeTime;
    }

    /**
     * 期望送达时间
     * @param hopeTime 期望送达时间
     */
    public void setHopeTime(Date hopeTime) {
        this.hopeTime = hopeTime;
    }

    /**
     * 商家推单时间
     * @return seller_push_time 商家推单时间
     */
    public Date getSellerPushTime() {
        return sellerPushTime;
    }

    /**
     * 商家推单时间
     * @param sellerPushTime 商家推单时间
     */
    public void setSellerPushTime(Date sellerPushTime) {
        this.sellerPushTime = sellerPushTime;
    }

    /**
     * 调度时间
     * @return dispatch_time 调度时间
     */
    public Date getDispatchTime() {
        return dispatchTime;
    }

    /**
     * 调度时间
     * @param dispatchTime 调度时间
     */
    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    /**
     * 接单时间
     * @return receive_time 接单时间
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * 接单时间
     * @param receiveTime 接单时间
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 到店时间
     * @return arrive_shop_time 到店时间
     */
    public Date getArriveShopTime() {
        return arriveShopTime;
    }

    /**
     * 到店时间
     * @param arriveShopTime 到店时间
     */
    public void setArriveShopTime(Date arriveShopTime) {
        this.arriveShopTime = arriveShopTime;
    }

    /**
     * 取货时间
     * @return pickup_time 取货时间
     */
    public Date getPickupTime() {
        return pickupTime;
    }

    /**
     * 取货时间
     * @param pickupTime 取货时间
     */
    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    /**
     * 送达时间
     * @return arrive_time 送达时间
     */
    public Date getArriveTime() {
        return arriveTime;
    }

    /**
     * 送达时间
     * @param arriveTime 送达时间
     */
    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * 取消时间
     * @return cancel_time 取消时间
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 取消时间
     * @param cancelTime 取消时间
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * 取消原因
     * @return cancel_reason 取消原因
     */
    public String getCancelReason() {
        return cancelReason;
    }

    /**
     * 取消原因
     * @param cancelReason 取消原因
     */
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    /**
     * 取消操作人
     * @return cancel_operator 取消操作人
     */
    public String getCancelOperator() {
        return cancelOperator;
    }

    /**
     * 取消操作人
     * @param cancelOperator 取消操作人
     */
    public void setCancelOperator(String cancelOperator) {
        this.cancelOperator = cancelOperator == null ? null : cancelOperator.trim();
    }

    /**
     * 申请退款原因
     * @return apply_refund_reason 申请退款原因
     */
    public String getApplyRefundReason() {
        return applyRefundReason;
    }

    /**
     * 申请退款原因
     * @param applyRefundReason 申请退款原因
     */
    public void setApplyRefundReason(String applyRefundReason) {
        this.applyRefundReason = applyRefundReason == null ? null : applyRefundReason.trim();
    }

    /**
     * 申请退款操作人
     * @return apply_refund_operator 申请退款操作人
     */
    public String getApplyRefundOperator() {
        return applyRefundOperator;
    }

    /**
     * 申请退款操作人
     * @param applyRefundOperator 申请退款操作人
     */
    public void setApplyRefundOperator(String applyRefundOperator) {
        this.applyRefundOperator = applyRefundOperator == null ? null : applyRefundOperator.trim();
    }

    /**
     * 
     * @return text1 
     */
    public String getText1() {
        return text1;
    }

    /**
     * 
     * @param text1 
     */
    public void setText1(String text1) {
        this.text1 = text1 == null ? null : text1.trim();
    }

    /**
     * 
     * @return text2 
     */
    public String getText2() {
        return text2;
    }

    /**
     * 
     * @param text2 
     */
    public void setText2(String text2) {
        this.text2 = text2 == null ? null : text2.trim();
    }

    /**
     * 
     * @return text3 
     */
    public String getText3() {
        return text3;
    }

    /**
     * 
     * @param text3 
     */
    public void setText3(String text3) {
        this.text3 = text3 == null ? null : text3.trim();
    }

    /**
     * 
     * @return text4 
     */
    public String getText4() {
        return text4;
    }

    /**
     * 
     * @param text4 
     */
    public void setText4(String text4) {
        this.text4 = text4 == null ? null : text4.trim();
    }

    /**
     * 
     * @return text5 
     */
    public String getText5() {
        return text5;
    }

    /**
     * 
     * @param text5 
     */
    public void setText5(String text5) {
        this.text5 = text5 == null ? null : text5.trim();
    }
}