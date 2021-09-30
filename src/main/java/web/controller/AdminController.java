package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // form 'admin_panel'
    @GetMapping("/crud_user")
    public String GetUsers() {
        return "admin/crud_user";
    }

    // Add new User
    @PostMapping("/add_user")
    public String AddUser(@ModelAttribute("User") User user) {
        if ((user.getName() != null) || (user.getPassword() != null) || (user.getRoles() != null)) {
            userService.addUser(user);
        }
        return "admin/crud_user";
    }

    // Update User
    @GetMapping("/upd_user/{id}")
    public String EditUsers(Model model, @PathVariable("id") Long id) {
        model.addAttribute("EditingUser", userService.getUser(id));
        return "admin/upd_user";
    }

    @PatchMapping("/upd_user")
    public String UpdateUser(@ModelAttribute("EditingUser") User user) {
        userService.updUser(user);
        return "admin/crud_user";
    }

    // Delete User
    @DeleteMapping("/del_user/{id}")
    public String DeleteUser(@PathVariable("id") Long id) {
        userService.delUser(id);
        return "admin/crud_user";
    }

    @ModelAttribute("Users")
    public List<User> ListUsers() {
        return userService.getUsers();
    }

    @ModelAttribute("User")
    public User getNewUser() {
        return new User();
    }
}
