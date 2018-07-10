package com.velvol.salary.domain;

import java.io.Serializable;
import java.util.Date;

public class EquipWorker implements Serializable {
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
    private Double total;

    /**
     * 
     */
    private Double fistprice;

    /**
     * 
     */
    private Double secondprice;

    /**
     * 
     */
    private Date fisttime;

    /**
     * 
     */
    private Date secondtime;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private Date addtime;

    /**
     * 
     */
    private Double deduction;

    /**
     * 
     */
    private Integer count;

    /**
     * equipworker
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
     * @return total 
     */
    public Double getTotal() {
        return total;
    }

    /**
     * 
     * @param total 
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * 
     * @return fistprice 
     */
    public Double getFistprice() {
        return fistprice;
    }

    /**
     * 
     * @param fistprice 
     */
    public void setFistprice(Double fistprice) {
        this.fistprice = fistprice;
    }

    /**
     * 
     * @return secondprice 
     */
    public Double getSecondprice() {
        return secondprice;
    }

    /**
     * 
     * @param secondprice 
     */
    public void setSecondprice(Double secondprice) {
        this.secondprice = secondprice;
    }

    /**
     * 
     * @return fisttime 
     */
    public Date getFisttime() {
        return fisttime;
    }

    /**
     * 
     * @param fisttime 
     */
    public void setFisttime(Date fisttime) {
        this.fisttime = fisttime;
    }

    /**
     * 
     * @return secondtime 
     */
    public Date getSecondtime() {
        return secondtime;
    }

    /**
     * 
     * @param secondtime 
     */
    public void setSecondtime(Date secondtime) {
        this.secondtime = secondtime;
    }

    /**
     * 
     * @return status 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status 
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * @return deduction 
     */
    public Double getDeduction() {
        return deduction;
    }

    /**
     * 
     * @param deduction 
     */
    public void setDeduction(Double deduction) {
        this.deduction = deduction;
    }

    /**
     * 
     * @return count 
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count 
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}