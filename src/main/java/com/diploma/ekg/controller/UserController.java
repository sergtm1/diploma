package com.diploma.ekg.controller;

import com.diploma.ekg.dto.UserDTO;
import com.diploma.ekg.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final IUserService IUserService;

    @Autowired
    public UserController(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @PostMapping(path = "/save")
    @ResponseBody
    public Integer save(@RequestBody UserDTO user) {
        return IUserService.save(user);
    }

    @GetMapping(path = "/get")
    @ResponseBody
    public Integer getUserIdByEmail(String email) {
        return IUserService.getUserIdByEmail(email);
    }

    @PostMapping(path = "/{userId}/resetPassword")
    public void resetPassword(@PathVariable Integer userId) {
        IUserService.getResetPasswordCode(userId);
    }

    //todo: create separate controller and probably object for email
    @GetMapping(path = "/validateCode")
    @ResponseBody
    public boolean validateCode(@RequestParam String email, String code) {
        return IUserService.validateCode(email, code);
    }

    @GetMapping(path = "/activateUser")
    @ResponseBody
    public boolean activateUser(@RequestParam String email, String code) {
        return IUserService.activateUser(email, code);
    }
}
