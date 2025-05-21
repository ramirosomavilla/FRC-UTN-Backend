package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dtos.CategoryDTO;
import org.example.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@Tag(name = "Category", description = "CRUD operations for Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    @Operation(summary = "Get all Category")
    @ApiResponse(responseCode = "200", description = "Category found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class))))
    @ApiResponse(responseCode = "404", description = "Category not found", content = @Content())
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Category by Id")
    @ApiResponse(responseCode = "200", description = "Category found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class))))
    @ApiResponse(responseCode = "404", description = "Category not found", content = @Content())
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id) {
        CategoryDTO category = categoryService.findById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a Category")
    @ApiResponse(responseCode = "201", description = "Category created", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class))))
    public ResponseEntity<CategoryDTO> createCatetory(@RequestBody CategoryDTO categoryDto) {
        CategoryDTO createdCategory = categoryService.save(categoryDto);
        URI location = URI.create("/api/v1/categories/" + createdCategory.getCategoryId());
        return ResponseEntity.created(location).body(createdCategory);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Category")
    @ApiResponse(responseCode = "200", description = "Category updated", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class))))
    @ApiResponse(responseCode = "404", description = "Category not found", content = @Content())
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long id,
                                                      @RequestBody CategoryDTO categoryDto) {
        CategoryDTO updatedCategory = categoryService.update(id, categoryDto);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Get a Category by Id")
    @ApiResponse(responseCode = "204", description = "Category found")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
