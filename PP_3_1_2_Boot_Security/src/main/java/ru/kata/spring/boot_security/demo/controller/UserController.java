package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/save")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        if (user.getId() != null) {
            model.addAttribute("user", userService.getUserById(user.getId()));
        }
        return "save";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "save";
        } else {
            if (user.getId() == null) {
                userService.saveUser(user);
            }
        }
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        if (user.getId() != null) {
            model.addAttribute("user", userService.getUserById(user.getId()));
        }
        return "update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update";
        } else {
            if (user.getId() != null) {
                userService.updateUser(user);
            }
        }
        return "redirect:/users";
    }

    @PostMapping("/del")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.removeUserById(user.getId());
        return "redirect:/users";
    }
}