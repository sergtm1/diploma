package com.diploma.ekg.service;

import com.diploma.ekg.dto.UserDTO;
import com.diploma.ekg.entity.User;
import com.diploma.ekg.utils.exceptions.CustomException;
import com.diploma.ekg.utils.exceptions.MissingObjectException;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> findUser(String email);

    Integer save(UserDTO user);

    boolean activateUser(String email, String code) throws CustomException;

    void sendValidationCode(String email);

    void resetPasswordCode(String email, String code, String newPassword) throws CustomException;

    void sendResetPasswordCode(String email);

    List<String> loginErrors(String email);

    User getUser(String username) throws MissingObjectException;
}
