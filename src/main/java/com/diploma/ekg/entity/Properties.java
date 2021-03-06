package com.diploma.ekg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTIES")
public class Properties extends AutoGeneratedIdModel {

    @Column
    private String firstQuestionResult;

    @Column
    private Boolean arePWavesPositive;

    @Column
    private int paperSpeed;

    @Column
    private Double meanInterval;

    @Column
    private Double pixelsPerMm;

    @Column
    private int pWaveRhythm;

    @Column
    private int qrsRate;

    public Properties() {
    }

    public String getFirstQuestionResult() {
        return firstQuestionResult;
    }

    public void setFirstQuestionResult(String firstQuestionResult) {
        this.firstQuestionResult = firstQuestionResult;
    }

    public Boolean getArePWavesPositive() {
        return arePWavesPositive;
    }

    public void setArePWavesPositive(Boolean arePWavesPositive) {
        this.arePWavesPositive = arePWavesPositive;
    }

    public int getPaperSpeed() {
        return paperSpeed;
    }

    public void setPaperSpeed(int paperSpeed) {
        this.paperSpeed = paperSpeed;
    }

    public Double getMeanInterval() {
        return meanInterval;
    }

    public void setMeanInterval(Double meanInterval) {
        this.meanInterval = meanInterval;
    }

    public Double getPixelsPerMm() {
        return pixelsPerMm;
    }

    public void setPixelsPerMm(Double pixelsPerMm) {
        this.pixelsPerMm = pixelsPerMm;
    }

    public int getpWaveRhythm() {
        return pWaveRhythm;
    }

    public void setpWaveRhythm(int pWaveRhythm) {
        this.pWaveRhythm = pWaveRhythm;
    }

    public int getQrsRate() {
        return qrsRate;
    }

    public void setQrsRate(int qrsRate) {
        this.qrsRate = qrsRate;
    }
}
