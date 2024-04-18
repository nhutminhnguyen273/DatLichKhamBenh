package com.example.datlichkhambenh.model;

import java.util.HashMap;

public class Feedback {
    private String id;
    private String fullName;
    private String emailSent;
    private String emailDoctor;
    private String doctor;
    private String content;

    public Feedback() {

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

    public String getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(String emailSent) {
        this.emailSent = emailSent;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmailDoctor() {
        return emailDoctor;
    }

    public void setEmailDoctor(String emailDoctor) {
        this.emailDoctor = emailDoctor;
    }

    public HashMap<String, Object> convertHashMap(){
        HashMap<String, Object> work = new HashMap<>();
        work.put("id", id);
        work.put("fullName",fullName);
        work.put("emailSent", emailSent);
        work.put("doctor", doctor);
        work.put("content", content);
        work.put("emailDoctor", emailDoctor);
        return work;
    }
}
