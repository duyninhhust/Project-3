package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import vn.edu.hust.project3.model.Alert;
import vn.edu.hust.project3.model.Role;
import vn.edu.hust.project3.model.User;
import vn.edu.hust.project3.repository.RoleRepository;
import vn.edu.hust.project3.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ManageUserResource {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping("/manage-user/{pageNumber}")
    String manageUser(Model model, @PathVariable(name = "pageNumber") int pageNo,
                      @RequestParam(name = "sortField") String sortField,
                      @RequestParam(name = "sortDir") String sortDir) {
        int pageSize = 3;
        int pageId = 4;

        Page<User> userPage = userService.findUserPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> users = userPage.getContent();

        model.addAttribute("pageUser", users);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageId", pageId);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("totalItems", userPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "ManageUser";
    }

    @GetMapping("/edit-user/{id}")
    String editUser(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "EditUser";
    }

    @GetMapping("/add-user")
    String addUser(Model model) {
        User user = new User();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "AddUser";
    }

    @PostMapping("/save-user")
    RedirectView savePhone(@ModelAttribute User user,
                           RedirectAttributes redirectAttributes) {

        RedirectView redirectView = null;
        String alert = "";
        try {
            if (user.getId() != 0) {
                userService.updateUser(user.getId(), user);
                alert = "Insert user information successfully";
            } else {
                userService.createUser(user);
                alert = "Add user successfully";
            }
            redirectView = new RedirectView("/manage-user/1?sortField=id&sortDir=asc", true);
        } catch (Exception e) {
            alert = "Add/Insert user failed";
            redirectView = new RedirectView("/manage-user/1?sortField=id&sortDir=asc", true);

        }

        redirectAttributes.addFlashAttribute("alert", alert);
        return redirectView;
    }

    @DeleteMapping("/delete-one-user")
    @ResponseBody
    String deleteUser(@RequestParam(value = "id") Integer id) {
        String rs = "";
        try {
            userService.deleteUser(id);
            rs = Alert.SUCCESS;
        } catch (Exception e) {
            rs = Alert.ERROR;
        }
        return rs;
    }


    @DeleteMapping("/delete-user")
    @ResponseBody
    String deletePhones(@RequestParam("phoneIds[]") String[] phoneIds) {
        String rs = "";
        try {
            List<Integer> ids = Arrays.stream(phoneIds)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            userService.deleteUsers(ids);

            rs = Alert.SUCCESS;
        } catch (Exception e) {
            rs = Alert.ERROR;
        }
        return rs;
    }
}
