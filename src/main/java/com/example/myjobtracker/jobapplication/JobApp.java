package com.example.myjobtracker.jobapplication;

import java.time.LocalDate;

// job application object, contains all information as well as getters/setters
public class JobApp {
    private Long id;

    private String companyName;
    private String position;
    private LocalDate appliedDate;
    private String status;
    private String notes;

    public JobApp() {
    }

    public JobApp(Long id,
                          String companyName,
                          String position,
                          LocalDate appliedDate,
                          String status,
                          String notes) {
        this.id = id;
        this.companyName = companyName;
        this.position = position;
        this.appliedDate = appliedDate;
        this.status = status;
        this.notes = notes;
    }

    public JobApp(String companyName,
                          String position,
                          LocalDate appliedDate,
                          String status,
                          String notes) {
        this.companyName = companyName;
        this.position = position;
        this.appliedDate = appliedDate;
        this.status = status;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "JobApplications{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", appliedDate=" + appliedDate +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
