package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import vn.edu.hust.project3.model.Alert;
import vn.edu.hust.project3.model.Category;
import vn.edu.hust.project3.model.Phone;
import vn.edu.hust.project3.service.CategoryService;
import vn.edu.hust.project3.service.PhoneService;

import java.util.List;

@Controller
//@RequestMapping("/admin/")
@RequiredArgsConstructor
public class ManagePhoneResource {

    private final PhoneService phoneService;
    private final CategoryService categoryService;

    @GetMapping("/manage-phone/{pageNumber}")
    String managePhone(Model model, @PathVariable(name = "pageNumber") int pageNo,
                       @RequestParam(name = "sortField") String sortField,
                       @RequestParam(name = "sortDir") String sortDir){
        int pageSize = 3;
        int pageId = 1;

        Page<Phone> phonePage = phoneService.findPhonePaginated(pageNo, pageSize, sortField, sortDir);
        List<Phone> phones = phonePage.getContent();

        model.addAttribute("pageProduct", phones);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageId", pageId);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", phonePage.getTotalPages());
        model.addAttribute("totalItems", phonePage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "ManagerProduct";
    }

    @GetMapping("/edit-phone/{id}")
    String editPhone(@PathVariable int id, Model model){
        Phone phone = phoneService.getPhoneById(id);
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("phone", phone);
        model.addAttribute("categories", categories);
        return "Edit";
    }

    @GetMapping("/add-phone")
    String addPhone(Model model){
        Phone phone = new Phone();
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("phone", phone);
        model.addAttribute("categories", categories);
        return "Add";
    }

    @PostMapping("/save-phone")
    RedirectView savePhone(@ModelAttribute Phone phone, Model model, RedirectAttributes redirectAttributes){
        RedirectView redirectView;
        String alert = "";
        if(phone.getId() != 0){
            phoneService.updatePhone(phone.getId(), phone);
            alert = "Chỉnh sửa thông tin sản phẩm thành công";
            redirectView = new RedirectView("/manage-phone/1?sortField=id&sortDir=asc" , true);
        } else {
            phoneService.createPhone(phone);
            alert = "Thêm mới sản phẩm thành công";
            redirectView = new RedirectView("/manage-phone/1?sortField=id&sortDir=asc", true);
        }
        redirectAttributes.addFlashAttribute("alert", alert);
        return redirectView;
    }

    @DeleteMapping("/delete-one-phone")
    @ResponseBody
    String deletePhone(@RequestParam(value = "id") Integer id){
        String rs = "";
        try{
            phoneService.deletePhone(id);
            rs = Alert.SUCCESS;
        } catch (Exception e){
            rs = Alert.ERROR;
        }
        return rs;
    }


    @DeleteMapping("/delete-phone")
    @ResponseBody
    String deletePhones(@RequestParam("phoneIds[]") String[] phoneIds, RedirectAttributes redirectAttributes){
        String rs = "";
        try{
            for (String id : phoneIds) {
                phoneService.deletePhone(Integer.parseInt(id));
            }
            rs = Alert.SUCCESS;
        } catch (Exception e){
            rs = Alert.ERROR;
        }
        return rs;
    }
}
