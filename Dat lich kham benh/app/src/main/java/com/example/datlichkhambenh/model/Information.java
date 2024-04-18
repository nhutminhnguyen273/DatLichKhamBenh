package com.example.datlichkhambenh.model;

import java.util.HashMap;

public class Information {
    private String id;
    private String fullName;
    private String dateOfBirth;
    private boolean gender;
    private  String phoneNumber;
    private String email;
    private String doctor;
    private String sick;
    private String date;
    private int totalAmount;

    public Information() {

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getSick() {
        return sick;
    }

    public void setSick(String sick) {
        this.sick = sick;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
    public HashMap<String, Object> convertHashMap(){
        HashMap<String, Object> work = new HashMap<>();
        work.put("id", id);
        work.put("fullName",fullName);
        work.put("dateOfBirth", dateOfBirth);
        work.put("gender", gender);
        work.put("phoneNumber", phoneNumber);
        work.put("email", email);
        work.put("doctor", doctor);
        work.put("sick", sick);
        work.put("date", date);
        work.put("totalAmount", totalAmount);
        return work;
    }
}