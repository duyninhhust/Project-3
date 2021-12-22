package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.hust.project3.model.Category;
import vn.edu.hust.project3.model.Phone;
import vn.edu.hust.project3.service.CategoryService;
import vn.edu.hust.project3.service.PhoneService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchResource {

    private final PhoneService phoneService;

    private final CategoryService categoryService;

    @GetMapping(value = "/search")
    String searchByName(HttpServletRequest request, Model model){
        String name = request.getParameter("q");
        List<Phone> phones = phoneService.searchPhone(name);
        List<Category> categories = categoryService.getAllCategory();
        List<Phone> newestPhone = phoneService.getNewestPhone();

        model.addAttribute("categories", categories);
        model.addAttribute("phones", phones);
        model.addAttribute("newestPhone", newestPhone);
        model.addAttribute("numberPhone", phones.size());
        return "products";
    }


}
