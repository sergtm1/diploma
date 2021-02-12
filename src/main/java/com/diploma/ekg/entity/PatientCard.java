package com.diploma.ekg.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT_CARD")
public class PatientCard extends AutoGeneratedIdModel {

    @ManyToOne
    @JoinColumn(name = "VISIT_ID")
    private Visit visits;

    @ManyToOne
    @JoinColumn(name = "DIAGNOSIS_ID")
    private Diagnosis diagnosis;

    public PatientCard() {
    }

    public Visit getVisits() {
        return visits;
    }

    public void setVisits(Visit visits) {
        this.visits = visits;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }
}
