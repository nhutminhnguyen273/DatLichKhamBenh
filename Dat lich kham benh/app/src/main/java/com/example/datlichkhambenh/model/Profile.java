package com.example.datlichkhambenh.model;

import java.util.HashMap;

public class Profile {
    private String id;
    private String fullName;
    private String dateOfBirth;
    private boolean gender;
    private String address;
    private String country;
    private String phoneNumber;
    private String email;
    private String sick;
    private String doctor;
    private String mEDate;
    private boolean status;

    public Profile() {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getSick() {
        return sick;
    }

    public void setSick(String sick) {
        this.sick = sick;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getMEDate() {
        return mEDate;
    }

    public void setMEDate(String mEDate) {
        this.mEDate = mEDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public HashMap<String, Object> convertHashMap(){
        HashMap<String, Object> work = new HashMap<>();
        work.put("id", id);
        work.put("fullName",fullName);
        work.put("dateOfBirth", dateOfBirth);
        work.put("gender", gender);
        work.put("address", address);
        work.put("country", country);
        work.put("phoneNumber", phoneNumber);
        work.put("email", email);
        work.put("sick", sick);
        work.put("doctor", doctor);
        work.put("mEDate", mEDate);
        work.put("status", status);
        return work;
    }
}
