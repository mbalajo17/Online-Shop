package com.symbiance.onlinestore.service;

import com.symbiance.onlinestore.model.Category;
import com.symbiance.onlinestore.model.Order;
import com.symbiance.onlinestore.model.Prduct;
import com.symbiance.onlinestore.model.User;
import com.symbiance.onlinestore.repository.Orderrepository;
import com.symbiance.onlinestore.repository.Productrepositoy;
import com.symbiance.onlinestore.repository.Userrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private Orderrepository orderrepository;
    @Autowired
    private Productrepositoy productrepositoy;

    @Autowired
    private Userrepository userrepository;

    public String createorder(Order order) {
        Optional<Prduct> prductOpt = productrepositoy.findById(order.getPrducts().getId());
        if(prductOpt.isPresent()){
            Prduct prduct = prductOpt.get();
            Optional<User> user = userrepository.findById(order.getUser().getId());
            if(user.isPresent()){
                Order orderObject = new Order();
                orderObject.setOrdername(order.getOrdername());
                orderObject.setAddress(order.getAddress());
                orderObject.setPhonenumber(order.getPhonenumber());
                float total = prduct.getPrice()* order.getQuentity();
                orderObject.setTotal(total);
                orderObject.setQuentity(order.getQuentity());
                orderObject.setDate(order.getDate());
                orderObject.setPrducts(prduct);
                orderObject.setUser(user.get());
                orderrepository.save(orderObject);
            }
        }
        return "successfully";
    }

    public List<Order> getallorder() {
        List<Order> list=new ArrayList<>();
        orderrepository.findAll().forEach(list::add);
        return list;
    }

    public Order getonedata(Long id) {
        Order order= orderrepository.findById(id).get();
        return order;
    }

    public List<Order> getorderbyuser(Long id) {
      List<Order> orders= orderrepository.findByUserId(id);
      return orders;

    }


    public String deleteOrder(Long id) {
        if (orderrepository.existsById(id)){
            orderrepository.deleteById(id);
            return id+"deleted successfully";
        }else {
            return "not success";
        }
    }
}
