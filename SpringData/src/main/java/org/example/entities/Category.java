package org.example.entities;

import jakarta.persistence.*;
import org.example.dtos.CategoryDTO;

@Entity
@Table(name = "Categories")
public class Category {
    @Id()
    @GeneratedValue(generator = "categories")
    @TableGenerator(name = "categories", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="Categories",
            initialValue=1, allocationSize=1)
    @Column(name = "categoryid")
    private Long categoryId;

    @Column(name = "categoryname")
    private String categoryName;
    private String description;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public CategoryDTO ToDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(this.getCategoryId());
        categoryDTO.setCategoryName(this.getCategoryName());
        categoryDTO.setDescription(this.getDescription());
        return categoryDTO;
    }
}

