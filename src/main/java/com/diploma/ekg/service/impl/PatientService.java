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
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public Patient save(PatientDTO patient) {
        return patientRepository.save(patient.toEntity());
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

    @Override
    @Transactional
    public Optional<Patient> findPatientByPesel(String PESEL) {
        return patientRepository.findPatientByPesel(PESEL);
    }

    private List<PatientDTO> mapToDTOs(Iterable<Patient> iterable) {
        List<PatientDTO> patientDTOs = new ArrayList<>();
        iterable.forEach(patient -> patientDTOs.add(patient.toDTO()));
        return patientDTOs;
    }
}
