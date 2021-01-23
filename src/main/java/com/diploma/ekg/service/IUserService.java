package com.diploma.ekg.service;

import com.diploma.ekg.dto.UserDTO;
import com.diploma.ekg.entity.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> findUser(String email);

    Integer save(UserDTO user);

    boolean validateCode(String email, String code);

    boolean activateUser(String email, String code);

    Integer getUserIdByEmail(String email);

    void sendValidationCode(String email);

    void resetPasswordCode(String email, String code, String newPassword);

    void sendResetPasswordCode(String email);
}
