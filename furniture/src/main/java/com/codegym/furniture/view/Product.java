package com.codegym.furniture.view;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private int price;
    private String image;
    private String description;
    private int idcategory;

    public Product() {
    }


    public Product(int id, String name, int quantity, int price, String image, String description, int idcategory) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.description = description;
        this.idcategory = idcategory;
    }

    public Product(String name, int quantity, int price, String image, String description, int idcategory) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.description = description;
        this.idcategory = idcategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
@NotEmpty(message = "Name Product not null")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
@Min(1)
@Max(100)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
@Min(10000)
@Max(100000000)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
@NotEmpty(message = "Image not null")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", idcategory=" + idcategory +
                '}';
    }
}
