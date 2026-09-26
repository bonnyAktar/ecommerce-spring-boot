package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.model.ErrorResponse;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public Page<Product> getAllproducts(Pageable pageable){
        return productService.getAllproducts(pageable);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getproductById(@PathVariable Integer id){
        Product product= productService.getproductById(id).orElse(null);

                if (product==null){

                    return ResponseEntity.status(404).body(new ErrorResponse("Product not found."));
                }

                return ResponseEntity.ok(product);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getproductsBycategory(@PathVariable String category){
        return productService.getproductsBycategory(category);
    }

    @GetMapping("/products/price/{price}")
    public List<Product> getproductsByPriceGreaterThan(@PathVariable double price){
        return productService.getproductsByPriceGreaterThan(price);
    }

    @PostMapping("/products")
    public Product CreateProduct(@Valid @RequestBody ProductRequest productRequestDTO){

        return productService.Createproduct(productRequestDTO);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateproduct(@PathVariable Integer id,@RequestBody Product product){
        Optional<Product> updateproduct=productService.updateproduct(id, product);
        if (updateproduct.isEmpty()){
            return ResponseEntity.status(404).body(new ErrorResponse("Product not found."));
        }
        return ResponseEntity.ok(updateproduct.get());
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteproduct(@PathVariable Integer id){
     boolean deleted  = productService.deleteproduct(id);
     if (!deleted){
         return ResponseEntity.status(404).body(new ErrorResponse("Product not found"));
     }
     return ResponseEntity.ok("Product deleted successfully.");
    }
}
