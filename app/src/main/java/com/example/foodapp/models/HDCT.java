package com.example.foodapp.models;

public class HDCT {
    String id,idSP,name;
    Double gia;
    Integer soLuong;

    public HDCT(String id,String idSP, String name, Double gia, Integer soLuong) {
        this.id=id;
        this.idSP = idSP;
        this.name = name;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
}
