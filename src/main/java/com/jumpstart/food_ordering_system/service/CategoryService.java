package com.jumpstart.food_ordering_system.service;
import com.jumpstart.food_ordering_system.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);
}


