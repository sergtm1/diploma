package com.diploma.ekg.service;

import com.diploma.ekg.dto.UserDTO;
import com.diploma.ekg.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> findUser(String email);

    Integer save(UserDTO user);

    boolean activateUser(String email, String code);

    void sendValidationCode(String email);

    void resetPasswordCode(String email, String code, String newPassword);

    void sendResetPasswordCode(String email);

    List<String> loginErrors(String email);
}
