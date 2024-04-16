package com.example.finalproject.Models;

import java.io.Serializable;

public class Email implements Serializable {
    public Email(String emailID, String title, String description) {
        this.emailID = emailID;
        this.title = title;
        this.description = description;
    }
    public Email() {
    }

    public String getEmailID() {
        return emailID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailID='" + emailID + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private String emailID;
    private String title;
    private String description;
}
