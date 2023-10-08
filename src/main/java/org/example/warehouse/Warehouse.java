package org.example.warehouse;

import org.example.ProductRecord;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {
    private static Warehouse instance;
    private final String name;
    private  final Map<UUID, ProductRecord> productMap;
    private final List<ProductRecord> changedProducts;

    private Warehouse(String name) {
        this.name = name;
        this.productMap = new HashMap<>();
        this.changedProducts = new ArrayList<>();
    }

    public static Warehouse getInstance() {
        if (instance == null) instance = new Warehouse("MyStore");
        return instance;
    }

    public static Warehouse getInstance(String name) {
        return new Warehouse(name);
    }

    public boolean isEmpty() {
        return productMap.isEmpty();
    }

    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {
        if (productMap.containsKey(id))
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Product name can't be null or empty.");
        if (category == null) throw new IllegalArgumentException("Category can't be null.");
        if (price == null) price = BigDecimal.ZERO;
        ProductRecord productRecord = new ProductRecord(id, name, category, price);
        productMap.put(id, productRecord);
        return productRecord;
    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        if (!productMap.containsKey(id)) throw new IllegalArgumentException("Product with that id doesn't exist.");
        else if (newPrice == null) newPrice = BigDecimal.ZERO;
        else {
            ProductRecord productRecord = productMap.get(id);
            productRecord.setPrice(newPrice);
            var add = changedProducts.add(productRecord);
        }
    }

    public List<ProductRecord> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(productMap.values()));
    }

    public Optional<ProductRecord> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public List<ProductRecord> getChangedProducts() {
        return changedProducts;
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        Map<Category, List<ProductRecord>> groupedProducts = new HashMap<>();
        for (ProductRecord product : productMap.values()) {
            Category c = product.getCategory();
            groupedProducts.computeIfAbsent(c, x -> new ArrayList<>()).add(product);
        }
        return groupedProducts;
    }

    public List<ProductRecord> getProductsBy(Category category) {
        List<ProductRecord> products = new ArrayList<>();
        for (ProductRecord product : productMap.values()) {
            if (product.getCategory().equals(category)) products.add(product);
        }
        return products;
    }

}