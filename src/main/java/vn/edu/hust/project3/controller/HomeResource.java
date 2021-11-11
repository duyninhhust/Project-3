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
        Phone phone = new Phone();
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("phone", phone);
        model.addAttribute("categories", categories);
        return "Add";
    }

    @PostMapping("/savePhone")
    RedirectView savePhone(@ModelAttribute Phone phone, Model model, RedirectAttributes redirectAttributes){
        RedirectView redirectView;
        String alert = "";
        if(phone.getId() != 0){
            Phone updatedPhone = phoneService.updatePhone(phone.getId(), phone);
            alert = "Chỉnh sửa thông tin sản phẩm thành công";
            redirectView = new RedirectView("/manage" , true);
        } else {
            Phone newPhone = phoneService.createPhone(phone);
            alert = "Thêm mới sản phẩm thành công";
            redirectView = new RedirectView("/manage", true);
        }
        redirectAttributes.addFlashAttribute("alert", alert);
        return redirectView;
    }


    @DeleteMapping("/deletePhone")
    @ResponseBody
    void deletePhone(@RequestParam("phoneIds[]") String[] phoneIds){
        for (String id : phoneIds) {
            phoneService.deletePhone(Integer.parseInt(id));
        }
    }
}
