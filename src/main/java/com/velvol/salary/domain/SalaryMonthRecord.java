package com.velvol.salary.domain;

import java.io.Serializable;
import java.util.Date;

public class SalaryMonthRecord extends BaseModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 工资计算年份
     */
    private String year;

    /**
     * 工资计算月份
     */
    private String month;

    /**
     * 0.未计算1.计算中.2计算完成
     */
    private Integer status;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 操作人
     */
    private String operator;

    private Integer region;

    private Integer attend;

    public Integer getAttend() {
        return attend;
    }

    public void setAttend(Integer attend) {
        this.attend = attend;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    /**
     * salary_month_record
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
     * 工资计算年份
     * @return year 工资计算年份
     */
    public String getYear() {
        return year;
    }

    /**
     * 工资计算年份
     * @param year 工资计算年份
     */
    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    /**
     * 工资计算月份
     * @return month 工资计算月份
     */
    public String getMonth() {
        return month;
    }

    /**
     * 工资计算月份
     * @param month 工资计算月份
     */
    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    /**
     * 0.未计算1.计算中.2计算完成
     * @return status 0.未计算1.计算中.2计算完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 0.未计算1.计算中.2计算完成
     * @param status 0.未计算1.计算中.2计算完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 添加时间
     * @return addtime 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 添加时间
     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 操作人
     * @return operator 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 操作人
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
}