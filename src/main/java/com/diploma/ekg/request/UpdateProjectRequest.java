package com.diploma.ekg.request;

import com.diploma.ekg.dto.PatientDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

public class UpdateProjectRequest {

    private final String regex = "^(.+)@(.+)$";

    public Integer id;

    public String projectName;

    public String email;

    public String patientFirstName;

    public String patientLastName;

    public String patientPESEL;

    public String projectBody;

    public PatientDTO toPatientDTO() {
        PatientDTO patient = new PatientDTO();
        patient.firstName = patientFirstName;
        patient.lastName = patientLastName;
        patient.PESEL = patientPESEL;
        return patient;
    }

    public void validateFields() {
        if (!Pattern.compile(regex).matcher(email).matches()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Passed email has wrong format. email: %s", email));
        }
        if (patientFirstName == null || patientFirstName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "First name can't be empty");
        }
        if (patientLastName == null || patientLastName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Last name can't be empty");
        }
        if (patientPESEL == null || patientPESEL.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "PESEL name can't be empty");
        }
        if (projectBody == null || projectBody.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Project body can't be empty");
        }
    }
}
