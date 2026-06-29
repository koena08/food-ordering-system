package com.jumpstart.food_ordering_system.repository;

import com.jumpstart.food_ordering_system.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    //Finds items strictly within a category(pagination)
    Page<Menu> findByCategoryId(Long categoryId, Pageable pageable);

    //Finds items where the name contains the search string
    Page<Menu> findByNameContainingIgnoreCase(String name, Pageable pageable);

    //Combines both filters(specific category AND name matching
    Page<Menu> findByCategoryIdAndNameContainingIgnoreCase(Long categoryId, String name, Pageable pageable);

}


