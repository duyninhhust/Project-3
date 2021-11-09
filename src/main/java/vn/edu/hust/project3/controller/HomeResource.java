package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.project3.model.Category;
import vn.edu.hust.project3.model.Phone;
import vn.edu.hust.project3.service.CategoryService;
import vn.edu.hust.project3.service.PhoneService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeResource {

    private final PhoneService phoneService;
    private final CategoryService categoryService;

    @GetMapping(value = {"/", "home"})
    String HomePage(Model model){

        List<Phone> phones = phoneService.getAllPhone();
        model.addAttribute("phones", phones);
        return "index";
    }

    @GetMapping("/manage")
    String managePhone(Model model){
        List<Phone> phones = phoneService.getAllPhone();
        model.addAttribute("phones", phones);
        return "ManagerProduct";
    }

    @GetMapping("/edit/{id}")
    String editPhone(@PathVariable int id, Model model){
        Phone phone = phoneService.getPhoneById(id);
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("phone", phone);
        model.addAttribute("categories", categories);
        return "Edit";
    }

    @GetMapping("/add")
    String addPhone(Model model){
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        return "Add";
    }



}
