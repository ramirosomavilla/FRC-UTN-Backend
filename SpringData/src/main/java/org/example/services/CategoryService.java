package org.example.services;

import org.example.dtos.CategoryDTO;
import org.example.entities.Category;
import org.example.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(Category::ToDTO).collect(Collectors.toList());
    }

    public CategoryDTO findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(Category::ToDTO).orElse(null);
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryDTO.ToEntity();
        Category savedCategory = categoryRepository.save(category);
        return savedCategory.ToDTO();
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category category = categoryDTO.ToEntity();
            category.setCategoryId(id);
            Category updatedCategory = categoryRepository.save(category);
            return updatedCategory.ToDTO();
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
