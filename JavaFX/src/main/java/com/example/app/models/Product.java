package com.example.app.models;

import java.time.LocalDateTime;

public class Product {
    private int id,stock;
    private String prod_id,prod_name,status,image;
    private Double price;
    private LocalDateTime date;
    private int  quantity;

    public Product(int idCart, int id, String prodId, String prodName, int quantity, String image, double price,
                   LocalDateTime date) {
        this.id = id;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.image = image;
        this.price = price;
        this.date=date;
        this.quantity=quantity;

    }


    public Product(int id, int stock, String prod_id, String prod_name, String status, String image, Double price,  LocalDateTime date) {
        this.id = id;
        this.stock = stock;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.status = status;
        this.image = image;
        this.price = price;
        this.date = date;
    }
    public  Product(int id, String prod_id, String prod_name,int quantity, String image, Double price,LocalDateTime date) {
        this.id = id;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.image = image;
        this.price = price;
        this.date=date;
        this.quantity=quantity;
    }



        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", stock=" + stock +
                ", prod_id='" + prod_id + '\'' +
                ", prod_name='" + prod_name + '\'' +
                ", status='" + status + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", quantity=" + quantity +
                '}';
    }
}

