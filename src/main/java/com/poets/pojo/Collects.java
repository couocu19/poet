package com.poets.pojo;

import java.io.Serializable;

public class Collects implements Serializable {
    private Integer id;

    private Integer poetId;

    private Integer userId;

    private Integer type;

    private Boolean isCanceled;

    public Collects(Integer id, Integer poetId, Integer userId, Integer type, Boolean isCanceled) {
        this.id = id;
        this.poetId = poetId;
        this.userId = userId;
        this.type = type;
        this.isCanceled = isCanceled;
    }

    public Collects() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoetId() {
        return poetId;
    }

    public void setPoetId(Integer poetId) {
        this.poetId = poetId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(Boolean isCanceled) {
        this.isCanceled = isCanceled;
    }
}