package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.hust.project3.model.Category;
import vn.edu.hust.project3.model.Phone;
import vn.edu.hust.project3.service.CategoryService;
import vn.edu.hust.project3.service.PhoneService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhoneResource {

    private final PhoneService phoneService;
    private final CategoryService categoryService;


    @GetMapping("/detail-phone")
    String DetailPhone(Model model){

        return "single";
    }

    @GetMapping("/all-phone")
    String AllPhone(Model model){
        List<Phone> phones = phoneService.getAllPhone();
        List<Category> categories = categoryService.getAllCategory();
        List<Phone> newestPhone = phoneService.getNewestPhone();
        model.addAttribute("categories", categories);
        model.addAttribute("phones", phones);
        model.addAttribute("newestPhone", newestPhone);
        model.addAttribute("numberPhone", phones.size());

        return "products";
    }
    @GetMapping(value = "/category")
    public String Category(Model model, @RequestParam(name = "id") Integer id) {

        List<Phone> phones = phoneService.getPhoneByCategory(id);
        List<Category> categories = categoryService.getAllCategory();
        List<Phone> newestPhone = phoneService.getNewestPhone();

        model.addAttribute("phones", phones);
        model.addAttribute("categories", categories);
        model.addAttribute("newestPhone", newestPhone);
        model.addAttribute("numberPhone", phones.size());
        return "products";
    }

    @GetMapping(value = "/phones")
    public String Phones(Model model,
                         @RequestParam(name = "minPrice") String price1,
                         @RequestParam(name = "maxPrice") String price2) {
        List<Phone> phones = phoneService.getPhoneByPrice(Double.parseDouble(price1), Double.parseDouble(price2));
        List<Category> categories = categoryService.getAllCategory();
        List<Phone> newestPhone = phoneService.getNewestPhone();
        model.addAttribute("phones", phones);
        model.addAttribute("categories", categories);
        model.addAttribute("newestPhone", newestPhone);
        return "products";
    }
}
