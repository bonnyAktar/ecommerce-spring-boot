package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartItemRequest;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<CartItem> getAllCartItem(){
        return cartItemRepository.findAll();

    }

    public CartItem CreateCartItem(CartItemRequest Request){
       Cart cart= cartRepository.findById(Request.getCartid()).orElse(null);
       Product product= productRepository.findById(Request.getProductid()).orElse(null);
       if (cart==null||product==null){
           return null;
       }
       CartItem cartItem=new CartItem();
       cartItem.setCart(cart);
       cartItem.setProduct(product);
       cartItem.setQuantity(Request.getQuantity());
      return cartItemRepository.save(cartItem);
    }

    public Optional<CartItem> getCartItemyById(Integer id){

        return cartItemRepository.findById(id);
    }

    public Optional<CartItem> updatecartItem(Integer id,CartItemRequest request) {
        return cartItemRepository.findById(id).map(existingCartItem -> {
            Product product=productRepository.findById(request.getProductid()).orElse(null);
            
            if (product==null){
                return null;
            }
            existingCartItem.setProduct(product);

            existingCartItem.setQuantity(request.getQuantity());

            return cartItemRepository.save(existingCartItem);
        });
    }

    public boolean deleteCartItem(Integer id){
        if (cartItemRepository.existsById(id)){
            cartItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
