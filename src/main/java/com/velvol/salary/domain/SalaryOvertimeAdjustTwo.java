package com.velvol.salary.domain;

/**
 * Created by Administrator on 2018/4/27.
 */
public class SalaryOvertimeAdjustTwo extends BaseModel {

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

    private Integer overtimeAdjustNumber; //普通超时容忍单量

    public Integer getOvertimeAdjustNumber() {
        return overtimeAdjustNumber;
    }

    public void setOvertimeAdjustNumber(Integer overtimeAdjustNumber) {
        this.overtimeAdjustNumber = overtimeAdjustNumber;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
