package com.velvol.salary.domain;

import java.io.Serializable;
import java.util.Date;

public class SalaryRule implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 计算规则名字
     */
    private String name;

    /**
     * 适用城市
     */
    private String city;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * salary_rule
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 计算规则名字
     * @return name 计算规则名字
     */
    public String getName() {
        return name;
    }

    /**
     * 计算规则名字
     * @param name 计算规则名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 适用城市
     * @return city 适用城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 适用城市
     * @param city 适用城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 添加时间
     * @return add_time 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 添加时间
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}