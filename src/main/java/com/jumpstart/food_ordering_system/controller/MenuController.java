package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.MenuDTO;
import com.jumpstart.food_ordering_system.response.Response;
import com.jumpstart.food_ordering_system.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<Response<MenuDTO>> create(
            @RequestBody @Valid MenuDTO dto) {
        return ResponseEntity.ok(menuService.createMenu(dto));
    }

    @GetMapping
    public ResponseEntity<Response<List<MenuDTO>>> all() {
        return ResponseEntity.ok(menuService.getAllMenus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MenuDTO>> byId(
            @PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }
}
