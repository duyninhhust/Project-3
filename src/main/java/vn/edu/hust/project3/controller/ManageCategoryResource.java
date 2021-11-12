package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.hust.project3.model.Category;
import vn.edu.hust.project3.service.CategoryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/admin/")
public class ManageCategoryResource {

    private final CategoryService categoryService;

    @GetMapping(value = "/manage-category")
    public String ManagerCategory(Model model){
        int pageId = 3;
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("pageId", pageId);
        return "ManagerCategory";
    }

    @GetMapping(value = "/manage-category/add")
    public String addCategory(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        return "AddCategory";
    }

    @PostMapping(value = "/manage-category/add")
    public String addCategory(@ModelAttribute Category category){
        categoryService.createCategory(category);
        return "redirect:/manage-category";
    }

    @GetMapping(value = "/manage-category/edit")
    public String editCategory(@RequestParam(value = "id") Integer id,
                               Model model){
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("cate", category);
        return "EditCategory";
    }

    @PostMapping(value = "/manage-category/edit")
    public String editCategory(@ModelAttribute Category category){
        categoryService.updateCategory(category.getId(), category);
        return "redirect:/manage-category";
    }
    @GetMapping(value = "/manage-category/delete")
    public String deleteCategory(@RequestParam(value = "id") Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/manage-category";
    }

}
