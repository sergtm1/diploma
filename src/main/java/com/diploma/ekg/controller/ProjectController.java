package com.diploma.ekg.controller;

import com.diploma.ekg.dto.ProjectDTO;
import com.diploma.ekg.request.CreateProjectRequest;
import com.diploma.ekg.service.IProjectService;
import com.diploma.ekg.utils.exceptions.MissingObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("/ekg")
public class ProjectController {

    private final IProjectService projectService;

    @Autowired
    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(path = "/save")
    @ResponseBody
    public void save(@RequestBody CreateProjectRequest request) {
        try {
            projectService.createProject(request);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Can't get bytes of image");
        } catch (MissingObjectException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping(path = "/getAll")
    public Collection<ProjectDTO> getAllProjects() {
        return projectService.getAll();
    }

    @GetMapping(path = "/getUserProjects")
    public Collection<ProjectDTO> getProjectsOfUser(@RequestParam String username) {
        try {
            return projectService.getProjectsOfUser(username);
        } catch (MissingObjectException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping(path = "/{projectId}")
    public void deleteEkg(@PathVariable Integer projectId) {
        projectService.deleteProject(projectId);
    }
}
