package com.diploma.ekg.dto;

import com.diploma.ekg.entity.User;

public class UserDTO {

    public String email;

    public String password;

    public User toEntity() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setActive(false);
        user.setAdminActive(false);
        return user;
    }
}
