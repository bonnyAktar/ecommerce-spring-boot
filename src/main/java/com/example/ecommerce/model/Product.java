package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@JsonPropertyOrder({"id","name","price","category","quantity"})
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @NotBlank(message = "name cannot be blank.")
    private String name;

    @Positive(message = "price must be positive.")
    private double price;

    @NotBlank(message = "category cannot be blank.")
    private String category;

    @Min(value = 0,message = "quantity cannot be negative.")
    private int quantity;

    public Product(){

    }

    public Product(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
