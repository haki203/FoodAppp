package com.example.foodapp.models;

import java.io.Serializable;

public class User implements Serializable {
    private String idUser,  name,sdt,diaChi,gmail,password,id;
    public User( String name, String sdt, String diaChi, String password,String gmail) {
        this.name = name;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.gmail = gmail;
        this.password = password;
    }
    public User(String id, String name, String sdt, String diaChi, String password,String gmail) {
        this.id=id;
        this.name = name;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.gmail = gmail;
        this.password = password;
    }
    public User()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String username) {
        this.sdt = username;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
