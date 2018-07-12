package com.velvol.salary.domain;


import org.omg.CORBA.PRIVATE_MEMBER;

public class SalaryResultDetail extends BaseModel {
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

    private String riderName;//骑手姓名

    private String stataionId;

    private String stationName;

    /**
     * 实际订单数量
     */
    private Integer totalNumber;

    /**
     * 系统统计数量
     */
    private Integer systemNumber;

    /**
     * 调整单量
     */
    private Integer adjustNumber;

    /**
     * 夜间单量
     */
    private Integer nightNumber;

    /**
     * 大夜单量
     */
    private Integer bigNightNumber;

    /**
     * 100-200元区间单量
     */
    private Integer oneTwoNumber;

    /**
     * 大于200单量
     */
    private Integer greaterTowNumber;

    /**
     * 大于三公里订单数量
     */
    private Integer distanceNumber;

    /**
     * 普通超时订单数量
     */
    private Integer commonOvertimeNumber;

    /**
     * 容忍后普通超时单量
     */
    private Integer adjustOvertimeNumber;

    /**
     * 严重超时订单数量
     */
    private Integer seriousOvertimeNumber;

    /**
     * 不满意单数量
     */
    private Integer discontentNumber;

    /**
     * 底薪
     */
    private Integer basePrice;

    /**
     * 超出任务单量基本金额
     */
    private Integer overTaskPrice;

    /**
     * 单量阶梯提成金额
     */
    private Integer intervalPrice;

    /**
     * 夜间单提成金额
     */
    private Integer nightPrice;

    /**
     * 大夜单提成金额
     */
    private Integer bigNightPrice;

    /**
     * 订单金额100-200元区间的提成
     */
    private Integer oneTwoPrice;

    /**
     * 订单金额大于200的提成
     */
    private Integer greaterTwoPrice;

    /**
     * 大于3公里订单提成
     */
    private Integer distancePrice;

    /**
     * 季度奖金
     */
    private Integer seasonPrice;

    /**
     * 车辆补助
     */
    private Integer vehiclePrice;

    /**
     * 餐补
     */
    private Integer mealPrice;

    /**
     * 话补
     */
    private Integer telephonePrice;

    /**
     * 住宿补助
     */
    private Integer livePrice;

    /**
     * 全勤补助
     */
    private Integer presentPrice;

    /**
     * 考核不达标扣款
     */
    private Integer noStandardPrice;

    private Integer discontent_price;//不满意单扣款

    /**
     * 普通超时扣款
     */
    private Integer cmmonOvertimePrice;

    /**
     * 严重超时扣款
     */
    private Integer seriousOvertimePrice;

    /**
     * 迟到扣款
     */
    private Integer belatePrice;

    /**
     * 旷工扣款
     */
    private Integer absentPrice;


    /**
     * 工资总额
     */
    private Integer amountPrice;

    private Integer beyondTaskNumber;//超出单数

    private Integer noTaskBasePrice;//<420单结算（5元）

    private Integer lastmonthPrice;//补上月工资

    private Integer trainPrice;// 培训费

    private Integer insurance;//保险费

    private Integer equipUsePrice;//物料使用费

    private Integer equipBuyPrice;//购买物料费用

    private Integer equipPrice;//物料扣款

    private Integer status;//状态 0 发放 1暂扣 2不发放

    private Integer vehicleDeductionPrice;//使用公司扣款

    private Integer noServicePrice;   //未送达点击罚款

    private Integer complainPrice;    //投诉单罚款

    private Integer classIiComplainPrice;     //二类投诉罚款

    /**
     * salary_result_detail
     */
    private static final long serialVersionUID = 1L;


    public Integer getNoServicePrice() {
        return noServicePrice;
    }

    public void setNoServicePrice(Integer noServicePrice) {
        this.noServicePrice = noServicePrice;
    }

    public Integer getComplainPrice() {
        return complainPrice;
    }

    public void setComplainPrice(Integer complainPrice) {
        this.complainPrice = complainPrice;
    }

    public Integer getClassIiComplainPrice() {
        return classIiComplainPrice;
    }

    public void setClassIiComplainPrice(Integer classIiComplainPrice) {
        this.classIiComplainPrice = classIiComplainPrice;
    }

    public Integer getBeyondTaskNumber() {
        return beyondTaskNumber;
    }

    public void setBeyondTaskNumber(Integer beyondTaskNumber) {
        this.beyondTaskNumber = beyondTaskNumber;
    }

    public Integer getNoTaskBasePrice() {
        return noTaskBasePrice;
    }

    public void setNoTaskBasePrice(Integer noTaskBasePrice) {
        this.noTaskBasePrice = noTaskBasePrice;
    }

