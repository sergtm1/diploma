package com.diploma.ekg.repository;

import com.diploma.ekg.entity.CodeForUserValidation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CodeForUserValidationRepository extends CrudRepository<CodeForUserValidation, Integer> {

    Optional<CodeForUserValidation> findCodeForUserValidationByEmail(String email);

    void removeCodeForUserValidationByEmail(String email);
}
