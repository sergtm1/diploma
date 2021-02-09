package com.diploma.ekg.repository;

import com.diploma.ekg.entity.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

    Optional<Patient> findPatientByPesel(String pesel);
}
