package com.shahbazyan.phonebook.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contacts {

    @Id
    @GeneratedValue
    private Long id;
    private String full_name;
    private String contact;
    private String description;

    public Contacts(String full_name, String contact, String description) {

        this.full_name = full_name;
        this.contact = contact;
        this.description = description;
    }

    public Contacts() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
