package com.diploma.ekg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping(path = "/login")
    @ResponseBody
    public String login() {
        return "login";
    }
}
