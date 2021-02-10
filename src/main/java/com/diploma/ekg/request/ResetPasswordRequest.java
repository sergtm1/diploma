package com.diploma.ekg.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

public class ResetPasswordRequest {

    private final String regex = "^(.+)@(.+)$";

    public String email;

    public String code;

    public String newPassword;

    public void validateFields() {
        if (!Pattern.compile(regex).matcher(email).matches()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Passed email has wrong format. email: %s", email));
        }
        if (code == null || code.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Code cab't be empty");
        }
        if (newPassword == null || newPassword.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "New password can't be empty");
        }
    }
}
