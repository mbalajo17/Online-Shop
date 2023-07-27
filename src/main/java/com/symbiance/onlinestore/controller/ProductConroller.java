package com.symbiance.onlinestore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.symbiance.onlinestore.model.Category;
import com.symbiance.onlinestore.model.Prduct;
import com.symbiance.onlinestore.response.Apiresponse;
import com.symbiance.onlinestore.service.CategoryService;
import com.symbiance.onlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;



@RestController
@CrossOrigin
public class ProductConroller {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addproduct")
    public void saveproduct(@RequestPart("file") MultipartFile file,
                            @RequestPart("data") String name) throws JsonProcessingException {

        productService.saveproduct(file, name);
    }
    @GetMapping(path = "/categories/{category}")
    public List<Prduct> getAllProductsByCategoryName(@PathVariable(value="category") String category){
        return productService.findAllByCategoryName(category);
    }
    @GetMapping("/listproduct")
    public List<Prduct> showall(){
        return productService.listallproduct();
    }

    @PutMapping("/updateproduct/{id}")
    public void updatecatgory(@RequestPart("file") MultipartFile file,@RequestPart("data") String data
                                 , @PathVariable Long id) throws IOException {
     productService.updatedetalis(file,data,id);

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        return  productService.deletedetails(id);
    }

    @GetMapping("/product/{id}")
    public Prduct getone(@PathVariable Long id){
        return productService.getonedata(id);
    }


}





