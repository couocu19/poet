package com.poets.pojo;

public class User {
    private Integer id;

    private String accountNumber;

    private String name;

    private String phone;

    private String password;

    private String header;

    private String signature;

    private Integer grades;

    public User(Integer id, String accountNumber, String name, String phone, String password, String header, String signature, Integer grades) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.header = header;
        this.signature = signature;
        this.grades = grades;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public Integer getGrades() {
        return grades;
    }

    public void setGrades(Integer grades) {
        this.grades = grades;
    }
}