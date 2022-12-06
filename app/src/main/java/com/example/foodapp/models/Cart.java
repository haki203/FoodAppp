package com.example.foodapp.models;

import java.io.Serializable;

public class Cart implements Serializable {
    private String photo;
    private String id,idUser;
    private String nameProduct;
    private Double price;
    private Integer amount;

    public Cart(String photo, String nameProduct, Double price, int amount) {
        this.photo = photo;
        this.nameProduct = nameProduct;
        this.price = price;
        this.amount = amount;
    }
    public Cart(String photo, String nameProduct, Double price, int amount,String id,String idUser) {
        this.photo = photo;
        this.nameProduct = nameProduct;
        this.price = price;
        this.amount = amount;
        this.id=id;
        this.idUser=idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
