package com.symbiance.onlinestore.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.symbiance.onlinestore.model.Prduct;
import com.symbiance.onlinestore.repository.Productrepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private Productrepositoy productrepositoy;


    public void saveproduct(MultipartFile file, String data) throws JsonProcessingException {

        Prduct prduct = objectMapper.readValue(data, Prduct.class);

        try {
            prduct.setImageURL(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        productrepositoy.save(prduct);
    }

    public List<Prduct> listallproduct() {

        return productrepositoy.findAll();
    }

    public Prduct updatedetalis(MultipartFile file, String data, Long id) throws IOException {
        Prduct prduct = objectMapper.readValue(data, Prduct.class);
        try {
            prduct.setImageURL(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Prduct prduct1=productrepositoy.findById(id).get();
        prduct1.setImageURL(file.getBytes());
        prduct1.setName(prduct.getName());
        prduct1.setDescription(prduct.getDescription());
        prduct1.setPrice(prduct.getPrice());
        prduct1.setCategory(prduct.getCategory());
        return  productrepositoy.save(prduct);
    }
    public ResponseEntity<String> deletedetails(long id) {
       try {
           productrepositoy.deleteById(id);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

       }
    }

    public List<Prduct> findAllByCategoryName(String name) {
        if (!name.equals("ALL")) {
            return productrepositoy.findByCategory_Name(name);
        } else {
            return productrepositoy.findAll();
        }
    }
    public Prduct getonedata(Long id) {
        Prduct prduct1 = productrepositoy.findById(id).get();
        return prduct1;
    }


}
