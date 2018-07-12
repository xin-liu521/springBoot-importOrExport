package com.velvol.salary.domain;

import java.io.Serializable;
import java.util.Date;

public class Bankcardinfo implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String workid;

    /**
     * 
     */
    private String cardnum;

    /**
     * 
     */
    private String cardholder;

    /**
     * 
     */
    private String address;

    /**
     * 
     */
    private String relationship;

    /**
     * 
     */
    private Integer flag;

    /**
     * 
     */
    private Date addtime;

    /**
     * 
     */
    private String ownerid;

    /**
     * 
     */
    private String ownerphone;

    /**
     * bankcardinfo
     */
    private static final long serialVersionUID = 1L;

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
     * 
     * @return workid 
     */
    public String getWorkid() {
        return workid;
    }

    /**
     * 
     * @param workid 
     */
    public void setWorkid(String workid) {
        this.workid = workid == null ? null : workid.trim();
    }

    /**
     * 
     * @return cardnum 
     */
    public String getCardnum() {
        return cardnum;
    }

    /**
     * 
     * @param cardnum 
     */
    public void setCardnum(String cardnum) {
        this.cardnum = cardnum == null ? null : cardnum.trim();
    }

    /**
     * 
     * @return cardholder 
     */
    public String getCardholder() {
        return cardholder;
    }

    /**
     * 
     * @param cardholder 
     */
    public void setCardholder(String cardholder) {
        this.cardholder = cardholder == null ? null : cardholder.trim();
    }

    /**
     * 
     * @return address 
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address 
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 
     * @return relationship 
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * 
     * @param relationship 
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship == null ? null : relationship.trim();
    }

    /**
     * 
     * @return flag 
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 
     * @param flag 
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 
     * @return addtime 
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 
     * @param addtime 
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 
     * @return ownerid 
     */
    public String getOwnerid() {
        return ownerid;
    }

    /**
     * 
     * @param ownerid 
     */
    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid == null ? null : ownerid.trim();
    }

    /**
     * 
     * @return ownerphone 
     */
    public String getOwnerphone() {
        return ownerphone;
    }

    /**
     * 
     * @param ownerphone 
     */
    public void setOwnerphone(String ownerphone) {
        this.ownerphone = ownerphone == null ? null : ownerphone.trim();
    }
}