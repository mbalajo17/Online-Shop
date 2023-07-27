package com.symbiance.onlinestore.repository;


import com.symbiance.onlinestore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Orderrepository extends JpaRepository<Order,Long> {

    List<Order> findByUserId(Long id);
}
