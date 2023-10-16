package com.symbiance.onlinestore.service;

import com.symbiance.onlinestore.model.Category;
import com.symbiance.onlinestore.repository.Categoryrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private Categoryrepository categoryrepository;

    public Optional<Category> addcatgory(Category category) {
        if (categoryrepository.existsById(category.getId())) {
            return Optional.empty();
        } else
            return Optional.of(categoryrepository.save(category));
    }

    public List<Category> getalldetails() {
       return categoryrepository.findAll();
    }

    public void editCategory(Long id, Category updateCategory) {
        Category category = categoryrepository.getById(id);
        category.setName(updateCategory.getName());
        categoryrepository.save(category);

    }

    public boolean findById(long id) {
        return categoryrepository.findById(id).isPresent();
    }

    public Optional<Category> getonedata(Long id) {
        Optional<Category> category1 = categoryrepository.findById(id);
        return category1;
    }

    public ResponseEntity<String> deleteCatagory(Long id) {
        try {
            categoryrepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

