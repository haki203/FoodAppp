package com.example.foodapp.models;

public class SanPham {
    private  String name,loai,moTa,tinhTrang,hinh,id;
    private String gia;

    public SanPham(String name, String loai, String moTa, String tinhTrang, String hinh, String id, String gia) {
        this.name = name;
        this.loai = loai;
        this.moTa = moTa;
        this.tinhTrang = tinhTrang;
        this.hinh = hinh;
        this.id = id;
        this.gia = gia;
    }
    public  SanPham(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
