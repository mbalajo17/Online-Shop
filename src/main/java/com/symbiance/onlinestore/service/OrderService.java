package com.symbiance.onlinestore.service;

import com.symbiance.onlinestore.model.Category;
import com.symbiance.onlinestore.model.Order;
import com.symbiance.onlinestore.model.Prduct;
import com.symbiance.onlinestore.model.User;
import com.symbiance.onlinestore.repository.Orderrepository;
import com.symbiance.onlinestore.repository.Productrepositoy;
import com.symbiance.onlinestore.repository.Userrepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {

    @Autowired
    private Orderrepository orderrepository;
    @Autowired
    private Productrepositoy productrepositoy;

    @Autowired
    private Userrepository userrepository;

    public String createOrder(Order order) {
        Optional<Prduct> productOpt = productrepositoy.findById(order.getPrducts().getId());
        if (productOpt.isPresent()) {
            Prduct product = productOpt.get();
            Optional<User> userOpt = userrepository.findById(order.getUser().getId());
            if (userOpt.isPresent()) {
                Order orderObject = new Order();
                orderObject.setOrdername(order.getOrdername());
                orderObject.setAddress(order.getAddress());
                orderObject.setPhonenumber(order.getPhonenumber());
                float total = product.getPrice() * order.getQuentity();
                orderObject.setTotal(total);
                orderObject.setQuentity(order.getQuentity());

                // Call the dateFormat method to set orderDate and deliveryDate
                dateFormate(orderObject, product.getCategory());

                orderObject.setPrducts(product);
                orderObject.setUser(userOpt.get());
                orderrepository.save(orderObject);
                return "successfully";
            }
        }
        return "failed";
    }

    private void dateFormate(Order orderObject, Category category) {
        LocalDate orderDate = LocalDate.now();
        orderObject.setOrderDate(orderDate);

        if (category != null) {
            String categoryName = category.getName();
            if (isCheckedElectronic(categoryName)) {
                LocalDate deliveryDate = orderDate.plusDays(7);
                orderObject.setDeliveryDate(deliveryDate);
            } else if (isCheckedTShirt(categoryName)) {
                LocalDate deliveryDate = orderDate.plusDays(4);
                orderObject.setDeliveryDate(deliveryDate);
            } else {
                LocalDate deliveryDate = orderDate.plusDays(8);
                orderObject.setDeliveryDate(deliveryDate);
            }
        }
    }

    private boolean isCheckedTShirt(String categoryName) {
       return StringUtils.containsIgnoreCase(CatagoryConstants.T_Shirt.toString(),categoryName);
    }

    private boolean isCheckedElectronic(String categoryName) {
        return StringUtils.containsIgnoreCase(CatagoryConstants.Electronics.toString(),categoryName);
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

//    public void stream(List<Order> order){
//        order.stream().filter(x->x.)
//    }


    public void deleteOrder(Long id) {
        CompletableFuture.supplyAsync(()->{
            orderrepository.deleteById(id);
            return "not";
        });
    }
}
