package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.model.ErrorResponse;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createorder(@RequestBody OrderRequest request){

        return orderService.createorder(request);
    }
    @GetMapping
    public List<Order> getallorders(){
        return orderService.getallorders();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getorderbyid(@PathVariable Integer id){
        Optional<Order> order=orderService.getorderbyid(id);
        if (order.isEmpty()){
            return ResponseEntity.status(404).body(new ErrorResponse("order not found."));
        }
        return ResponseEntity.ok(order.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateorderstatus(@PathVariable Integer id,@RequestBody OrderRequest request){
        Optional<Order> order=orderService.updateorderstatus(id,request.getStatus());
        if (order.isEmpty()){
            return ResponseEntity.status(404).body(new ErrorResponse("order not found"));
        }
        return ResponseEntity.ok(order.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteorder(@PathVariable Integer id){
        boolean deleted = orderService.deleteorder(id);
        if(!deleted){
            return ResponseEntity.status(404).body(new ErrorResponse("order not found"));
        }
        return ResponseEntity.ok("order deleted successfully");
    }
}
