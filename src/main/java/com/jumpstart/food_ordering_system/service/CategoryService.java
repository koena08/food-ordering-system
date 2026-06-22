package com.jumpstart.food_ordering_system.service;
import com.jumpstart.food_ordering_system.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);
    CategoryDTO addCategory(CategoryDTO dto);

    //Requires both ID and new data
    CategoryDTO updateCategory(Long id, CategoryDTO dto);

    //Method returns void since deleted data doesn't need to be sent back
    void deleteCategory(Long id);
}


