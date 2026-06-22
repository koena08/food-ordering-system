package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.CategoryDTO;
import com.jumpstart.food_ordering_system.response.Response;
import com.jumpstart.food_ordering_system.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Entry point for the REST API
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    //Get All Categories
    @GetMapping
    public ResponseEntity<Response<List<CategoryDTO>>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(Response.success("Categories retrieved successfully", categories));
    }
    //Get Category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Response<CategoryDTO>> getCategoryById(@PathVariable Long id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(Response.success("Category retrieved successfully", category));
    }

    //Http 201 Status
    @PostMapping
    public ResponseEntity<Response<CategoryDTO>> createCategory(@RequestBody @Valid CategoryDTO dto) {
        CategoryDTO createdCategory = categoryService.addCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("Category created successfully", createdCategory));
    }

    //Maps the url path variable to the ID
    //Validates the request body
    @PutMapping("/{id}")
    public ResponseEntity<Response<CategoryDTO>> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDTO dto) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(Response.success("Category updated successfully", updatedCategory));
    }

    //Returns 200 status code with an empty payload
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(Response.success("Category retrieved", null));
    }

}
