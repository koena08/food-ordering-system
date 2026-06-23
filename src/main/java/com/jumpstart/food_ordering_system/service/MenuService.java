package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.MenuDTO;
import com.jumpstart.food_ordering_system.response.Response;
import org.springframework.data.domain.Page;

public interface MenuService {
    Response<MenuDTO> createMenu(MenuDTO dto);

    //Upgrade: handles pagination & filtering
    Response<Page<MenuDTO>> getAllMenus(Long categoryId, String search, int page, int size, String sort);

    Response<MenuDTO> getMenuById(Long id);

    //New CRUD ops
    Response<MenuDTO> updateMenu(Long id, MenuDTO dto);
    Response<Void> deleteMenu(Long id);
}
