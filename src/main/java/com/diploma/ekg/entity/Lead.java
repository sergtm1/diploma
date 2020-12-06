package com.diploma.ekg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LEAD")
public class Lead extends AutoGeneratedIdModel {

    @Column
    private String label;

    public Lead() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}