package com.velvol.salary.domain;

import java.io.Serializable;
import java.util.Date;

public class EquipWorksec implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer equipworkid;

    /**
     * 
     */
    private Double fee;

    /**
     * 
     */
    private Date kftime;

    /**
     * 
     */
    private String username;

    /**
     * equipworksec
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
     * @return equipworkid 
     */
    public Integer getEquipworkid() {
        return equipworkid;
    }

    /**
     * 
     * @param equipworkid 
     */
    public void setEquipworkid(Integer equipworkid) {
        this.equipworkid = equipworkid;
    }

    /**
     * 
     * @return fee 
     */
    public Double getFee() {
        return fee;
    }

    /**
     * 
     * @param fee 
     */
    public void setFee(Double fee) {
        this.fee = fee;
    }

    /**
     * 
     * @return kftime 
     */
    public Date getKftime() {
        return kftime;
    }

    /**
     * 
     * @param kftime 
     */
    public void setKftime(Date kftime) {
        this.kftime = kftime;
    }

    /**
     * 
     * @return username 
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}