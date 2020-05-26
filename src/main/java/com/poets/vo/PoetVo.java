package com.poets.vo;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class PoetVo {
    private int sid;
    private String author;
    private String title;
    private String paragraphs;
    private String dynasty;
    private Boolean isCollected;


    public PoetVo(int sid, String author, String title, String paragraphs, String dynasty, Boolean isCollected) {
        this.sid = sid;
        this.author = author;
        this.title = title;
        this.paragraphs = paragraphs;
        this.dynasty = dynasty;
        this.isCollected = isCollected;
    }

    public Boolean getCollected() {
        return isCollected;
    }

    public void setCollected(Boolean collected) {
        isCollected = collected;
    }

    public PoetVo(){


    }
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String paragraphs) {
        this.paragraphs = paragraphs;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }
}
