package com.poets.pojo;

public class Clothes {
    private Integer id;

    private Integer userId;

    private Integer hair;

    private Integer dress;

    private Integer background;

    private Integer face;

    public Clothes(Integer id, Integer userId, Integer hair, Integer dress, Integer background, Integer face) {
        this.id = id;
        this.userId = userId;
        this.hair = hair;
        this.dress = dress;
        this.background = background;
        this.face = face;
    }

    public Clothes() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHair() {
        return hair;
    }

    public void setHair(Integer hair) {
        this.hair = hair;
    }

    public Integer getDress() {
        return dress;
    }

    public void setDress(Integer dress) {
        this.dress = dress;
    }

    public Integer getBackground() {
        return background;
    }

    public void setBackground(Integer background) {
        this.background = background;
    }

    public Integer getFace() {
        return face;
    }

    public void setFace(Integer face) {
        this.face = face;
    }
}