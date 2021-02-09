package com.diploma.ekg.request;

import org.springframework.web.multipart.MultipartFile;

public class CreateProjectRequest {

    public String projectName;

    public Integer paperSpeed;

    public MultipartFile multipartFile;

    public String username;

    public Integer patientId;

}
