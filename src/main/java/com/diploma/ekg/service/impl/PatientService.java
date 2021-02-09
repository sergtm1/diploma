package com.diploma.ekg.service.impl;

import com.diploma.ekg.dto.PatientDTO;
import com.diploma.ekg.entity.Patient;
import com.diploma.ekg.repository.PatientRepository;
import com.diploma.ekg.service.IPatientService;
import com.diploma.ekg.utils.exceptions.MissingObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public Integer save(PatientDTO patient) {
        Patient savedPatient = patientRepository.save(patient.toEntity());
        return savedPatient.getId();
    }

    @Override
    @Transactional
    public Collection<PatientDTO> getAll() {
        return mapToDTOs(patientRepository.findAll());
    }

    @Override
    @Transactional
    public void deletePatient(Integer patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    @Transactional
    public Patient getPatient(Integer patientId) throws MissingObjectException {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient == null) {
            throw new MissingObjectException("Can't find patient");
        }
        return patient;
    }

    private List<PatientDTO> mapToDTOs(Iterable<Patient> iterable) {
        List<PatientDTO> patientDTOs = new ArrayList<>();
        iterable.forEach(patient -> patientDTOs.add(patient.toDTO()));
        return patientDTOs;
    }
}
