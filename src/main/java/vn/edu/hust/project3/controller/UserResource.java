package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.project3.dto.UserDTO;
import vn.edu.hust.project3.model.Bill;
import vn.edu.hust.project3.model.User;
import vn.edu.hust.project3.repository.BillRepository;
import vn.edu.hust.project3.repository.RoleRepository;
import vn.edu.hust.project3.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserResource {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BillRepository billRepository;

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
        return "myAccount";
    }

    @GetMapping("/user/my-order/{pageNum}")
    public String myOrder(Model model,
                          @PathVariable(name = "pageNum") int pageNum,
                          @RequestParam(defaultValue = "3") int pageSize,
                          @RequestParam("userId") int userId
    ){
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Bill> billPage = billRepository.findByUserId(userId, pageable);
        List<Bill> bills = billPage.getContent();
        model.addAttribute("bills", bills);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        return "user";
    }

    @GetMapping("/user/change-password")
    public String changePassword() {
        return "ChangePassword";
    }

    @PostMapping("/user/change-password")
    public String changePassword(Model model, Principal principal, HttpServletRequest request) {
        User personal = userRepository.findAccountByUsername(principal.getName()).get();
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!personal.getPassword().equals(currentPassword)){
            model.addAttribute("message", "The password has just been entered incorrectly");
        } else if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("message", "The entered passwords do not match. Try again.");
        } else {
            personal.setPassword(newPassword);
            userRepository.save(personal);
            model.addAttribute("messageSuccess", "Change Password Successfully!");
        }

        return "ChangePassword";
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
