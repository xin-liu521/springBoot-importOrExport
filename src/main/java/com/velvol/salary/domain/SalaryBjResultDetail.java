package com.velvol.salary.domain;

import java.io.Serializable;

public class SalaryBjResultDetail extends BaseModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 工资计算记录表id
     */
    private Integer recordId;

    /**
     * 骑手id
     */
    private String riderId;

    /**
     * 
     */
    private String riderName;

    /**
     * 
     */
    private String stataionId;

    /**
     * 
     */
    private String stationName;

    /**
     * 内单量
     */
    private Integer insideNumber;

    /**
     * 底薪
     */
    private Integer basePrice;

    /**
     * 小于等于400单薪资
     */
    private Integer lessTaskPrice;

    /**
     * 充电补助
     */
    private Integer chargePrice;

    /**
     * 住宿补助
     */
    private Integer livePrice;

    /**
     * 话补
     */
    private Integer telephonePrice;

    /**
     * 餐补
     */
    private Integer mealPrice;

    /**
     * 车辆补助
     */
    private Integer vehiclePrice;


    /**
     * 车辆使用费
     */
    private Integer vehicleDeductionPrice;

    /**
     * 数据考核扣款
     */
    private Integer noStandardPrice;

    /**
     * 夜间单量
     */
    private Integer nightNumber;

    /**
     * 夜间补贴
     */
    private Integer nightPrice;

    /**
     * 大夜单量
     */
    private Integer bigNightNumber;

    /**
     * 大夜补贴
     */
    private Integer bigNightPrice;

    /**
     * 阶梯提成
     */
    private Integer intervalPrice;

    /**
     * 大于等于80小于200单量
     */
    private Integer intervalOneNumber;

    /**
     * 奖励
     */
    private Integer intervalOnePrice;

    /**
     * 大于等于90小于200单量（新骑手）
     */
    private Integer intervalTwoNumber;

    /**
     * 奖励
     */
    private Integer intervalTwoPrice;

    /**
     * 大于等于200小于400
     */
    private Integer intervalThreeNumber;

    /**
     * 奖励
     */
    private Integer intervalThreePrice;

    /**
     * 大于等于400小于700
     */
    private Integer intervalFourNumber;

    /**
     * 奖励
     */
    private Integer intervalFourPrice;

    /**
     * 大于等于700
     */
    private Integer intervalFiveNumber;

    /**
     * 奖励
     */
    private Integer intervalFivePrice;

    /**
     * 3-4KM单量
     */
    private Integer distanceOneNumber;

    /**
     * 奖励
     */
    private Integer diatanceOnePrice;

    /**
     * 4-5KM单量
     */
    private Integer distanceTwoNumber;

    /**
     * 奖励
     */
    private Integer distanceTwoPrice;

    /**
     * 物料款
     */
    private Integer equipPrice;

    /**
     * 商业险
     */
    private Integer insurancePrice;

    /**
     * 外单量
     */
    private Integer outsideNumber;

    /**
     * 提成
     */
    private Integer outsidePrice;

    /**
     * 外单距离补助
     */
    private Integer outsideDistancePrice;

    /**
     * 夜宵
     */
    private Integer outsideNightPrice;

    /**
     * 午高峰
     */
    private Integer outsideNoonPrice;

    /**
     * 普通超时扣款
     */
    private Integer cmmonOvertimePrice;

    /**
     * 严重超时扣款
     */
    private Integer seriousOvertimePrice;

    /**
     * 未送达点送达罚款
     */
    private Integer noServicePrice;

    /**
     * 一星罚款
     */
    private Integer oneStarPrice;

    /**
     * 二星罚款
     */
    private Integer twoStarPrice;

    /**
     * 投诉单罚款
     */
    private Integer complainPrice;

    /**
     * 二类投诉
     */
    private Integer classIiComplain;

    /**
     * 迟到扣款
     */
    private Integer belatePrice;

    /**
     * 旷工扣款
     */
    private Integer absentPrice;

    private Integer personalTax;

    /**
     * 工资总额
     */
    private Integer amountPrice;

    /**
     * 状态 0 发放 1暂扣 2不发放
     */
    private Integer status;

    /**
     * salary_bj_result_detail
     */
    private static final long serialVersionUID = 1L;


    public Integer getPersonalTax() {
        return personalTax;
    }

    public void setPersonalTax(Integer personalTax) {
        this.personalTax = personalTax;
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
     * 内单量
     * @return inside_number 内单量
     */
    public Integer getInsideNumber() {
        return insideNumber;
    }

    /**
     * 内单量
     * @param insideNumber 内单量
     */
    public void setInsideNumber(Integer insideNumber) {
        this.insideNumber = insideNumber;
    }

    /**
     * 底薪
     * @return base_price 底薪
     */
    public Integer getBasePrice() {
        return basePrice;
    }

    /**
     * 底薪
     * @param basePrice 底薪
     */
    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * 小于等于400单薪资
     * @return less_task_price 小于等于400单薪资
     */
    public Integer getLessTaskPrice() {
        return lessTaskPrice;
    }

    /**
     * 小于等于400单薪资
     * @param lessTaskPrice 小于等于400单薪资
     */
    public void setLessTaskPrice(Integer lessTaskPrice) {
        this.lessTaskPrice = lessTaskPrice;
    }

    /**
     * 充电补助
     * @return charge_price 充电补助
     */
    public Integer getChargePrice() {
        return chargePrice;
    }

    /**
     * 充电补助
     * @param chargePrice 充电补助
     */
    public void setChargePrice(Integer chargePrice) {
        this.chargePrice = chargePrice;
    }

    /**
     * 住宿补助
     * @return live_price 住宿补助
     */
    public Integer getLivePrice() {
        return livePrice;
    }

    /**
     * 住宿补助
     * @param livePrice 住宿补助
     */
    public void setLivePrice(Integer livePrice) {
        this.livePrice = livePrice;
    }

    /**
     * 话补
     * @return telephone_price 话补
     */
    public Integer getTelephonePrice() {
        return telephonePrice;
    }

    /**
     * 话补
     * @param telephonePrice 话补
     */
    public void setTelephonePrice(Integer telephonePrice) {
        this.telephonePrice = telephonePrice;
    }

    /**
     * 餐补
     * @return meal_price 餐补
     */
    public Integer getMealPrice() {
        return mealPrice;
    }

    /**
     * 餐补
     * @param mealPrice 餐补
     */
    public void setMealPrice(Integer mealPrice) {
        this.mealPrice = mealPrice;
    }

    /**
     * 车辆补助
     * @return vehicle_price 车辆补助
     */
    public Integer getVehiclePrice() {
        return vehiclePrice;
    }

    /**
     * 车辆补助
     * @param vehiclePrice 车辆补助
     */
    public void setVehiclePrice(Integer vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }



    /**
     * 车辆使用费
     * @return vehicle_deduction_price 车辆使用费
     */
    public Integer getVehicleDeductionPrice() {
        return vehicleDeductionPrice;
    }

    /**
     * 车辆使用费
     * @param vehicleDeductionPrice 车辆使用费
     */
    public void setVehicleDeductionPrice(Integer vehicleDeductionPrice) {
        this.vehicleDeductionPrice = vehicleDeductionPrice;
    }

    /**
     * 夜间单量
     * @return no_standard_price 夜间单量
     */
    public Integer getNoStandardPrice() {
        return noStandardPrice;
    }

    /**
     * 夜间单量
     * @param noStandardPrice 夜间单量
     */
    public void setNoStandardPrice(Integer noStandardPrice) {
        this.noStandardPrice = noStandardPrice;
    }

    /**
     * 夜间单量
     * @return night_number 夜间单量
     */
    public Integer getNightNumber() {
        return nightNumber;
    }

    /**
     * 夜间单量
     * @param nightNumber 夜间单量
     */
    public void setNightNumber(Integer nightNumber) {
        this.nightNumber = nightNumber;
    }

    /**
     * 夜间补贴
     * @return night_price 夜间补贴
     */
    public Integer getNightPrice() {
        return nightPrice;
    }

    /**
     * 夜间补贴
     * @param nightPrice 夜间补贴
     */
    public void setNightPrice(Integer nightPrice) {
        this.nightPrice = nightPrice;
    }

    /**
     * 大夜单量
     * @return big_night_number 大夜单量
     */
    public Integer getBigNightNumber() {
        return bigNightNumber;
    }

    /**
     * 大夜单量
     * @param bigNightNumber 大夜单量
     */
    public void setBigNightNumber(Integer bigNightNumber) {
        this.bigNightNumber = bigNightNumber;
    }

    /**
     * 大夜补贴
     * @return big_night_price 大夜补贴
     */
    public Integer getBigNightPrice() {
        return bigNightPrice;
    }

    /**
     * 大夜补贴
     * @param bigNightPrice 大夜补贴
     */
    public void setBigNightPrice(Integer bigNightPrice) {
        this.bigNightPrice = bigNightPrice;
    }

    /**
     * 阶梯提成
     * @return interval_price 阶梯提成
     */
    public Integer getIntervalPrice() {
        return intervalPrice;
    }

    /**
     * 阶梯提成
     * @param intervalPrice 阶梯提成
     */
    public void setIntervalPrice(Integer intervalPrice) {
        this.intervalPrice = intervalPrice;
    }

    /**
     * 大于等于80小于200单量
     * @return interval_one_number 大于等于80小于200单量
     */
    public Integer getIntervalOneNumber() {
        return intervalOneNumber;
    }

    /**
     * 大于等于80小于200单量
     * @param intervalOneNumber 大于等于80小于200单量
     */
    public void setIntervalOneNumber(Integer intervalOneNumber) {
        this.intervalOneNumber = intervalOneNumber;
    }

    /**
     * 奖励
     * @return interval_one_price 奖励
     */
    public Integer getIntervalOnePrice() {
        return intervalOnePrice;
    }

    /**
     * 奖励
     * @param intervalOnePrice 奖励
     */
    public void setIntervalOnePrice(Integer intervalOnePrice) {
        this.intervalOnePrice = intervalOnePrice;
    }

    /**
     * 大于等于90小于200单量
     * @return interval_two_number 大于等于90小于200单量
     */
    public Integer getIntervalTwoNumber() {
        return intervalTwoNumber;
    }

    /**
     * 大于等于90小于200单量
     * @param intervalTwoNumber 大于等于90小于200单量
     */
    public void setIntervalTwoNumber(Integer intervalTwoNumber) {
        this.intervalTwoNumber = intervalTwoNumber;
    }

    /**
     * 奖励
     * @return interval_two_price 奖励
     */
    public Integer getIntervalTwoPrice() {
        return intervalTwoPrice;
    }

    /**
     * 奖励
     * @param intervalTwoPrice 奖励
     */
    public void setIntervalTwoPrice(Integer intervalTwoPrice) {
        this.intervalTwoPrice = intervalTwoPrice;
    }

    /**
     * 大于等于200小于400
     * @return interval_three_number 大于等于200小于400
     */
    public Integer getIntervalThreeNumber() {
        return intervalThreeNumber;
    }

    /**
     * 大于等于200小于400
     * @param intervalThreeNumber 大于等于200小于400
     */
    public void setIntervalThreeNumber(Integer intervalThreeNumber) {
        this.intervalThreeNumber = intervalThreeNumber;
    }

    /**
     * 奖励
     * @return interval_three_price 奖励
     */
    public Integer getIntervalThreePrice() {
        return intervalThreePrice;
    }

    /**
     * 奖励
     * @param intervalThreePrice 奖励
     */
    public void setIntervalThreePrice(Integer intervalThreePrice) {
        this.intervalThreePrice = intervalThreePrice;
    }

    /**
     * 大于等于400小于700
     * @return interval_four_number 大于等于400小于700
     */
    public Integer getIntervalFourNumber() {
        return intervalFourNumber;
    }

    /**
     * 大于等于400小于700
     * @param intervalFourNumber 大于等于400小于700
     */
    public void setIntervalFourNumber(Integer intervalFourNumber) {
        this.intervalFourNumber = intervalFourNumber;
    }

    /**
     * 奖励
     * @return interval_four_price 奖励
     */
    public Integer getIntervalFourPrice() {
        return intervalFourPrice;
    }

    /**
     * 奖励
     * @param intervalFourPrice 奖励
     */
    public void setIntervalFourPrice(Integer intervalFourPrice) {
        this.intervalFourPrice = intervalFourPrice;
    }

    /**
     * 大于等于700
     * @return interval_five_number 大于等于700
     */
    public Integer getIntervalFiveNumber() {
        return intervalFiveNumber;
    }

    /**
     * 大于等于700
     * @param intervalFiveNumber 大于等于700
     */
    public void setIntervalFiveNumber(Integer intervalFiveNumber) {
        this.intervalFiveNumber = intervalFiveNumber;
    }

    /**
     * 奖励
     * @return interval_five_price 奖励
     */
    public Integer getIntervalFivePrice() {
        return intervalFivePrice;
    }

    /**
     * 奖励
     * @param intervalFivePrice 奖励
     */
    public void setIntervalFivePrice(Integer intervalFivePrice) {
        this.intervalFivePrice = intervalFivePrice;
    }

    /**
     * 3-4KM单量
     * @return distance_one_number 3-4KM单量
     */
    public Integer getDistanceOneNumber() {
        return distanceOneNumber;
    }

    /**
     * 3-4KM单量
     * @param distanceOneNumber 3-4KM单量
     */
    public void setDistanceOneNumber(Integer distanceOneNumber) {
        this.distanceOneNumber = distanceOneNumber;
    }

    /**
     * 奖励
     * @return diatance_one_price 奖励
     */
    public Integer getDiatanceOnePrice() {
        return diatanceOnePrice;
    }

    /**
     * 奖励
     * @param diatanceOnePrice 奖励
     */
    public void setDiatanceOnePrice(Integer diatanceOnePrice) {
        this.diatanceOnePrice = diatanceOnePrice;
    }

    /**
     * 4-5KM单量
     * @return distance_two_number 4-5KM单量
     */
    public Integer getDistanceTwoNumber() {
        return distanceTwoNumber;
    }

    /**
     * 4-5KM单量
     * @param distanceTwoNumber 4-5KM单量
     */
    public void setDistanceTwoNumber(Integer distanceTwoNumber) {
        this.distanceTwoNumber = distanceTwoNumber;
    }

    /**
     * 奖励
     * @return distance_two_price 奖励
     */
    public Integer getDistanceTwoPrice() {
        return distanceTwoPrice;
    }

    /**
     * 奖励
     * @param distanceTwoPrice 奖励
     */
    public void setDistanceTwoPrice(Integer distanceTwoPrice) {
        this.distanceTwoPrice = distanceTwoPrice;
    }

    /**
     * 物料款
     * @return equip_price 物料款
     */
    public Integer getEquipPrice() {
        return equipPrice;
    }

    /**
     * 物料款
     * @param equipPrice 物料款
     */
    public void setEquipPrice(Integer equipPrice) {
        this.equipPrice = equipPrice;
    }

    /**
     * 商业险
     * @return insurance_price 商业险
     */
    public Integer getInsurancePrice() {
        return insurancePrice;
    }

    /**
     * 商业险
     * @param insurancePrice 商业险
     */
    public void setInsurancePrice(Integer insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    /**
     * 外单量
     * @return outside_number 外单量
     */
    public Integer getOutsideNumber() {
        return outsideNumber;
    }

    /**
     * 外单量
     * @param outsideNumber 外单量
     */
    public void setOutsideNumber(Integer outsideNumber) {
        this.outsideNumber = outsideNumber;
    }

    /**
     * 提成
     * @return outside_price 提成
     */
    public Integer getOutsidePrice() {
        return outsidePrice;
    }

    /**
     * 提成
     * @param outsidePrice 提成
     */
    public void setOutsidePrice(Integer outsidePrice) {
        this.outsidePrice = outsidePrice;
    }

    /**
     * 外单距离补助
     * @return outside_distance_price 外单距离补助
     */
    public Integer getOutsideDistancePrice() {
        return outsideDistancePrice;
    }

    /**
     * 外单距离补助
     * @param outsideDistancePrice 外单距离补助
     */
    public void setOutsideDistancePrice(Integer outsideDistancePrice) {
        this.outsideDistancePrice = outsideDistancePrice;
    }

    /**
     * 夜宵
     * @return outside_night_price 夜宵
     */
    public Integer getOutsideNightPrice() {
        return outsideNightPrice;
    }

    /**
     * 夜宵
     * @param outsideNightPrice 夜宵
     */
    public void setOutsideNightPrice(Integer outsideNightPrice) {
        this.outsideNightPrice = outsideNightPrice;
    }

    /**
     * 午高峰
     * @return outside_noon_price 午高峰
     */
    public Integer getOutsideNoonPrice() {
        return outsideNoonPrice;
    }

    /**
     * 午高峰
     * @param outsideNoonPrice 午高峰
     */
    public void setOutsideNoonPrice(Integer outsideNoonPrice) {
        this.outsideNoonPrice = outsideNoonPrice;
    }

    /**
     * 普通超时扣款
     * @return cmmon_overtime_price 普通超时扣款
     */
    public Integer getCmmonOvertimePrice() {
        return cmmonOvertimePrice;
    }

    /**
     * 普通超时扣款
     * @param cmmonOvertimePrice 普通超时扣款
     */
    public void setCmmonOvertimePrice(Integer cmmonOvertimePrice) {
        this.cmmonOvertimePrice = cmmonOvertimePrice;
    }

    /**
     * 严重超时扣款
     * @return serious_overtime_price 严重超时扣款
     */
    public Integer getSeriousOvertimePrice() {
        return seriousOvertimePrice;
    }

    /**
     * 严重超时扣款
     * @param seriousOvertimePrice 严重超时扣款
     */
    public void setSeriousOvertimePrice(Integer seriousOvertimePrice) {
        this.seriousOvertimePrice = seriousOvertimePrice;
    }

    /**
     * 未送达点送达罚款
     * @return no_service_price 未送达点送达罚款
     */
    public Integer getNoServicePrice() {
        return noServicePrice;
    }

    /**
     * 未送达点送达罚款
     * @param noServicePrice 未送达点送达罚款
     */
    public void setNoServicePrice(Integer noServicePrice) {
        this.noServicePrice = noServicePrice;
    }

    /**
     * 一星罚款
     * @return one_star_price 一星罚款
     */
    public Integer getOneStarPrice() {
        return oneStarPrice;
    }

    /**
     * 一星罚款
     * @param oneStarPrice 一星罚款
     */
    public void setOneStarPrice(Integer oneStarPrice) {
        this.oneStarPrice = oneStarPrice;
    }

    /**
     * 二星罚款
     * @return two_star_price 二星罚款
     */
    public Integer getTwoStarPrice() {
        return twoStarPrice;
    }

    /**
     * 二星罚款
     * @param twoStarPrice 二星罚款
     */
    public void setTwoStarPrice(Integer twoStarPrice) {
        this.twoStarPrice = twoStarPrice;
    }

    /**
     * 投诉单罚款
     * @return complain_price 投诉单罚款
     */
    public Integer getComplainPrice() {
        return complainPrice;
    }

    /**
     * 投诉单罚款
     * @param complainPrice 投诉单罚款
     */
    public void setComplainPrice(Integer complainPrice) {
        this.complainPrice = complainPrice;
    }

    /**
     * 二类投诉
     * @return class_ii_complain 二类投诉
     */
    public Integer getClassIiComplain() {
        return classIiComplain;
    }

    /**
     * 二类投诉
     * @param classIiComplain 二类投诉
     */
    public void setClassIiComplain(Integer classIiComplain) {
        this.classIiComplain = classIiComplain;
    }

    /**
     * 迟到扣款
     * @return belate_price 迟到扣款
     */
    public Integer getBelatePrice() {
        return belatePrice;
    }

    /**
     * 迟到扣款
     * @param belatePrice 迟到扣款
     */
    public void setBelatePrice(Integer belatePrice) {
        this.belatePrice = belatePrice;
    }

    /**
     * 旷工扣款
     * @return absent_price 旷工扣款
     */
    public Integer getAbsentPrice() {
        return absentPrice;
    }

    /**
     * 旷工扣款
     * @param absentPrice 旷工扣款
     */
    public void setAbsentPrice(Integer absentPrice) {
        this.absentPrice = absentPrice;
    }

    /**
     * 工资总额
     * @return amount_price 工资总额
     */
    public Integer getAmountPrice() {
        return amountPrice;
    }

    /**
     * 工资总额
     * @param amountPrice 工资总额
     */
    public void setAmountPrice(Integer amountPrice) {
        this.amountPrice = amountPrice;
    }

    /**
     * 状态 0 发放 1暂扣 2不发放
     * @return status 状态 0 发放 1暂扣 2不发放
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态 0 发放 1暂扣 2不发放
     * @param status 状态 0 发放 1暂扣 2不发放
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}