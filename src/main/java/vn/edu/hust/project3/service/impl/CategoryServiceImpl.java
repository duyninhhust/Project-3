package vn.edu.hust.project3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.hust.project3.exception.CategoryNotFoundException;
import vn.edu.hust.project3.model.Category;
import vn.edu.hust.project3.repository.CategoryRepository;
import vn.edu.hust.project3.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int id, Category category) {
        Category category1 = findCategoryById(id);
        category1.setName(category.getName());
        return categoryRepository.save(category1);
    }

    @Override
    public void deleteCategory(int id) {
        Category category = findCategoryById(id);
        categoryRepository.delete(category);
    }

    @Override
    public Category getCategoryById(int id) {
        return findCategoryById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllCategoryByIds(List<Integer> ids) {
        return categoryRepository.findByIdIn(ids);
    }

    private Category findCategoryById(int id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (!optional.isPresent()) {
            throw new CategoryNotFoundException(String.format("Could not found category with ID= [%s]", id));
        }
        return optional.get();
    }
}
