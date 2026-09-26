package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

        public Page<Product> getAllproducts(Pageable pageable){
            return productRepository.findAll(pageable);
        }

        public List<Product> getproductsBycategory(String category){
        return productRepository.findByCategory(category);
        }

        public List<Product> getproductsByPriceGreaterThan(double price){
        return productRepository.findByPriceGreaterThan(price);
        }

        public Optional<Product> getproductById(Integer id){
        return productRepository.findById(id);
        }

        public Product Createproduct(ProductRequest productRequestDTO){

        Product product=new Product();

        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setCategory(productRequestDTO.getCategory());
        product.setQuantity(productRequestDTO.getQuantity());

        return productRepository.save(product);

        }

        public Optional<Product> updateproduct(Integer id,Product product){
        return productRepository.findById(id).map(existingproduct->{
            existingproduct.setName(product.getName());
            existingproduct.setPrice(product.getPrice());
            existingproduct.setCategory(product.getCategory());
            existingproduct.setQuantity(product.getQuantity());

            return productRepository.save(existingproduct);
        });
        }

        public boolean deleteproduct(Integer id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
        }
}


