package com.example.leavemanagementsystem;

public class Admin {

    String email, password, conform_passowrd;

    public Admin(String email, String password, String conform_passowrd) {
        this.email = email;
        this.password = password;
        this.conform_passowrd = conform_passowrd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConform_passowrd() {
        return conform_passowrd;
    }

    public void setConform_passowrd(String conform_passowrd) {
        this.conform_passowrd = conform_passowrd;
    }
}
