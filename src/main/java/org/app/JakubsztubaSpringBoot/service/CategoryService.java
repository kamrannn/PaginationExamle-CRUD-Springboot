package org.app.JakubsztubaSpringBoot.service;

import org.app.JakubsztubaSpringBoot.model.Category;
import org.app.JakubsztubaSpringBoot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Object> listAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    public ResponseEntity<Object> addCategory(Category category) {
        categoryRepository.save(category);
        return new ResponseEntity<>("Category Successfully added", HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            categoryRepository.delete(optionalCategory.get());
            return new ResponseEntity<>("Category Successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Category against this Id", HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> updateCategory(Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if (optionalCategory.isPresent()) {
            categoryRepository.save(category);
            return new ResponseEntity<>("Category Successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Category against this Id", HttpStatus.OK);
        }
    }
}
