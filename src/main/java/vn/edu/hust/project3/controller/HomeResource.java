package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    String Home(Model model){
        List<Phone> featuredPhone = phoneService.getFeaturedPhone();
        List<Phone> mostQuantityPhone = phoneService.getMostQuantityPhone();
        List<Phone> newestPhone = phoneService.getNewestPhone();
        model.addAttribute("featuredPhones", featuredPhone);
        model.addAttribute("mostQuantityPhones", mostQuantityPhone);
        model.addAttribute("newestPhones", newestPhone);
        return "index";
    }


}
