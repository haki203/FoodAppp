package com.example.foodapp.models;

public class HoaDon {
    String nameSP,nameUser,time,diaChi,idHD,idUser,price;

    public HoaDon(String nameSP, String nameUser, String time, String diaChi, String idHD, String idUser, String price) {
        this.nameSP = nameSP;
        this.nameUser = nameUser;
        this.time = time;
        this.diaChi = diaChi;
        this.idHD = idHD;
        this.idUser = idUser;
        this.price = price;
    }

    public String getNameSP() {
        return nameSP;
    }

    public void setNameSP(String nameSP) {
        this.nameSP = nameSP;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
