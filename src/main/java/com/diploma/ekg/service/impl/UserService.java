package com.diploma.ekg.service.impl;

import com.diploma.ekg.dto.UserDTO;
import com.diploma.ekg.entity.CodeForUserValidation;
import com.diploma.ekg.entity.User;
import com.diploma.ekg.repository.CodeForUserValidationRepository;
import com.diploma.ekg.repository.UserRepository;
import com.diploma.ekg.service.IUserService;
import com.diploma.ekg.utils.exceptions.CustomException;
import com.diploma.ekg.utils.exceptions.MissingObjectException;
import com.diploma.ekg.utils.exceptions.OutDatedCodeException;
import com.diploma.ekg.utils.exceptions.WrongCodeException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final EmailSenderService emailSenderService;

    private final UserRepository userRepository;

    private final CodeForUserValidationRepository userValidationRepository;

    @Autowired
    public UserService(EmailSenderService emailSenderService,
                       UserRepository userRepository,
                       CodeForUserValidationRepository userValidationRepository) {
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
        this.userValidationRepository = userValidationRepository;
    }

    @Override
    @Transactional
    public Optional<User> findUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Integer save(UserDTO userDTO) {
        User save = userRepository.save(userDTO.toEntity());
        CodeForUserValidation newCode = createCode(save);
        userValidationRepository.save(newCode);
        sendEmailWithConfirmationCode(newCode);
        return save.getId();
    }

    @Override
    @Transactional
    public void sendValidationCode(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            userValidationRepository.removeCodeForUserValidationByEmail(email);
            CodeForUserValidation newCode = createCode(user);
            userValidationRepository.save(newCode);
            sendEmailWithConfirmationCode(newCode);
        }
    }

    @Override
    @Transactional
    public boolean activateUser(String email, String code) throws CustomException {
        CodeForUserValidation codeFromRepo = userValidationRepository.findCodeForUserValidationByEmail(email)
                .orElseThrow(() -> new MissingObjectException("no_code"));
        validateCode(code, codeFromRepo);
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            user.setActive(true);
            userRepository.save(user);
        }
        return true;
    }

    @Override
    public void resetPasswordCode(String email, String code, String newPassword) throws CustomException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new MissingObjectException("no_user"));
        CodeForUserValidation codeFromRepo = userValidationRepository.findCodeForUserValidationByEmail(user.getEmail())
                .orElseThrow(() -> new MissingObjectException("no_code"));
        validateCode(code, codeFromRepo);
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public void sendResetPasswordCode(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(IllegalAccessError::new);
        userValidationRepository.findCodeForUserValidationByEmail(user.getEmail()).ifPresent(userValidationRepository::delete);
        CodeForUserValidation newCode = createCode(user);
        userValidationRepository.save(newCode);
        sendEmailWithConfirmationCode(newCode);
    }

    @Override
    public List<String> loginErrors(String email) {
        List<String> errors = new ArrayList<>();
        User user = findUser(email).orElse(null);
        if (user == null) {
            errors.add("not_found");
        } else if (!user.isActive()) {
            errors.add("unactive");
        }
        return errors;
    }

    private void validateCode(String code, CodeForUserValidation codeFromRepo) throws OutDatedCodeException, WrongCodeException {
        if (!codeFromRepo.getCode().equals(code)) {
            throw new WrongCodeException();
        } else if (codeFromRepo.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new OutDatedCodeException();
        }
    }

    private CodeForUserValidation createCode(User user) {
        CodeForUserValidation newCode = new CodeForUserValidation();
        newCode.setCode(RandomStringUtils.randomNumeric(6));
        newCode.setEmail(user.getEmail());
        newCode.setValidUntil(LocalDateTime.now().plusMinutes(5));
        return newCode;
    }

    private void sendEmailWithConfirmationCode(CodeForUserValidation newCode) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newCode.getEmail());
        mailMessage.setSubject("Reset password code");
        mailMessage.setText(
                String.format("To reset your password enter next code into application: %s", newCode.getCode())
        );
        emailSenderService.sendEmail(mailMessage);
    }
}
