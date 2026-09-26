package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderItemRequest;
import com.example.ecommerce.model.ErrorResponse;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    @PostMapping
    public OrderItem createorderitem(@RequestBody OrderItemRequest request){
        return orderItemService.createorderitem(request);
    }
    @GetMapping
    public List<OrderItem> getallorderitems(){
        return orderItemService.getallorderitems();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getorderitembyid(@PathVariable Integer id){
        Optional<OrderItem> orderItem=orderItemService.getorderitembyid(id);
        if (orderItem.isEmpty()){
            return ResponseEntity.status(404).body(new ErrorResponse("orderitem not found."));
        }
        return ResponseEntity.ok(orderItem.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateorderitem(@PathVariable Integer id,@RequestBody OrderItemRequest request){
        Optional<OrderItem> orderItem=orderItemService.updateorderitem(id,request);
        if (orderItem.isEmpty()){
            return ResponseEntity.status(404).body(new ErrorResponse("orderitem not found"));
        }
        return ResponseEntity.ok(orderItem.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteorderitem(@PathVariable Integer id){
        boolean deleted = orderItemService.deleteorderitem(id);
        if(!deleted){
            return ResponseEntity.status(404).body(new ErrorResponse("orderitem not found"));
        }
        return ResponseEntity.ok("orderitem deleted successfully");
    }
}
