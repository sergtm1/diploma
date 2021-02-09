package com.diploma.ekg.service;

import com.diploma.ekg.dto.PatientDTO;
import com.diploma.ekg.entity.Patient;
import com.diploma.ekg.utils.exceptions.MissingObjectException;

import java.util.Collection;
import java.util.Optional;

public interface IPatientService {

    Patient save(PatientDTO patient);

    Collection<PatientDTO> getAll();

    void deletePatient(Integer patientId);

    Patient getPatient(Integer patientId) throws MissingObjectException;

    Optional<Patient> findPatientByPesel(String PESEL) throws MissingObjectException;
}
