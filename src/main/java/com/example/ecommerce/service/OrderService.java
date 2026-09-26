package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    //create order
    public Order createorder(OrderRequest request){
       User user= userRepository.findById(request.getUserid()).orElse(null);

       if (user==null){
           return null;
       }
       Order order=new Order();

       order.setUser(user);
       order.setTotalPrice(0);
       order.setStatus("Pending");
       return orderRepository.save(order);
    }

//get all orders
    public List<Order> getallorders(){
         return orderRepository.findAll();
    }

//get order by id
    public Optional<Order> getorderbyid(Integer id){
        return orderRepository.findById(id);
    }

//update order status
    public Optional<Order> updateorderstatus(Integer id,String status){
        return orderRepository.findById(id).map(order->{
            order.setStatus(status);
            return orderRepository.save(order);
        });
    }

//delete order
public boolean deleteorder(Integer id){
    if (orderRepository.existsById(id)){
        orderRepository.deleteById(id);
        return true;
    }
    return false;
     }

 }
