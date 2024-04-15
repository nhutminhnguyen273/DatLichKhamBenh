package com.example.datlichkhambenh.model;

import java.util.HashMap;

public class MedicalHistory {
    private String id;
    private String email;
    private String medicalRecord;
    private String services;
    private String prescription;
    private String visitDate;
    private String payment;

    public MedicalHistory() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public HashMap<String, Object> convertHashMap(){
        HashMap<String, Object> work = new HashMap<>();
        work.put("id", id);
        work.put("email", email);
        work.put("medicalRecord", medicalRecord);
        work.put("services", services);
        work.put("prescription", prescription);
        work.put("visitDate", visitDate);
        work.put("payment", payment);
        return work;
    }
}

