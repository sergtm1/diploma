package com.diploma.ekg.entity;

import com.diploma.ekg.dto.ProjectDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PROJECT")
public class Project extends AutoGeneratedIdModel {

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    @Lob
    private String projectSettings;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "PROPERTIES_ID")
    private Properties properties;

    public Project() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectSettings() {
        return projectSettings;
    }

    public void setProjectSettings(String projectSettings) {
        this.projectSettings = projectSettings;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public ProjectDTO toDTO() {
        ProjectDTO dto = new ProjectDTO();
        dto.id = id;
        dto.name = name;
        dto.projectBody = projectSettings;
        dto.email = user.getEmail();
        dto.patientFirstName = patient.getFirstName();
        dto.patientLastName = patient.getLastName();
        dto.patientPESEL = patient.getPesel();
        return dto;
    }
}
