package com.diploma.ekg.repository;

import com.diploma.ekg.entity.Patient;
import com.diploma.ekg.entity.Project;
import com.diploma.ekg.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

    Collection<Project> findProjectsByUserEquals(User user);

    Collection<Project> findProjectsByUserAndPatient(User user, Patient patient);
}
