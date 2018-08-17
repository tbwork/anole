/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.model;

import java.util.Date;

public class AnoleSysSetting {
    private Long id;

    private Long servingBoss;

    private Integer heartBeatInterval;

    private Integer touchInterval;

    private Integer touchStopCount;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServingBoss() {
        return servingBoss;
    }

    public void setServingBoss(Long servingBoss) {
        this.servingBoss = servingBoss;
    }

    public Integer getHeartBeatInterval() {
        return heartBeatInterval;
    }

    public void setHeartBeatInterval(Integer heartBeatInterval) {
        this.heartBeatInterval = heartBeatInterval;
    }

    public Integer getTouchInterval() {
        return touchInterval;
    }

    public void setTouchInterval(Integer touchInterval) {
        this.touchInterval = touchInterval;
    }

    public Integer getTouchStopCount() {
        return touchStopCount;
    }

    public void setTouchStopCount(Integer touchStopCount) {
        this.touchStopCount = touchStopCount;
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