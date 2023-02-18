package com.tms.webshop.model;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private String imageName;
    private List<Product> productList;

    public Category(int id, String name, String imageName, List<Product> productList) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.productList = productList;
    }

    public Category() {
    }

    public Category(String name, String imageName, List<Product> productList) {
        this.name = name;
        this.imageName = imageName;
        this.productList = productList;
    }

    public Category(String name, String imageName) {
        this.name = name;
        this.imageName = imageName;
    }

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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
