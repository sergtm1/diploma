package com.diploma.ekg.service.impl;

import com.diploma.ekg.dto.ProjectDTO;
import com.diploma.ekg.entity.Patient;
import com.diploma.ekg.entity.Project;
import com.diploma.ekg.entity.User;
import com.diploma.ekg.repository.ProjectRepository;
import com.diploma.ekg.request.CreateProjectRequest;
import com.diploma.ekg.request.UpdateProjectRequest;
import com.diploma.ekg.service.IPatientService;
import com.diploma.ekg.service.IProjectService;
import com.diploma.ekg.service.IUserService;
import com.diploma.ekg.utils.exceptions.MissingObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {

    private final IUserService userService;

    private final IPatientService patientService;

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(IUserService userService, IPatientService patientService, ProjectRepository projectRepository) {
        this.userService = userService;
        this.patientService = patientService;
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional
    public Integer createProject(CreateProjectRequest request) throws MissingObjectException, IOException {
        Project project = new Project();
        project.setUser(userService.getUser(request.username));
        Optional<Patient> patientByPesel = patientService.findPatientByPesel(request.PESEL);
        Patient patient = patientByPesel.orElseGet(() -> patientService.save(request.toPatientDTO()));
        project.setPatient(patient);
        project.setName(request.projectName);
        project.setPaperSpeed(request.paperSpeed);
        project.setImage(request.multipartFile.getBytes());
        Project savedProject = projectRepository.save(project);
        return savedProject.getId();
    }

    @Override
    @Transactional
    public Integer updateProject(UpdateProjectRequest request) throws MissingObjectException, IOException {
        Project project = projectRepository.findById(request.id)
                .orElseThrow(() -> new MissingObjectException("Can't load project"));
        project.setUser(userService.getUser(request.username));
        Optional<Patient> patientByPesel = patientService.findPatientByPesel(request.PESEL);
        Patient patient = patientByPesel.orElseGet(() -> patientService.save(request.toPatientDTO()));
        project.setPatient(patient);
        project.setName(request.projectName);
        project.setPaperSpeed(request.paperSpeed);
        project.setImage(request.multipartFile.getBytes());
        Project savedProject = projectRepository.save(project);
        return savedProject.getId();
    }

    @Override
    @Transactional
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Collection<ProjectDTO> getAll() {
        return mapToDTOs(projectRepository.findAll());
    }

    @Override
    @Transactional
    public Collection<ProjectDTO> getProjectsOfUser(String username) throws MissingObjectException {
        User user = userService.getUser(username);
        Collection<Project> projects = projectRepository.findProjectsByUserEquals(user);
        return mapToDTOs(projects);
    }

    private List<ProjectDTO> mapToDTOs(Iterable<Project> iterable) {
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        iterable.forEach(patient -> projectDTOs.add(patient.toDTO()));
        return projectDTOs;
    }
}
