package com.velvol.salary.domain;

import java.io.Serializable;

public class SalaryUserInfo  extends BaseModel {
    /**
     * 
     */
    private Integer id;

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
    private String attendActual;

    /**
     * 迟到天数
     */
    private String belate;

    /**
     * 旷工
     */
    private String absent;

    /**
     * 公休
     */
    private String rest;

    /**
     * 事假
     */
    private String leave;

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

    /**
     * salary_user_info
     */
    private static final long serialVersionUID = 1L;

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

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 骑手id
     * @return record_id 骑手id
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * 骑手id
     * @param recordId 骑手id
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /**
     * 
     * @return rider_id 
     */
    public String getRiderId() {
        return riderId;
    }

    /**
     * 
     * @param riderId 
     */
    public void setRiderId(String riderId) {
        this.riderId = riderId == null ? null : riderId.trim();
    }

    /**
     * 入职时间
     * @return entry_time 入职时间
     */
    public String getEntryTime() {
        return entryTime;
    }

    /**
     * 入职时间
     * @param entryTime 入职时间
     */
    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime == null ? null : entryTime.trim();
    }

    /**
     * 离职时间
     * @return resign_time 离职时间
     */
    public String getResignTime() {
        return resignTime;
    }

    /**
     * 离职时间
     * @param resignTime 离职时间
     */
    public void setResignTime(String resignTime) {
        this.resignTime = resignTime == null ? null : resignTime.trim();
    }

    public String getAttendActual() {
        return attendActual;
    }

    public void setAttendActual(String attendActual) {
        this.attendActual = attendActual;
    }

    public String getBelate() {
        return belate;
    }

    public void setBelate(String belate) {
        this.belate = belate;
    }

    public String getAbsent() {
        return absent;
    }

    public void setAbsent(String absent) {
        this.absent = absent;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    /**
     * 车辆使用情况 0自备 1使用公司车辆
     * @return vehicle 车辆使用情况 0自备 1使用公司车辆
     */
    public Integer getVehicle() {
        return vehicle;
    }

    /**
     * 车辆使用情况 0自备 1使用公司车辆
     * @param vehicle 车辆使用情况 0自备 1使用公司车辆
     */
    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * 是否住宿 0 未住宿 1已住宿
     * @return live 是否住宿 0 未住宿 1已住宿
     */
    public Integer getLive() {
        return live;
    }

    /**
     * 是否住宿 0 未住宿 1已住宿
     * @param live 是否住宿 0 未住宿 1已住宿
     */
    public void setLive(Integer live) {
        this.live = live;
    }




}