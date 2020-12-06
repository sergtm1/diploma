package com.diploma.ekg.repository;

import com.diploma.ekg.entity.CodeForUserValidationCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CodeForUserValidationRepository extends CrudRepository<CodeForUserValidationCode, Integer> {

    Optional<CodeForUserValidationCode> findCodeForUserValidationByEmail(String email);
}
