package com.poets.pojo;

public class Remembered {
    private Integer id;

    private String poetId;

    private Integer userId;

    private Integer type;

    private Boolean isCanceled;

    public Remembered(Integer id, String poetId, Integer userId, Integer type, Boolean isCanceled) {
        this.id = id;
        this.poetId = poetId;
        this.userId = userId;
        this.type = type;
        this.isCanceled = isCanceled;
    }

    public Remembered() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoetId() {
        return poetId;
    }

    public void setPoetId(String poetId) {
        this.poetId = poetId == null ? null : poetId.trim();
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