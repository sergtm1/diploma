package com.diploma.ekg.entity;

import javax.persistence.*;

@Entity
@Table(name = "DIAGNOSIS_VISIT")
public class DiagnosisDuringVisit extends AutoGeneratedIdModel {

    @Column
    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "VISIT_ID")
    private Visit visit;

    @ManyToOne
    @JoinColumn(name = "DIAGNOSIS_ID")
    private Diagnosis diagnosis;

    public DiagnosisDuringVisit() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }
}
