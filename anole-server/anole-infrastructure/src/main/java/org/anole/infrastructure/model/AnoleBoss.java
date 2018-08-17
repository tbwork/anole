/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.model;

import java.util.Date;

public class AnoleBoss {
    private Long id;

    private String alias;

    private String address;

    private Integer portForWorker;

    private Byte status;

    private Long touch;

    private String extraInfo;

    private Integer portForPublisher;

    private Integer portForSubscriber;

    private Integer weight;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getPortForWorker() {
        return portForWorker;
    }

    public void setPortForWorker(Integer portForWorker) {
        this.portForWorker = portForWorker;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getTouch() {
        return touch;
    }

    public void setTouch(Long touch) {
        this.touch = touch;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo == null ? null : extraInfo.trim();
    }

    public Integer getPortForPublisher() {
        return portForPublisher;
    }

    public void setPortForPublisher(Integer portForPublisher) {
        this.portForPublisher = portForPublisher;
    }

    public Integer getPortForSubscriber() {
        return portForSubscriber;
    }

    public void setPortForSubscriber(Integer portForSubscriber) {
        this.portForSubscriber = portForSubscriber;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}