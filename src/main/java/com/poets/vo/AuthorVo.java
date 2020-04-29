package com.poets.vo;

public class AuthorVo {
    private int mid;
    private String name;
    private String dynasty;
    private String header;

    public AuthorVo(int mid, String name, String dynasty, String header) {
        this.mid = mid;
        this.name = name;
        this.dynasty = dynasty;
        this.header = header;
    }

    public AuthorVo(){


    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
