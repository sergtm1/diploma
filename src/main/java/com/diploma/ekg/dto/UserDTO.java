package com.diploma.ekg.dto;

import com.diploma.ekg.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

public class UserDTO {

    private final String regex = "^(.+)@(.+)$";

    public String email;

    public String password;

    public User toEntity() {
        validateFields();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setActive(false);
        return user;
    }

    private void validateFields() {
        if (!Pattern.compile(regex).matcher(email).matches()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Passed email has wrong format. email: %s", email));
        }
        if (password == null || password.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Password can't be empty");
        }
    }
}
