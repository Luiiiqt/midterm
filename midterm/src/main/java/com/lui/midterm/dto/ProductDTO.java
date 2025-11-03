package com.lui.midterm.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductDTO{

        private int id;
        @NotBlank(message = "Make is required")
        private String name;

        @NotBlank
        private String description;

        @Min(1)
        @NotBlank
        private int stock;

        @NotBlank
        private String unit;

        @Min(1)
        @NotBlank
        private double price;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }
}

