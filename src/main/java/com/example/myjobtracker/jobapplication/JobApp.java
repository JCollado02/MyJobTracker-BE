package com.example.myjobtracker.jobapplication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.time.LocalDate;
import java.util.Objects;

@Entity
// job application object, contains all information as well as getters/setters
public class JobApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // creates ids for each inserted job app
    private Integer id;

    private String companyName;
    private String position;
    private LocalDate appliedDate;
    private String status;
    private String notes;

    public JobApp() {
    }

    public JobApp(int id,
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JobApp that = (JobApp) o;
        return Objects.equals(id, that.id) && Objects.equals(companyName, that.companyName) && Objects.equals(position, that.position) && Objects.equals(appliedDate, that.appliedDate) && Objects.equals(status, that.status) && Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, position, appliedDate, status, notes);
    }
}
