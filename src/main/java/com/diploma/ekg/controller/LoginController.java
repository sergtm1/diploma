package com.diploma.ekg.controller;

import com.diploma.ekg.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private IUserService userService;

    @GetMapping(path = "login")
    @ResponseBody
    public String login() {
        return "login";
    }

    @GetMapping(path = "loginErrors")
    @ResponseBody
    public List<String> loginErrors(String email) {
        return userService.loginErrors(email);
    }
}
