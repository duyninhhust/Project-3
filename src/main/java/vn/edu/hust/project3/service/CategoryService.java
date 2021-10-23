package vn.edu.hust.project3.service;

import org.springframework.stereotype.Component;
import vn.edu.hust.project3.model.Category;

import java.util.List;

@Component
public interface CategoryService {

    Category createCategory (Category category);
    Category updateCategory(int id, Category category);
    void deleteCategory(int id);
    Category getCategoryById(int id);
    List<Category> getAllCategory();
    List<Category> getAllCategoryByIds(List<Integer> ids);
}
