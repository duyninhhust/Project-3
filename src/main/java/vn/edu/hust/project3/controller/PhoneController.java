package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.project3.model.Phone;
import vn.edu.hust.project3.service.PhoneService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService phoneService;

    @GetMapping("/products")
    public List<Phone> fetchPhoneList(){
        return phoneService.getAllPhone();
    }

    @GetMapping("/products/{id}")
    public Phone fetchPhoneById(@PathVariable int id){
        return phoneService.getPhoneById(id);
    }

    @PostMapping("/products")
    public Phone createPhone(@RequestBody Phone phone){
        return phoneService.createPhone(phone);
    }

    @PutMapping("/products/{id}")
    public Phone updatePhone(@PathVariable int id, @RequestBody Phone phone){
        return phoneService.updatePhone(id, phone);
    }

    @DeleteMapping("/products/{id}")
    public String deletePhone(@PathVariable int id){
        phoneService.deletePhone(id);
        return "Delete Category Completed";
    }
}
