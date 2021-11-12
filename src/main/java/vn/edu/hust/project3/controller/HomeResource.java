package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
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

}
