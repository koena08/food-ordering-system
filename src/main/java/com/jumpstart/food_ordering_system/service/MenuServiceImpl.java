package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.MenuDTO;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.entity.Menu;
import com.jumpstart.food_ordering_system.exception.NotFoundException;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import com.jumpstart.food_ordering_system.repository.MenuRepository;
import com.jumpstart.food_ordering_system.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Response<MenuDTO> createMenu(MenuDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + dto.getCategoryId()));

        Menu menu = mapToEntity(dto, category);
        Menu savedMenu = menuRepository.save(menu);

        return Response.success("Menu created successfully", mapToDto(savedMenu));
    }

    //Pagination & Filtering logic
    @Override
    public Response<Page<MenuDTO>> getAllMenus(Long categoryId, String search, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, getSort(sort));
        Page<Menu> menuPage;

        boolean hasCategory = categoryId != null;
        boolean hasSearch = search != null && !search.trim().isEmpty();

        if (hasCategory && hasSearch) {
            menuPage = menuRepository.findByCategoryIdAndNameContainingIgnoreCase(categoryId, search, pageable);
        } else if (hasCategory) {
            menuPage = menuRepository.findByCategoryId(categoryId, pageable);
        } else if (hasSearch) {
            menuPage = menuRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            menuPage = menuRepository.findAll(pageable);
        }

        Page<MenuDTO> dtoPage = menuPage.map(this::mapToDto);
        return Response.success("Menus retrieved successfully", dtoPage);
    }

    @Override
    public Response<MenuDTO> getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu not found with id: " + id));
        return Response.success("Menu retrieved successfully", mapToDto(menu));
    }

    //Update logic
    @Override
    public Response<MenuDTO> updateMenu(Long id, MenuDTO dto) {
        Menu existingMenu = menuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + dto.getCategoryId()));

        existingMenu.setName(dto.getName());
        existingMenu.setDescription(dto.getDescription());
        existingMenu.setPrice(dto.getPrice());
        existingMenu.setImageUrl(dto.getImageUrl());
        existingMenu.setCategory(category);

        Menu updatedMenu = menuRepository.save(existingMenu);
        return Response.success("Menu updated successfully", mapToDto(updatedMenu));
    }

    //Delete logic
    @Override
    public Response<Void> deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu not found with id: " + id));

        menuRepository.delete(menu);
        return Response.success("Menu deleted successfully", null);
    }

    //Helper method to parse strings like "price,asc"
    private Sort getSort(String sortParam) {
        if (sortParam == null || sortParam.trim().isEmpty()) {
            return Sort.unsorted();
        }

        String[] parts = sortParam.split(",");
        String property = parts[0];
        Sort.Direction direction = Sort.Direction.ASC;

        if (parts.length > 1 && parts[1].equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }

        return Sort.by(direction, property);
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
