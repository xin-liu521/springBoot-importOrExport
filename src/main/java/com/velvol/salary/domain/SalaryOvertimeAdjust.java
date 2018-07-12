package com.velvol.salary.domain;

import java.io.Serializable;

public class SalaryOvertimeAdjust  extends BaseModel {
    /**
     * id
     */
    private Integer id;

    /**
     * 工资计算记录表id
     */
    private Integer recordId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 骑手id
     */
    private String riderId;

    /**
     * 是否容忍 0 不容忍 1容忍
     */
    private Integer status;

    /**
     * 容忍原因
     */
    private String reason;

    /**
     * salary_overtime_adjust
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Integer id) {
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
     * 订单id
     * @return order_id 订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 订单id
     * @param orderId 订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 骑手id
     * @return rider_id 骑手id
     */
    public String getRiderId() {
        return riderId;
    }

    /**
     * 骑手id
     * @param riderId 骑手id
     */
    public void setRiderId(String riderId) {
        this.riderId = riderId == null ? null : riderId.trim();
    }

    /**
     * 是否容忍 0 不容忍 1容忍
     * @return status 是否容忍 0 不容忍 1容忍
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 是否容忍 0 不容忍 1容忍
     * @param status 是否容忍 0 不容忍 1容忍
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 容忍原因
     * @return reason 容忍原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 容忍原因
     * @param reason 容忍原因
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}