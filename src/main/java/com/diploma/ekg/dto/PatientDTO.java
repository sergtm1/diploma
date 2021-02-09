package com.diploma.ekg.dto;

import com.diploma.ekg.entity.Patient;

public class PatientDTO {

    public String firstName;

    public String lastName;

    public String PESEL;

    public Patient toEntity() {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPesel(PESEL);
        return patient;
    }
}
