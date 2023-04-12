/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author FaroukDev
 */

import java.time.LocalDateTime;

public class Product {
    private int id;
    private int categoryId;
    private int userId;
    private String name;
    private String condition;
    private String price;
    private String description;
    private String imagePath;
    private String status;
    private String location;
    private String brand;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String details;
    private boolean authorization;

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", categoryId=" + categoryId + ", userId=" + userId + ", name=" + name + ", condition=" + condition + ", price=" + price + ", description=" + description + ", imagePath=" + imagePath + ", status=" + status + ", location=" + location + ", brand=" + brand + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", details=" + details + ", authorization=" + authorization + '}';
    }

    public Product() {
    }

    public Product(int id, int categoryId, int userId, String name, String condition, String price, String description, String imagePath, String status, String location, String brand, LocalDateTime createdAt, LocalDateTime updatedAt, String details, boolean authorization) {
        this.id = id;
        this.categoryId = categoryId;
        this.userId = userId;
        this.name = name;
        this.condition = condition;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
        this.status = status;
        this.location = location;
        this.brand = brand;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.details = details;
        this.authorization = authorization;
    }

    public Product(int categoryId, int userId, String name, String condition, String price, String description, String imagePath, String status, String location, String brand, String details, boolean authorization) {
        this(0, categoryId, userId, name, condition, price, description, imagePath, status, location, brand, null, null, details, authorization);
    }

     public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public String getBrand() {
        return brand;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getDetails() {
        return details;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

}


