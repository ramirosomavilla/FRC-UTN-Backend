package org.example.dtos;

import org.example.entities.Category;

public class CategoryDTO {
    private Long categoryId;
    private String CategoryName;
    private String description;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category ToEntity() {
        Category category = new Category();
        category.setCategoryId(this.getCategoryId());
        category.setCategoryName(this.getCategoryName());
        category.setDescription(this.getDescription());
        return category;
    }
}
