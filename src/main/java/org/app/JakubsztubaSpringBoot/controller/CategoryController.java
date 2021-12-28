package org.app.JakubsztubaSpringBoot.controller;

import org.app.JakubsztubaSpringBoot.model.Category;
import org.app.JakubsztubaSpringBoot.model.Category;
import org.app.JakubsztubaSpringBoot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listOfCategories() {
        return categoryService.listAllCategories();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }
}
