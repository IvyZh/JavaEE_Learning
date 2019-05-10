package com.ivyzh.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class User {
    private String username;
    private int age;
    private boolean isAudit;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;
    @JsonIgnore
    private String password;

    public User() {

    }

    public User(String username, int age, boolean isAudit, Date birthDay, String password) {
        this.username = username;
        this.age = age;
        this.isAudit = isAudit;
        this.birthDay = birthDay;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAudit() {
        return isAudit;
    }

    public void setAudit(boolean audit) {
        isAudit = audit;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", isAudit=" + isAudit +
                ", birthDay=" + birthDay +
                ", password='" + password + '\'' +
                '}';
    }
}
