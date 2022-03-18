package com.example.examfinal.model.service;

public class UserRegistrationServiceModel {


    private String firstName;
    private String lastName;
    private String password;
    private String username;

    public String getUsername() {
        return username != null ?
                username.trim() :
                null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}