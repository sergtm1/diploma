package com.diploma.ekg.service;

import com.diploma.ekg.dto.PatientDTO;
import com.diploma.ekg.entity.Patient;
import com.diploma.ekg.utils.exceptions.MissingObjectException;

import java.util.Collection;

public interface IPatientService {

    Integer save(PatientDTO patient);

    Collection<PatientDTO> getAll();

    void deletePatient(Integer patientId);

    Patient getPatient(Integer patientId) throws MissingObjectException;
}
