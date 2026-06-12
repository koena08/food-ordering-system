package com.jumpstart.food_ordering_system.repository;
import com.jumpstart.food_ordering_system.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Encapsulates logic required to access data sources
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}