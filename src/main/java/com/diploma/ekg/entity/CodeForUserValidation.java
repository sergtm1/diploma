package com.diploma.ekg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "VALIDATION_CODE")
public class CodeForUserValidation extends AutoGeneratedIdModel {

    @Column
    private String code;

    @Column
    private LocalDateTime validUntil;

    @Column
    private String email;

    public CodeForUserValidation() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime dateTime) {
        this.validUntil = dateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
