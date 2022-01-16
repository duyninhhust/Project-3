package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.hust.project3.dto.UserDTO;
import vn.edu.hust.project3.model.User;
import vn.edu.hust.project3.repository.RoleRepository;
import vn.edu.hust.project3.repository.UserRepository;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserResource {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null){
            return "redirect:/";
        }
        return "Login";
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        User personal = userRepository.findAccountByUsername(principal.getName()).get();
        model.addAttribute("personal", personal);
        return "user";
    }
    @GetMapping("/signup")
    public String signUp(){

        return "register";
    }

    @PostMapping("/signup")
    public String registerPost(@ModelAttribute UserDTO userDTO, Model model){

        String password = userDTO.getPassword();
        String repass = userDTO.getMatchingPassword();
        System.out.println(userDTO);
        User user = userRepository.findUserByUsername(userDTO.getUsername());

        if (user == null){
            if (password.equals(repass)){
                user = new User(userDTO.getUsername(), password, userDTO.getEmail(), userDTO.getFirstName(), userDTO.getLastName(), roleRepository.getById(2));
                userRepository.save(user);
                System.out.println(user);
                model.addAttribute("message","Sign Up Successful");
            }
            else {
                model.addAttribute("message", "The entered passwords do not match. Try again.");
            }
        }else {
            model.addAttribute("message","Account already exists");
        }

        return "register";
    }
}
