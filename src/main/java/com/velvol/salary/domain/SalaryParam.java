package com.velvol.salary.domain;

import java.io.Serializable;

public class SalaryParam implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 规则id
     */
    private Integer ruleid;

    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 参数名字
     */
    private String name;

    /**
     * 区间参数下限
     */
    private Integer intervalMin;

    /**
     * 区间参数上限
     */
    private Integer intervalMax;

    /**
     * 参数值
     */
    private Integer value;

    /**
     * 参数类型 0 普通参数,1金额参数 (金额存储为分)
     */
    private Integer type;

    /**
     * salary_param
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
     * 规则id
     * @return ruleid 规则id
     */
    public Integer getRuleid() {
        return ruleid;
    }

    /**
     * 规则id
     * @param ruleid 规则id
     */
    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
    }

    /**
     * 参数代码
     * @return param_code 参数代码
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 参数代码
     * @param paramCode 参数代码
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * 参数名字
     * @return name 参数名字
     */
    public String getName() {
        return name;
    }

    /**
     * 参数名字
     * @param name 参数名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 区间参数下限
     * @return interval_min 区间参数下限
     */
    public Integer getIntervalMin() {
        return intervalMin;
    }

    /**
     * 区间参数下限
     * @param intervalMin 区间参数下限
     */
    public void setIntervalMin(Integer intervalMin) {
        this.intervalMin = intervalMin;
    }

    /**
     * 区间参数上限
     * @return interval_max 区间参数上限
     */
    public Integer getIntervalMax() {
        return intervalMax;
    }

    /**
     * 区间参数上限
     * @param intervalMax 区间参数上限
     */
    public void setIntervalMax(Integer intervalMax) {
        this.intervalMax = intervalMax;
    }

    /**
     * 参数值
     * @return value 参数值
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 参数值
     * @param value 参数值
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * 参数类型 0 普通参数,1金额参数 (金额存储为分)
     * @return type 参数类型 0 普通参数,1金额参数 (金额存储为分)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 参数类型 0 普通参数,1金额参数 (金额存储为分)
     * @param type 参数类型 0 普通参数,1金额参数 (金额存储为分)
     */
    public void setType(Integer type) {
        this.type = type;
    }
}