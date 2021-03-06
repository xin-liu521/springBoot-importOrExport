package com.velvol.salary.domain;


public class SalaryOrderAdjust  extends BaseModel {
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

    private Integer systemNumber;

    /**
     * 人效单量
     */
    private Integer amount;


    private Integer commonOvertimeNumber;

    /**
     * 普通超时容忍后单量
     */
    private Integer overtimeAdjustNumber;


    private Integer seriousOvertimeNumber;  //严重超时容忍后单量



    /**
     * @desc 不满意单量
     */
    private Integer discontentNumber;

    /**
     * 
     */
    private String onTimeProportion;

    /**
     * 完成率
     */
    private String completeProportion;

    /**
     * 满意率
     */
    private String pleasedProportion;

    private String deliverScore;  //配送得分

    private Integer noServiceNumber;

    private Integer oneStar;    //一星单

    private Integer twoStar;    //二星单

    private Integer complain;   //投诉单

    private Integer classIiComplain;   //二类投诉

    private Integer socialSecurity;   //社保扣款

    private Integer liveDeduct;   //住宿扣款

    private Integer introductionFeeDeduct; //介绍费扣款

    private Integer groupLeader;   //组长补贴

    private Integer royalKnight;   //皇家骑士

    private Integer fiveStarGeneral;   //五星上将


    public Integer getSeriousOvertimeNumber() {
        return seriousOvertimeNumber;
    }

    public void setSeriousOvertimeNumber(Integer seriousOvertimeNumber) {
        this.seriousOvertimeNumber = seriousOvertimeNumber;
    }

    public Integer getNoServiceNumber() {
        return noServiceNumber;
    }

    public void setNoServiceNumber(Integer noServiceNumber) {
        this.noServiceNumber = noServiceNumber;
    }

    public String getDeliverScore() {
        return deliverScore;
    }

    public void setDeliverScore(String deliverScore) {
        this.deliverScore = deliverScore;
    }

    public Integer getOneStar() {
        return oneStar;
    }

    public void setOneStar(Integer oneStar) {
        this.oneStar = oneStar;
    }

    public Integer getTwoStar() {
        return twoStar;
    }

    public void setTwoStar(Integer twoStar) {
        this.twoStar = twoStar;
    }

    public Integer getComplain() {
        return complain;
    }

    public void setComplain(Integer complain) {
        this.complain = complain;
    }

    public Integer getClassIiComplain() {
        return classIiComplain;
    }

    public void setClassIiComplain(Integer classIiComplain) {
        this.classIiComplain = classIiComplain;
    }

    public Integer getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(Integer socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Integer getLiveDeduct() {
        return liveDeduct;
    }

    public void setLiveDeduct(Integer liveDeduct) {
        this.liveDeduct = liveDeduct;
    }

    public Integer getIntroductionFeeDeduct() {
        return introductionFeeDeduct;
    }

    public void setIntroductionFeeDeduct(Integer introductionFeeDeduct) {
        this.introductionFeeDeduct = introductionFeeDeduct;
    }

    public Integer getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(Integer groupLeader) {
        this.groupLeader = groupLeader;
    }

    public Integer getRoyalKnight() {
        return royalKnight;
    }

    public void setRoyalKnight(Integer royalKnight) {
        this.royalKnight = royalKnight;
    }

    public Integer getFiveStarGeneral() {
        return fiveStarGeneral;
    }

    public void setFiveStarGeneral(Integer fiveStarGeneral) {
        this.fiveStarGeneral = fiveStarGeneral;
    }

    public Integer getDiscontentNumber() {
        return discontentNumber;
    }

    public void setDiscontentNumber(Integer discontentNumber) {
        this.discontentNumber = discontentNumber;
    }

    /**
     * salary_order_adjust
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
     * 人效单量
     * @return amount 人效单量
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 人效单量
     * @param amount 人效单量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 普通超时容忍单量
     * @return overtime_adjust_number 普通超时容忍单量
     */
    public Integer getOvertimeAdjustNumber() {
        return overtimeAdjustNumber;
    }

    /**
     * 普通超时容忍单量
     * @param overtimeAdjustNumber 普通超时容忍单量
     */
    public void setOvertimeAdjustNumber(Integer overtimeAdjustNumber) {
        this.overtimeAdjustNumber = overtimeAdjustNumber;
    }

    public String getOnTimeProportion() {
        return onTimeProportion;
    }

    public void setOnTimeProportion(String onTimeProportion) {
        this.onTimeProportion = onTimeProportion;
    }

    public String getCompleteProportion() {
        return completeProportion;
    }

    public void setCompleteProportion(String completeProportion) {
        this.completeProportion = completeProportion;
    }

    public String getPleasedProportion() {
        return pleasedProportion;
    }

    public void setPleasedProportion(String pleasedProportion) {
        this.pleasedProportion = pleasedProportion;
    }


    public Integer getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(Integer systemNumber) {
        this.systemNumber = systemNumber;
    }

    public Integer getCommonOvertimeNumber() {
        return commonOvertimeNumber;
    }

    public void setCommonOvertimeNumber(Integer commonOvertimeNumber) {
        this.commonOvertimeNumber = commonOvertimeNumber;
    }
}