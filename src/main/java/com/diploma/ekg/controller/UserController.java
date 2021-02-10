package com.diploma.ekg.controller;

import com.diploma.ekg.dto.EmailDTO;
import com.diploma.ekg.dto.UserDTO;
import com.diploma.ekg.request.ResetPasswordRequest;
import com.diploma.ekg.service.IUserService;
import com.diploma.ekg.utils.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        IUserService.sendResetPasswordCode(email.toDomain());
    }

    @PostMapping(path = "/resetPassword")
    @ResponseBody
    public void resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            request.validateFields();
            IUserService.resetPasswordCode(request.email, request.code, request.newPassword);
        } catch (CustomException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping(path = "/activateUser")
    @ResponseBody
    public boolean activateUser(@RequestParam String email, @RequestParam String code) {
        try {
            return IUserService.activateUser(email, code);
        } catch (CustomException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping(path = "/sendValidationCode")
    @ResponseBody
    public void sendValidationCode(@RequestBody EmailDTO email) {
        IUserService.sendValidationCode(email.email);
    }
}
