package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage (
            @RequestParam(required = false, name = "logout") boolean logout,
            @RequestParam(required = false, name = "error") String error,
            @RequestParam(required = false, name = "userCreatedStatus") boolean userCreatedStatus,
            Model model) {

        //in case of failed login (e.g. user doesn't exist or wrong credentials)
        if (error == "") {
            model.addAttribute("loginSuccessStatus", "failure");
        }
        //logout successful
        if (logout) {
            model.addAttribute("logoutSuccessStatus", "success");
        }
        if (userCreatedStatus) {
            model.addAttribute("userCreatedStatus", "ok");
        }

        return "login";
    }

}