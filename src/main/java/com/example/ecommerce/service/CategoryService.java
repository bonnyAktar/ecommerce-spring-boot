package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

   public CategoryService(CategoryRepository categoryRepository){
       this.categoryRepository=categoryRepository;
   }

   public List<Category> getAllCategory(){
       return categoryRepository.findAll();

   }

   public Category Createcategory(Category category){

       return categoryRepository.save(category);
   }

    public Optional<Category> getCategoryById(Integer id){
        return categoryRepository.findById(id);
    }

    public Optional<Category> updateCategory(Integer id, Category category) {
        return categoryRepository.findById(id).map(existingCategory -> {

            existingCategory.setName(category.getName());

            return categoryRepository.save(existingCategory);
        });
    }

   public boolean deleteCategory(Integer id){
       if (categoryRepository.existsById(id)){
           categoryRepository.deleteById(id);
           return true;
       }
       return false;
   }


}
