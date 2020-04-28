package com.poets.pojo;

public class Authors {
    private Integer mid;

    private String id;

    private String name;

    private String desc;

    public Authors(Integer mid, String id, String name, String desc) {
        this.mid = mid;
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public Authors() {
        super();
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}