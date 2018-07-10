package com.velvol.salary.domain;

import java.io.Serializable;
import java.util.Date;

public class Worker implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String meituanid;

    /**
     * 
     */
    private String sex;

    /**
     * 
     */
    private String cardid;

    /**
     * 
     */
    private String telephone;

    /**
     * 
     */
    private Integer type;

    /**
     * 
     */
    private Integer getstate;

    /**
     * 
     */
    private String picpath;

    /**
     * 
     */
    private String picpathlzd;

    /**
     * 
     */
    private Integer state;

    /**
     * 
     */
    private Long regionId;

    /**
     * 
     */
    private Long stationId;

    /**
     * 
     */
    private String yhcard;

    /**
     * 
     */
    private String yhperson;

    /**
     * 
     */
    private String yhfrom;

    /**
     * 
     */
    private Date indate;

    /**
     * 
     */
    private String remark;

    /**
     * 
     */
    private Date meituandate;

    /**
     * 
     */
    private Integer sort;

    /**
     * 
     */
    private Date predate;

    /**
     * 
     */
    private String infofrom;

    /**
     * 
     */
    private String referee;

    /**
     * 
     */
    private Date htdate;

    /**
     * 
     */
    private Date lzdate;

    /**
     * 
     */
    private String lianxiren;

    /**
     * 
     */
    private String lianxirenphone;

    /**
     * 
     */
    private String otherfrom;

    /**
     * 
     */
    private String openid;

    /**
     * 
     */
    private String otherphone;

    /**
     * 
     */
    private String xflag;

    private Integer recruittype;  // 招聘类型(0人事  1站长)

    private Date reqtime; //申请时间
    /**
     * worker
     */
    private static final long serialVersionUID = 1L;


    public Integer getRecruittype() {
        return recruittype;
    }

    public void setRecruittype(Integer recruittype) {
        this.recruittype = recruittype;
    }

    public Date getReqtime() {
        return reqtime;
    }

    public void setReqtime(Date reqtime) {
        this.reqtime = reqtime;
    }

    /**
     * 
     * @return id 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 
     * @return meituanid 
     */
    public String getMeituanid() {
        return meituanid;
    }

    /**
     * 
     * @param meituanid 
     */
    public void setMeituanid(String meituanid) {
        this.meituanid = meituanid == null ? null : meituanid.trim();
    }

    /**
     * 
     * @return sex 
     */
    public String getSex() {
        return sex;
    }

    /**
     * 
     * @param sex 
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 
     * @return cardid 
     */
    public String getCardid() {
        return cardid;
    }

    /**
     * 
     * @param cardid 
     */
    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    /**
     * 
     * @return telephone 
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 
     * @param telephone 
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 
     * @return type 
     */
    public Integer getType() {
        return type;
    }

    /**
     * 
     * @param type 
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 
     * @return getstate 
     */
    public Integer getGetstate() {
        return getstate;
    }

    /**
     * 
     * @param getstate 
     */
    public void setGetstate(Integer getstate) {
        this.getstate = getstate;
    }

    /**
     * 
     * @return picpath 
     */
    public String getPicpath() {
        return picpath;
    }

    /**
     * 
     * @param picpath 
     */
    public void setPicpath(String picpath) {
        this.picpath = picpath == null ? null : picpath.trim();
    }

    /**
     * 
     * @return picpathlzd 
     */
    public String getPicpathlzd() {
        return picpathlzd;
    }

    /**
     * 
     * @param picpathlzd 
     */
    public void setPicpathlzd(String picpathlzd) {
        this.picpathlzd = picpathlzd == null ? null : picpathlzd.trim();
    }

    /**
     * 
     * @return state 
     */
    public Integer getState() {
        return state;
    }

    /**
     * 
     * @param state 
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 
     * @return region_id 
     */
    public Long getRegionId() {
        return regionId;
    }

    /**
     * 
     * @param regionId 
     */
    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    /**
     * 
     * @return station_id 
     */
    public Long getStationId() {
        return stationId;
    }

    /**
     * 
     * @param stationId 
     */
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    /**
     * 
     * @return yhcard 
     */
    public String getYhcard() {
        return yhcard;
    }

    /**
     * 
     * @param yhcard 
     */
    public void setYhcard(String yhcard) {
        this.yhcard = yhcard == null ? null : yhcard.trim();
    }

    /**
     * 
     * @return yhperson 
     */
    public String getYhperson() {
        return yhperson;
    }

    /**
     * 
     * @param yhperson 
     */
    public void setYhperson(String yhperson) {
        this.yhperson = yhperson == null ? null : yhperson.trim();
    }

    /**
     * 
     * @return yhfrom 
     */
    public String getYhfrom() {
        return yhfrom;
    }

    /**
     * 
     * @param yhfrom 
     */
    public void setYhfrom(String yhfrom) {
        this.yhfrom = yhfrom == null ? null : yhfrom.trim();
    }

    /**
     * 
     * @return indate 
     */
    public Date getIndate() {
        return indate;
    }

    /**
     * 
     * @param indate 
     */
    public void setIndate(Date indate) {
        this.indate = indate;
    }

    /**
     * 
     * @return remark 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 
     * @return meituandate 
     */
    public Date getMeituandate() {
        return meituandate;
    }

    /**
     * 
     * @param meituandate 
     */
    public void setMeituandate(Date meituandate) {
        this.meituandate = meituandate;
    }

    /**
     * 
     * @return sort 
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 
     * @param sort 
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 
     * @return predate 
     */
    public Date getPredate() {
        return predate;
    }

    /**
     * 
     * @param predate 
     */
    public void setPredate(Date predate) {
        this.predate = predate;
    }

    /**
     * 
     * @return infofrom 
     */
    public String getInfofrom() {
        return infofrom;
    }

    /**
     * 
     * @param infofrom 
     */
    public void setInfofrom(String infofrom) {
        this.infofrom = infofrom == null ? null : infofrom.trim();
    }

    /**
     * 
     * @return referee 
     */
    public String getReferee() {
        return referee;
    }

    /**
     * 
     * @param referee 
     */
    public void setReferee(String referee) {
        this.referee = referee == null ? null : referee.trim();
    }

    /**
     * 
     * @return htDate 
     */
    public Date getHtdate() {
        return htdate;
    }

    /**
     * 
     * @param htdate 
     */
    public void setHtdate(Date htdate) {
        this.htdate = htdate;
    }

    /**
     * 
     * @return lzDate 
     */
    public Date getLzdate() {
        return lzdate;
    }

    /**
     * 
     * @param lzdate 
     */
    public void setLzdate(Date lzdate) {
        this.lzdate = lzdate;
    }

    /**
     * 
     * @return lianxiren 
     */
    public String getLianxiren() {
        return lianxiren;
    }

    /**
     * 
     * @param lianxiren 
     */
    public void setLianxiren(String lianxiren) {
        this.lianxiren = lianxiren == null ? null : lianxiren.trim();
    }

    /**
     * 
     * @return lianxirenphone 
     */
    public String getLianxirenphone() {
        return lianxirenphone;
    }

    /**
     * 
     * @param lianxirenphone 
     */
    public void setLianxirenphone(String lianxirenphone) {
        this.lianxirenphone = lianxirenphone == null ? null : lianxirenphone.trim();
    }

    /**
     * 
     * @return otherfrom 
     */
    public String getOtherfrom() {
        return otherfrom;
    }

    /**
     * 
     * @param otherfrom 
     */
    public void setOtherfrom(String otherfrom) {
        this.otherfrom = otherfrom == null ? null : otherfrom.trim();
    }

    /**
     * 
     * @return openid 
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 
     * @param openid 
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 
     * @return otherphone 
     */
    public String getOtherphone() {
        return otherphone;
    }

    /**
     * 
     * @param otherphone 
     */
    public void setOtherphone(String otherphone) {
        this.otherphone = otherphone == null ? null : otherphone.trim();
    }

    /**
     * 
     * @return xflag 
     */
    public String getXflag() {
        return xflag;
    }

    /**
     * 
     * @param xflag 
     */
    public void setXflag(String xflag) {
        this.xflag = xflag == null ? null : xflag.trim();
    }
}