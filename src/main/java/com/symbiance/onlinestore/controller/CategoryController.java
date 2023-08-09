package com.symbiance.onlinestore.controller;

import com.symbiance.onlinestore.model.Category;

import com.symbiance.onlinestore.response.Apiresponse;
import com.symbiance.onlinestore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public String addcatgory(@RequestBody Category category) {
        Optional<Category> category1 = categoryService.addcatgory(category);
        if (category1.isPresent()) {
            return "the category add successfully";
        } else return "already exits";
    }

    @GetMapping("/category")
    public List<Category> getall() {
        return categoryService.getalldetails();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getone(@PathVariable Long id) {
        Optional<Category> category = categoryService.getonedata(id);
        if (category.isPresent()) {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Apiresponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        System.out.println(" id " + id);
        if (!categoryService.findById(id)) {
            return new ResponseEntity<>(new Apiresponse("category does not exists", false), HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(id, category);
        return new ResponseEntity<>(new Apiresponse("category has been updated", true), HttpStatus.OK);
    }


    @DeleteMapping("/category/{id}")
    public void deletecatgoty(@PathVariable Long id) {
        Category category = categoryService.findCategoryById(id);
        categoryService.deletecatgory(category);
    }

}
