package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

public class Category {
    private final String name;

    private Category(String name) {
        if (name == null) throw new IllegalArgumentException("Category name can't be null");
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static Category of(String name) {
        if(name==null)throw new IllegalArgumentException("Category name can't be null");
        return CategoryCache.getCategory(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category category = (Category) obj;
        return name.equals(category.name);
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    private static class CategoryCache {
        private static final Category NULL_CATEGORY = new Category("Null");
        private static final Map<String, Category> CACHE = new HashMap<>();
        static Category getCategory(String name) {
            if (name == null) return NULL_CATEGORY;
            return CACHE.computeIfAbsent(name, Category::new);
        }
    }
}