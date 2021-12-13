package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.project3.model.Category;
import vn.edu.hust.project3.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> fetchCategoryList(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/categories/{id}")
    public Category fetchCategoryById(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category){
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return "Delete Category Completed";
    }

}
