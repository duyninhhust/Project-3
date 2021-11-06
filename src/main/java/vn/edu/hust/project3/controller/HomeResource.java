package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.hust.project3.model.Phone;
import vn.edu.hust.project3.service.PhoneService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeResource {

    private final PhoneService phoneService;

    @GetMapping(value = {"/", "home"})
    String HomePage(Model model){

        List<Phone> phones = phoneService.getAllPhone();
        model.addAttribute("phones", phones);
        return "index";
    }

    @GetMapping("/manage")
    String asdasd(){
        return "ManagerProduct";
    }

    @GetMapping("/manage1")
    String asddssdasd(){
        return "Add";
    }

    @GetMapping("/manage2")
    String asddssdassd(){
        return "Edit";
    }

}
