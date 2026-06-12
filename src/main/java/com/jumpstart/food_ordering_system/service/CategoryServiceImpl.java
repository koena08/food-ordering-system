package com.jumpstart.food_ordering_system.service;
import com.jumpstart.food_ordering_system.dto.CategoryDTO;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

//Handles business logic & data transformations
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
