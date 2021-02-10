package com.diploma.ekg.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

public class EmailDTO {

    private final String regex = "^(.+)@(.+)$";

    public String email;

    public String toDomain() {
        if (!Pattern.compile(regex).matcher(email).matches()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Passed email has wrong format. email: %s", email));
        }
        return email;
    }
}
