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
    public ProjectDTO createProject(CreateProjectRequest request) throws MissingObjectException {
        Project project = new Project();
        project.setUser(userService.getUser(request.email));
        Optional<Patient> patientByPesel = patientService.findPatientByPesel(request.patientPESEL);
        Patient patient = patientByPesel.orElseGet(() -> patientService.save(request.toPatientDTO()));
        project.setPatient(patient);
        project.setName(request.projectName);
        project.setProjectSettings(request.projectBody);
        return projectRepository.save(project).toDTO();
    }

    @Override
    @Transactional
    public ProjectDTO updateProject(UpdateProjectRequest request) throws MissingObjectException {
        Project project = projectRepository.findById(request.id)
                .orElseThrow(() -> new MissingObjectException("Can't load project"));
        project.setUser(userService.getUser(request.email));
        Optional<Patient> patientByPesel = patientService.findPatientByPesel(request.patientPESEL);
        Patient patient = patientByPesel.orElseGet(() -> patientService.save(request.toPatientDTO()));
        project.setPatient(patient);
        project.setName(request.projectName);
        project.setProjectSettings(request.projectBody);
        return projectRepository.save(project).toDTO();
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

    @Override
    public Collection<ProjectDTO> getProjectsForPatient(String username, Integer patientId) throws MissingObjectException {
        User user = userService.getUser(username);
        Patient patient = patientService.getPatient(patientId);
        Collection<Project> projects = projectRepository.findProjectsByUserAndPatient(user, patient);
        return mapToDTOs(projects);
    }

    private List<ProjectDTO> mapToDTOs(Iterable<Project> iterable) {
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        iterable.forEach(patient -> projectDTOs.add(patient.toDTO()));
        return projectDTOs;
    }
}
