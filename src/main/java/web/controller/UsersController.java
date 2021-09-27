package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String GetUsers() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/edituser/{id}")
    public String EditUsers(Model model, @PathVariable("id") Long id) {
        model.addAttribute("EditingUser", userService.getUser(id));
        return "edituser";
    }

    @PostMapping("/users")
    public String AddUser(@ModelAttribute("User") User user) {
        if((user.getName() != null) || (user.getPassword() != null) || (user.getRoles() != null)) {
            userService.addUser(user);
        }
        return "redirect:/users";
    }

    @PatchMapping("/edituser")
    public String UpdateUser(@ModelAttribute("EditingUser") User user) {
        userService.updUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String DeleteUser(@PathVariable("id") Long id) {
        userService.delUser(id);
        return "redirect:/users";
    }

    @ModelAttribute("Users")
    public  List<User>  ListUsers() {
        return userService.getUsers();
    }

    @ModelAttribute("User")
    public  User  getNewUser() {
        return new User();
    }
}
