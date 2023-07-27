package com.symbiance.onlinestore.repository;

import com.symbiance.onlinestore.model.Prduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Productrepositoy extends JpaRepository<Prduct,Long> {
    List<Prduct> findAllByCategory_Id(int id);

    List<Prduct> findByCategory_Name(String name);



}
