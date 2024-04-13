package com.example.datlichkhambenh.model;

import java.util.HashMap;

public class MedicalExaminationInformation {
    private String id;
    private String fullName;
    private String date;
    private String doctor;
    private boolean status;

    public MedicalExaminationInformation() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public HashMap<String, Object> convertHashMap(){
        HashMap<String, Object> work = new HashMap<>();
        work.put("id", id);
        work.put("fullName",fullName);
        work.put("date", date);
        work.put("doctor", doctor);
        work.put("status", status);
        return work;
    }
}