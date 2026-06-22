package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.MenuDTO;
import com.jumpstart.food_ordering_system.response.Response;

import java.util.List;

public interface MenuService {
    Response<MenuDTO> createMenu(MenuDTO dto);
    Response<List<MenuDTO>> getAllMenus();
    Response<MenuDTO> getMenuById(Long id);
}
