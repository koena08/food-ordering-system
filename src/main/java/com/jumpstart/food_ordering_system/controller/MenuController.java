package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.MenuDTO;
import com.jumpstart.food_ordering_system.response.Response;
import com.jumpstart.food_ordering_system.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<Response<MenuDTO>> create(
            @RequestBody @Valid MenuDTO dto) {
        return ResponseEntity.ok(menuService.createMenu(dto));
    }

    @GetMapping
    public ResponseEntity<Response<Page<MenuDTO>>> all(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(menuService.getAllMenus(categoryId, search, page, size, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MenuDTO>> byId(
            @PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }

    //Update endpoint
    @PutMapping("/{id}")
    public ResponseEntity<Response<MenuDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody MenuDTO dto) {
        return ResponseEntity.ok(menuService.updateMenu(id, dto));
    }

    //Delete Endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.deleteMenu(id));
    }
}
