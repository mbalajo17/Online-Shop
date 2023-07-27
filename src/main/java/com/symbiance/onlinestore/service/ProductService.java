package com.symbiance.onlinestore.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.symbiance.onlinestore.model.Prduct;
import com.symbiance.onlinestore.repository.Productrepositoy;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Prduct> list = new ArrayList<>();
        productrepositoy.findAll().forEach(list::add);
        return list;
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
    public String deletedetails(long id) {
        if (productrepositoy.existsById(id)) {
            productrepositoy.deleteById(id);
            return id + "delete successfully";
        } else {
            return "not exits";
        }
    }

    public List<Prduct> findAllByCategoryName(String name) {
        return productrepositoy.findByCategory_Name(name);
    }
    public Prduct getonedata(Long id) {
        Prduct prduct1 = productrepositoy.findById(id).get();
        return prduct1;
    }


}
