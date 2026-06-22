package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.MenuDTO;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.entity.Menu;
import com.jumpstart.food_ordering_system.exception.CategoryNotFoundException;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import com.jumpstart.food_ordering_system.repository.MenuRepository;
import com.jumpstart.food_ordering_system.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Response<MenuDTO> createMenu(MenuDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + dto.getCategoryId()));

        Menu menu = mapToEntity(dto, category);
        Menu savedMenu = menuRepository.save(menu);

        return Response.success("Menu created successfully", mapToDto(savedMenu));
    }

    @Override
    public Response<List<MenuDTO>> getAllMenus() {
        List<MenuDTO> menuList = menuRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
        return Response.success("Menus retrieved successfully", menuList);
    }

    @Override
    public Response<MenuDTO> getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Menu not found with id: " + id));
        return Response.success("Menu retrieved successfully", mapToDto(menu));
    }

    private MenuDTO mapToDto(Menu menu) {
        return MenuDTO.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .imageUrl(menu.getImageUrl())
                .categoryId(menu.getCategory().getId())
                .categoryName(menu.getCategory().getName())
                .build();
    }

    private Menu mapToEntity(MenuDTO dto, Category category) {
        return Menu.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .category(category)
                .build();
    }
}
