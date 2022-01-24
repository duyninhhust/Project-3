package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hust.project3.dto.ContactMail;
import vn.edu.hust.project3.model.User;
import vn.edu.hust.project3.repository.UserRepository;
import vn.edu.hust.project3.service.MailService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MailResource {

    private final UserRepository userRepository;

    private final MailService mailService;


    @GetMapping("/contact-me")
    String contact(){
        return "contact";
    }

    @PostMapping("/contact-me")
    String sendContactMail(ContactMail mail){
        mailService.sentContactMail(mail);
        return "redirect:/";
    }

    @RequestMapping("/send-order-mail")
    String sendOrderMail(Principal principal){
        User user = userRepository.findUserByUsername(principal.getName());
        mailService.sendOrderMail(user);
        return "redirect:/";
    }

    @GetMapping("/contact-mes")
    String contacts(){
        return "OrderMail";
    }


}
