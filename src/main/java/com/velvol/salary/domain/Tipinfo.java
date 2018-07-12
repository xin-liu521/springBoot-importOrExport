package com.velvol.salary.domain;

import java.io.Serializable;

public class Tipinfo implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer type;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Long regionid;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String linkman;

    /**
     * 
     */
    private String linkmantel;

    /**
     * 
     */
    private String addr;

    /**
     * tipinfo
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
     * @return content 
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content 
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 
     * @return regionid 
     */
    public Long getRegionid() {
        return regionid;
    }

    /**
     * 
     * @param regionid 
     */
    public void setRegionid(Long regionid) {
        this.regionid = regionid;
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
     * @return linkman 
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * 
     * @param linkman 
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    /**
     * 
     * @return linkmantel 
     */
    public String getLinkmantel() {
        return linkmantel;
    }

    /**
     * 
     * @param linkmantel 
     */
    public void setLinkmantel(String linkmantel) {
        this.linkmantel = linkmantel == null ? null : linkmantel.trim();
    }

    /**
     * 
     * @return addr 
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 
     * @param addr 
     */
    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }
}