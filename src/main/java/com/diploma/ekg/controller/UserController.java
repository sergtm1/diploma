package com.diploma.ekg.controller;

import com.diploma.ekg.dto.EmailDTO;
import com.diploma.ekg.dto.ResetPasswordRequest;
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

    @PutMapping(path = "/sendResetPasswordCode")
    @ResponseBody
    public void sendResetPasswordCode(@RequestBody EmailDTO email) {
        IUserService.sendResetPasswordCode(email.email);
    }

    @PostMapping(path = "/resetPassword")
    @ResponseBody
    public void resetPassword(@RequestBody ResetPasswordRequest email) {
        IUserService.resetPasswordCode(email.email, email.code, email.newPassword);
    }

    @GetMapping(path = "/activateUser")
    @ResponseBody
    public boolean activateUser(@RequestParam String email, @RequestParam String code) {
        return IUserService.activateUser(email, code);
    }

    @PutMapping(path = "/sendValidationCode")
    @ResponseBody
    public void sendValidationCode(@RequestBody EmailDTO email) {
        IUserService.sendValidationCode(email.email);
    }
}
