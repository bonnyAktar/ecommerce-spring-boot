package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartItemRequest;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.ErrorResponse;
import com.example.ecommerce.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public List<CartItem> getallcartitem() {

        return cartItemService.getAllCartItem();
    }

    @PostMapping
    public CartItem createcartitem(@RequestBody CartItemRequest cartItemRequest) {

        return cartItemService.CreateCartItem(cartItemRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getcartitembyid(@PathVariable Integer id) {

        CartItem cartItem = cartItemService.getCartItemyById(id).orElse(null);

        if (cartItem == null) {

            return ResponseEntity.status(404).body(new ErrorResponse("CartItem not found"));
        }
        return ResponseEntity.ok(cartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuantity(@PathVariable Integer id, @RequestBody CartItemRequest request) {

        Optional<CartItem> updatecartitem = cartItemService.updatecartItem(id,request);

        if (updatecartitem.isEmpty()) {

            return ResponseEntity.status(404).body(new ErrorResponse("Cartitem not found"));
        }
        return ResponseEntity.ok(updatecartitem.get());
    }


 @DeleteMapping("/{id}")
 public ResponseEntity<?> deletecartitem(@PathVariable Integer id){

        boolean deleted = cartItemService.deleteCartItem(id);

        if (!deleted){
            return ResponseEntity.status(404).body(new ErrorResponse("Cartitem not found."));
        }
        return ResponseEntity.ok("Cartitem deleted successfully");
    }

}

