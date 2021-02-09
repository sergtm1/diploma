package com.diploma.ekg.service;

import com.diploma.ekg.dto.ProjectDTO;
import com.diploma.ekg.request.CreateProjectRequest;
import com.diploma.ekg.request.UpdateProjectRequest;
import com.diploma.ekg.utils.exceptions.MissingObjectException;

import java.io.IOException;
import java.util.Collection;

public interface IProjectService {

    Integer createProject(CreateProjectRequest request) throws MissingObjectException, IOException;

    Integer updateProject(UpdateProjectRequest request) throws MissingObjectException, IOException;

    void deleteProject(Integer id);

    Collection<ProjectDTO> getAll();

    Collection<ProjectDTO> getProjectsOfUser(String username) throws MissingObjectException;

    Collection<ProjectDTO> getProjectsForPatient(String username, Integer patientId) throws MissingObjectException;
}
