package com.example.mayur.employeedatabase;

public class Employee {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    String name;
    String email;
    String contact;

    public Employee(String name, String email, String contact, String skill) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.skill = skill;
    }

    String skill;
}
