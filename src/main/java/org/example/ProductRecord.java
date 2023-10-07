package org.example;

import org.example.warehouse.Category;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRecord {
    private final UUID id;
    private final String name;
    private final Category category;
    public BigDecimal price;

    public ProductRecord(UUID id, String name, Category category, BigDecimal price) {
        if(id==null) this.id=UUID.randomUUID();
        else this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UUID uuid() {
        return this.getId();
    }

    public Category category() {
        return this.getCategory();
    }

    public BigDecimal price() {
        return this.getPrice();
    }
}