package com.codegym.furniture.view;

import java.time.Instant;

public class Admin {
    private int id;
    private String username;
    private String passwork;
    private String fullname;
    private String phone;
    private String email;
    private int  idAddress;
    private String image;
    private int role;
    private Instant createdAt;
    private Instant updateAt;


    public Admin() {
    }

    public Admin(int id, String username, String passwork, String fullname, String phone, String email, int idAddress, String image, int role, Instant createdAt, Instant updateAt) {
        this.id = id;
        this.username = username;
        this.passwork = passwork;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.idAddress = idAddress;
        this.image = image;
        this.role = role;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

}
