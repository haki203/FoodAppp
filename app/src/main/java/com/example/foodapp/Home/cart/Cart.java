package com.example.foodapp.Home.cart;

public class Cart {
    private int photo;
    private int id;
    private String nameProduct;
    private Double price;
    private int amount;

    public Cart(int photo, int id, String nameProduct, Double price, int amount) {
        this.photo = photo;
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.amount = amount;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