    public Integer getLastmonthPrice() {
        return lastmonthPrice;
    }

    public void setLastmonthPrice(Integer lastmonthPrice) {
        this.lastmonthPrice = lastmonthPrice;
    }

    public Integer getTrainPrice() {
        return trainPrice;
    }

    public void setTrainPrice(Integer trainPrice) {
        this.trainPrice = trainPrice;
    }

    public Integer getInsurance() {
        return insurance;
    }

    public void setInsurance(Integer insurance) {
        this.insurance = insurance;
    }

    public Integer getEquipUsePrice() {
        return equipUsePrice;
    }

    public void setEquipUsePrice(Integer equipUsePrice) {
        this.equipUsePrice = equipUsePrice;
    }

    public Integer getEquipBuyPrice() {
        return equipBuyPrice;
    }

    public void setEquipBuyPrice(Integer equipBuyPrice) {
        this.equipBuyPrice = equipBuyPrice;
    }

    public Integer getEquipPrice() {
        return equipPrice;
    }

    public void setEquipPrice(Integer equipPrice) {
        this.equipPrice = equipPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDiscontent_price() {
        return discontent_price;
    }

    public void setDiscontent_price(Integer discontent_price) {
        this.discontent_price = discontent_price;
    }

    public Integer getVehicleDeductionPrice() {
        return vehicleDeductionPrice;
    }

    public void setVehicleDeductionPrice(Integer vehicleDeductionPrice) {
        this.vehicleDeductionPrice = vehicleDeductionPrice;
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
     * 实际订单数量
     * @return total_number 实际订单数量
     */
    public Integer getTotalNumber() {
        return totalNumber;
    }

    /**
     * 实际订单数量
     * @param totalNumber 实际订单数量
     */
    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    /**
     * 系统统计数量
     * @return system_number 系统统计数量
     */
    public Integer getSystemNumber() {
        return systemNumber;
    }

    /**
     * 系统统计数量
     * @param systemNumber 系统统计数量
     */
    public void setSystemNumber(Integer systemNumber) {
        this.systemNumber = systemNumber;
    }

    /**
     * 调整单量
     * @return adjust_number 调整单量
     */
    public Integer getAdjustNumber() {
        return adjustNumber;
    }

    /**
     * 调整单量
     * @param adjustNumber 调整单量
     */
    public void setAdjustNumber(Integer adjustNumber) {
        this.adjustNumber = adjustNumber;
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
     * 100-200元区间单量
     * @return one_two_number 100-200元区间单量
     */
    public Integer getOneTwoNumber() {
        return oneTwoNumber;
    }

    /**
     * 100-200元区间单量
     * @param oneTwoNumber 100-200元区间单量
     */
    public void setOneTwoNumber(Integer oneTwoNumber) {
        this.oneTwoNumber = oneTwoNumber;
    }

    /**
     * 大于200单量
     * @return greater_tow_number 大于200单量
     */
    public Integer getGreaterTowNumber() {
        return greaterTowNumber;
    }

    /**
     * 大于200单量
     * @param greaterTowNumber 大于200单量
     */
    public void setGreaterTowNumber(Integer greaterTowNumber) {
        this.greaterTowNumber = greaterTowNumber;
    }

    /**
     * 大于三公里订单数量
     * @return distance_number 大于三公里订单数量
     */
    public Integer getDistanceNumber() {
        return distanceNumber;
    }

    /**
     * 大于三公里订单数量
     * @param distanceNumber 大于三公里订单数量
     */
    public void setDistanceNumber(Integer distanceNumber) {
        this.distanceNumber = distanceNumber;
    }

    /**
     * 普通超时订单数量
     * @return common_overtime_number 普通超时订单数量
     */
    public Integer getCommonOvertimeNumber() {
        return commonOvertimeNumber;
    }

    /**
     * 普通超时订单数量
     * @param commonOvertimeNumber 普通超时订单数量
     */
    public void setCommonOvertimeNumber(Integer commonOvertimeNumber) {
        this.commonOvertimeNumber = commonOvertimeNumber;
    }

    /**
     * 容忍后普通超时单量
     * @return adjust_overtime_number 容忍后普通超时单量
     */
    public Integer getAdjustOvertimeNumber() {
        return adjustOvertimeNumber;
    }

    /**
     * 容忍后普通超时单量
     * @param adjustOvertimeNumber 容忍后普通超时单量
     */
    public void setAdjustOvertimeNumber(Integer adjustOvertimeNumber) {
        this.adjustOvertimeNumber = adjustOvertimeNumber;
    }

    /**
     * 严重超时订单数量
     * @return serious_overtime_number 严重超时订单数量
     */
    public Integer getSeriousOvertimeNumber() {
        return seriousOvertimeNumber;
    }

    /**
     * 严重超时订单数量
     * @param seriousOvertimeNumber 严重超时订单数量
     */
    public void setSeriousOvertimeNumber(Integer seriousOvertimeNumber) {
        this.seriousOvertimeNumber = seriousOvertimeNumber;
    }

    /**
     * 不满意单数量
     * @return discontent_number 不满意单数量
     */
    public Integer getDiscontentNumber() {
        return discontentNumber;
    }

    /**
     * 不满意单数量
     * @param discontentNumber 不满意单数量
     */
    public void setDiscontentNumber(Integer discontentNumber) {
        this.discontentNumber = discontentNumber;
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
     * 超出任务单量基本金额
     * @return over_task_price 超出任务单量基本金额
     */
    public Integer getOverTaskPrice() {
        return overTaskPrice;
    }

    /**
     * 超出任务单量基本金额
     * @param overTaskPrice 超出任务单量基本金额
     */
    public void setOverTaskPrice(Integer overTaskPrice) {
        this.overTaskPrice = overTaskPrice;
    }

    /**
     * 单量阶梯提成金额
     * @return interval_price 单量阶梯提成金额
     */
    public Integer getIntervalPrice() {
        return intervalPrice;
    }

    /**
     * 单量阶梯提成金额
     * @param intervalPrice 单量阶梯提成金额
     */
    public void setIntervalPrice(Integer intervalPrice) {
        this.intervalPrice = intervalPrice;
    }

    /**
     * 夜间单提成金额
     * @return night_price 夜间单提成金额
     */
    public Integer getNightPrice() {
        return nightPrice;
    }

    /**
     * 夜间单提成金额
     * @param nightPrice 夜间单提成金额
     */
    public void setNightPrice(Integer nightPrice) {
        this.nightPrice = nightPrice;
    }

    /**
     * 大夜单提成金额
     * @return big_night_price 大夜单提成金额
     */
    public Integer getBigNightPrice() {
        return bigNightPrice;
    }

    /**
     * 大夜单提成金额
     * @param bigNightPrice 大夜单提成金额
     */
    public void setBigNightPrice(Integer bigNightPrice) {
        this.bigNightPrice = bigNightPrice;
    }

    /**
     * 订单金额100-200元区间的提成
     * @return one_two_price 订单金额100-200元区间的提成
     */
    public Integer getOneTwoPrice() {
        return oneTwoPrice;
    }

    /**
     * 订单金额100-200元区间的提成
     * @param oneTwoPrice 订单金额100-200元区间的提成
     */
    public void setOneTwoPrice(Integer oneTwoPrice) {
        this.oneTwoPrice = oneTwoPrice;
    }

    /**
     * 订单金额大于200的提成
     * @return greater_two_price 订单金额大于200的提成
     */
    public Integer getGreaterTwoPrice() {
        return greaterTwoPrice;
    }

    /**
     * 订单金额大于200的提成
     * @param greaterTwoPrice 订单金额大于200的提成
     */
    public void setGreaterTwoPrice(Integer greaterTwoPrice) {
        this.greaterTwoPrice = greaterTwoPrice;
    }

    /**
     * 大于3公里订单提成
     * @return distance_price 大于3公里订单提成
     */
    public Integer getDistancePrice() {
        return distancePrice;
    }

    /**
     * 大于3公里订单提成
     * @param distancePrice 大于3公里订单提成
     */
    public void setDistancePrice(Integer distancePrice) {
        this.distancePrice = distancePrice;
    }

    /**
     * 季度奖金
     * @return season_price 季度奖金
     */
    public Integer getSeasonPrice() {
        return seasonPrice;
    }

    /**
     * 季度奖金
     * @param seasonPrice 季度奖金
     */
    public void setSeasonPrice(Integer seasonPrice) {
        this.seasonPrice = seasonPrice;
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
     * 全勤补助
     * @return present_price 全勤补助
     */
    public Integer getPresentPrice() {
        return presentPrice;
    }

    /**
     * 全勤补助
     * @param presentPrice 全勤补助
     */
    public void setPresentPrice(Integer presentPrice) {
        this.presentPrice = presentPrice;
    }

    /**
     * 考核不达标扣款
     * @return no_standard_price 考核不达标扣款
     */
    public Integer getNoStandardPrice() {
        return noStandardPrice;
    }

    /**
     * 考核不达标扣款
     * @param noStandardPrice 考核不达标扣款
     */
    public void setNoStandardPrice(Integer noStandardPrice) {
        this.noStandardPrice = noStandardPrice;
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