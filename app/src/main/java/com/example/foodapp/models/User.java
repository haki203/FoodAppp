package com.example.foodapp.models;

public class User {
    private String idUser,  name,username,diaChi,ngaySinh,password;
    public User(String username){
        this.username=username;
    }
    public User( String name, String username, String diaChi, String ngaySinh, String password) {
        this.name = name;
        this.username = username;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.password = password;
    }
    public User()
    {

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
