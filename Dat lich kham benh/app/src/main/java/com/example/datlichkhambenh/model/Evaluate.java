package com.example.datlichkhambenh.model;

import java.util.HashMap;

public class Evaluate {
    private String id;
    private String email;
    private String doctor;
    private String evaluateDate;
    private String evaluate;
    private String comment;

    public Evaluate() {

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

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public HashMap<String, Object> convertHashMap(){
        HashMap<String, Object> work = new HashMap<>();
        work.put("id", id);
        work.put("email", email);
        work.put("doctor", doctor);
        work.put("evaluate", evaluate);
        work.put("comment", comment);
        return work;
    }
}
