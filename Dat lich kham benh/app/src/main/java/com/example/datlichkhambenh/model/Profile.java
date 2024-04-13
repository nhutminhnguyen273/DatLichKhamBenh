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

    public boolean getGender() {
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
        return work;
    }
}
