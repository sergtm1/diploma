package com.diploma.ekg.controller;

import com.diploma.ekg.dto.PatientDTO;
import com.diploma.ekg.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(path = "/save")
    @ResponseBody
    public Integer save(@RequestBody PatientDTO request) {
        return patientService.save(request).getId();
    }

    @GetMapping(path = "/getAll")
    @ResponseBody
    public Collection<PatientDTO> getPatients() {
        return patientService.getAll();
    }

    @DeleteMapping(path = "/{patientId}")
    public void deletePatient(@PathVariable Integer patientId) {
        patientService.deletePatient(patientId);
    }
}
