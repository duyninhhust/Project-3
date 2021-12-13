package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.hust.project3.model.Category;
import vn.edu.hust.project3.model.Phone;
import vn.edu.hust.project3.service.CategoryService;
import vn.edu.hust.project3.service.PhoneService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DetailPhoneResource {

    private final PhoneService phoneService;

    private final CategoryService categoryService;

    @GetMapping("/all-phone/{id}")
    String getDetailPhoneById(@PathVariable(value = "id") Integer id, Model model){
        Phone phone = phoneService.getPhoneById(id);
        List<Category> categories = categoryService.getAllCategory();
        List<Phone> newestPhone = phoneService.getNewestPhone();

        model.addAttribute("categories", categories);
        model.addAttribute("phone", phone);
        model.addAttribute("newestPhone", newestPhone);

        return "single";
    }
}
