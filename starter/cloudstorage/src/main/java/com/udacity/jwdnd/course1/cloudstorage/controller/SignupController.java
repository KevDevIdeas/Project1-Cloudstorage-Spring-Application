package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignUpPage(Model model) {
        return "signup";
    }

    @PostMapping
    public String postSignup(User user, Model model) {
        if (userService.createUser(user)) {
            return "redirect:/login?userCreatedStatus=true";
        } else {
            model.addAttribute("userCreatedStatus", "failure");
            return "signup";
        }
    }
}

