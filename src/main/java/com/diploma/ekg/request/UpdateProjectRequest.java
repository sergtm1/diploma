package com.diploma.ekg.request;

import com.diploma.ekg.dto.PatientDTO;
import org.springframework.web.multipart.MultipartFile;

public class UpdateProjectRequest {

    public Integer id;

    public String projectName;

    public Integer paperSpeed;

    public MultipartFile multipartFile;

    public String username;

    public String patientFirstName;

    public String patientLastName;

    public String PESEL;

    public PatientDTO toPatientDTO() {
        PatientDTO patient = new PatientDTO();
        patient.firstName = patientFirstName;
        patient.lastName = patientLastName;
        patient.PESEL = PESEL;
        return patient;
    }
}
