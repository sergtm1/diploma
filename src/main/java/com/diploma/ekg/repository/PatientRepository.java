package com.diploma.ekg.repository;

import com.diploma.ekg.entity.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
