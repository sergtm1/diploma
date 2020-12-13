package com.diploma.ekg.service.impl;

import com.diploma.ekg.dto.UserDTO;
import com.diploma.ekg.entity.CodeForUserValidationCode;
import com.diploma.ekg.entity.User;
import com.diploma.ekg.repository.CodeForUserValidationRepository;
import com.diploma.ekg.repository.UserRepository;
import com.diploma.ekg.service.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public Optional<User> findUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void getResetPasswordCode(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        userValidationRepository.findCodeForUserValidationByEmail(user.getEmail()).ifPresent(userValidationRepository::delete);
        //todo: generate indeed valid code or check email during validation(other possibility is to validate email during reset process)
        CodeForUserValidationCode newCode = createCode(user);
        userValidationRepository.save(newCode);
        sendEmailWithConfirmationCode(newCode);
    }

    @Override
    @Transactional
    public Integer save(UserDTO userDTO) {
        User save = userRepository.save(userDTO.toEntity());
        CodeForUserValidationCode newCode = createCode(save);
        userValidationRepository.save(newCode);
        sendEmailWithConfirmationCode(newCode);
        return save.getId();
    }

    @Override
    public boolean validateCode(String email, String code) {
        Optional<CodeForUserValidationCode> codeFromRepo
                = userValidationRepository.findCodeForUserValidationByEmail(email);
        return codeFromRepo
                .map(validationCode -> validationCode.getCode().equals(code))
                .orElse(false);
    }

    @Override
    public boolean activateUser(String email, String code) {
        CodeForUserValidationCode codeFromRepo = userValidationRepository.findCodeForUserValidationByEmail(email).orElse(null);
        boolean isValid = false;
        if (codeFromRepo != null) {
            isValid = codeFromRepo.getCode().equals(code);
        }
        if (isValid) {
            User user = userRepository.findByEmail(email).orElse(null);
            if (user != null) {
                user.setActive(true);
                userRepository.save(user);
            }
        }
        return isValid;
    }

    private void sendEmailWithConfirmationCode(CodeForUserValidationCode newCode) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newCode.getEmail());
        mailMessage.setSubject("Reset password code");
        mailMessage.setText(
                String.format("To reset your password enter next code into application: %s", newCode.getCode())
        );
        emailSenderService.sendEmail(mailMessage);
    }

    private CodeForUserValidationCode createCode(User user) {
        CodeForUserValidationCode newCode = new CodeForUserValidationCode();
        newCode.setCode(RandomStringUtils.randomNumeric(6));
        newCode.setEmail(user.getEmail());
        return newCode;
    }
}
