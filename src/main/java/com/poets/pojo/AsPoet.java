package com.poets.pojo;

public class AsPoet {
    private Integer id;

    private Integer age;

    private String sex;

    private String title;

    private String author;

    private String dynasty;

    private String content;

    private String translation;

    private String annotation;

    public AsPoet(Integer id, Integer age, String sex, String title, String author, String dynasty, String content, String translation, String annotation) {
        this.id = id;
        this.age = age;
        this.sex = sex;
        this.title = title;
        this.author = author;
        this.dynasty = dynasty;
        this.content = content;
        this.translation = translation;
        this.annotation = annotation;
    }

    public AsPoet() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty == null ? null : dynasty.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation == null ? null : translation.trim();
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation == null ? null : annotation.trim();
    }
}