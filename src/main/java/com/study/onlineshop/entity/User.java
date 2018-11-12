package com.study.onlineshop.entity;

import java.time.LocalDateTime;

public class User {
    private int    id;
    private String name;
    private LocalDateTime creationDate;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User {" +
                "id =" + id +
                ", name ='" + name + '\'' +
                ", creationDate = " + creationDate +
                ", role = " + role +
                '}';
    }

}
