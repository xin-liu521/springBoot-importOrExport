package com.velvol.salary.domain;

/**
 * Created by Administrator on 2018/4/27.
 */
public class SalaryUserInfoTwo extends BaseModel{

    /**
     * 骑手id
     */
    private Integer recordId;

    /**
     *
     */
    private String riderId;

    private String riderName;//骑手姓名

    private String stataionId;

    private String stationName;

    /**
     * 入职时间
     */
    private String entryTime;

    /**
     * 离职时间
     */
    private String resignTime;

    private Integer isNewRider;


    /**
     * 实际出勤天数
     */
    private Integer attendActual;

    /**
     * 迟到天数
     */
    private Integer belate;

    /**
     * 旷工
     */
    private Integer absent;

    /**
     * 公休
     */
    private Integer rest;

    /**
     * 事假
     */
    private Integer leave;

    /**
     * 车辆使用情况 0自备 1使用公司车辆
     */
    private Integer vehicle;

    /**
     * 是否住宿 0 未住宿 1已住宿
     */
    private Integer live;

    private Integer subsidyPrice;       //岗位补贴/培训补贴

    private Integer smileActionPrice;  //微笑行动不达标扣款

    private Integer temperaturePrice;   //高温补贴

    private Integer equipPrice;//物料扣款

    private String remark;

    public Integer getIsNewRider() {
        return isNewRider;
    }

    public void setIsNewRider(Integer isNewRider) {
        this.isNewRider = isNewRider;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Integer getEquipPrice() {
        return equipPrice;
    }

    public void setEquipPrice(Integer equipPrice) {
        this.equipPrice = equipPrice;
    }

    public Integer getSubsidyPrice() {
        return subsidyPrice;
    }

    public void setSubsidyPrice(Integer subsidyPrice) {
        this.subsidyPrice = subsidyPrice;
    }

    public Integer getSmileActionPrice() {
        return smileActionPrice;
    }

    public void setSmileActionPrice(Integer smileActionPrice) {
        this.smileActionPrice = smileActionPrice;
    }

    public Integer getTemperaturePrice() {
        return temperaturePrice;
    }

    public void setTemperaturePrice(Integer temperaturePrice) {
        this.temperaturePrice = temperaturePrice;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getResignTime() {
        return resignTime;
    }

    public void setResignTime(String resignTime) {
        this.resignTime = resignTime;
    }

    public Integer getAttendActual() {
        return attendActual;
    }

    public void setAttendActual(Integer attendActual) {
        this.attendActual = attendActual;
    }

    public Integer getBelate() {
        return belate;
    }

    public void setBelate(Integer belate) {
        this.belate = belate;
    }

    public Integer getAbsent() {
        return absent;
    }

    public void setAbsent(Integer absent) {
        this.absent = absent;
    }

    public Integer getRest() {
        return rest;
    }

    public void setRest(Integer rest) {
        this.rest = rest;
    }

    public Integer getLeave() {
        return leave;
    }

    public void setLeave(Integer leave) {
        this.leave = leave;
    }

    public Integer getVehicle() {
        return vehicle;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getLive() {
        return live;
    }

    public void setLive(Integer live) {
        this.live = live;
    }



    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getStataionId() {
        return stataionId;
    }

    public void setStataionId(String stataionId) {
        this.stataionId = stataionId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
