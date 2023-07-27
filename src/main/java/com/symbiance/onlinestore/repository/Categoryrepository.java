package com.symbiance.onlinestore.repository;

import com.symbiance.onlinestore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Categoryrepository extends JpaRepository<Category,Long> {

    Category findCategoryById(Long id);
    Category findCategoryByName(String name);
}
