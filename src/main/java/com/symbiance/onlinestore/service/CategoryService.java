package com.symbiance.onlinestore.service;

import com.symbiance.onlinestore.model.Category;
import com.symbiance.onlinestore.repository.Categoryrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private Categoryrepository categoryrepository;

    public void addcatgory(Category category) {
         categoryrepository.save(category);
    }

    public List<Category> getalldetails() {
        List<Category> list=new ArrayList<>();
        categoryrepository.findAll().forEach(list::add);
        return list;
    }


   public Category findCategoryById(Long id){
    return categoryrepository.findCategoryById(id);
  }
    public void deletecatgory(Category category) {
         categoryrepository.delete(category);

    }
    public void editCategory(Long id, Category updateCategory) {
        Category category = categoryrepository.getById(id);
        category.setName(updateCategory.getName());
        categoryrepository.save(category);

    }
    public boolean findById(long id) {
        return categoryrepository.findById(id).isPresent();
    }
    public Category getonedata(Long id) {
    Category category1=categoryrepository.findById(id).get();
    return category1;
    }
}
