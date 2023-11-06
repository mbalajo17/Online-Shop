package com.symbiance.onlinestore.controller;



import com.symbiance.onlinestore.model.Order;
import com.symbiance.onlinestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/addorder")
    public String addorder(@RequestBody Order order){
       return orderService.createOrder(order);
    }

    @GetMapping("/getAll")
    public List<Order> getall(){
        return orderService.getallorder();
    }

    @GetMapping("/order/{id}")
    public Order getone(@PathVariable Long id){
        return orderService.getonedata(id);
    }
    @GetMapping("/order/user/{id}")
    public  List<Order> getOneUser(@PathVariable Long id){
        return orderService.getorderbyuser(id);
    }
     @DeleteMapping("/deleteOrder/{id}")
    public void delete(@PathVariable Long id){

         orderService.deleteOrder(id);
    }


}
