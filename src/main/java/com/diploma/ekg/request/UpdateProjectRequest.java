package com.diploma.ekg.request;

import org.springframework.web.multipart.MultipartFile;

public class UpdateProjectRequest {

    public Integer id;

    public String projectName;

    public Integer paperSpeed;

    public MultipartFile multipartFile;

    public String username;

    public Integer patientId;

}
