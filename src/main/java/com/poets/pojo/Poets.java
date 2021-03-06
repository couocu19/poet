package com.poets.pojo;

import java.io.Serializable;

public class Poets implements Serializable {
    private Integer sid;

    private String id;

    private String author;

    private String paragraphs;

    private String title;

    public Poets(Integer sid, String id, String author, String paragraphs, String title) {
        this.sid = sid;
        this.id = id;
        this.author = author;
        this.paragraphs = paragraphs;
        this.title = title;
    }

    public Poets() {
        super();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String paragraphs) {
        this.paragraphs = paragraphs == null ? null : paragraphs.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}