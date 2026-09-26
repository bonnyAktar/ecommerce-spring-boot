package com.example.ecommerce.controller;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.ErrorResponse;
import com.example.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

private final CategoryService categoryService;
public CategoryController(CategoryService categoryService){
    this.categoryService=categoryService;
}
@GetMapping
    public List<Category> getAllCategories(){
    return categoryService.getAllCategory();
}

@PostMapping
    public Category createcategories(@Valid @RequestBody Category category){
    return categoryService.Createcategory(category);
}

@GetMapping("/{id}")
    public ResponseEntity<?> getcategorybyid(@PathVariable Integer id){

        Category category = categoryService.getCategoryById(id).orElse(null);

        if (category == null){
            return ResponseEntity.status(404).body(new ErrorResponse("Category not found."));
        }
        return ResponseEntity.ok(category);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<?> deletecategory(@PathVariable Integer id){
    boolean deleted = categoryService.deleteCategory(id);
    if(!deleted){
        return ResponseEntity.status(404).body(new ErrorResponse("category not found"));
    }
    return ResponseEntity.ok("category deleted successfully");
}

@PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody Category category) {

        Optional<Category> updatedCategory = categoryService.updateCategory(id, category);

        if (updatedCategory.isEmpty()) {
            return ResponseEntity.status(404).body(new ErrorResponse("Category not found."));
        }
        return ResponseEntity.ok(updatedCategory.get());
    }


}
