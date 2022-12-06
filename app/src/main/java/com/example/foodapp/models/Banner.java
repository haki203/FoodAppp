package com.example.foodapp.models;

public class Banner {
    private int resourceId;
    //sử dụng cho HomeActivity, phần slideshow
    public Banner(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}