package com.example.leavemanagementsystem;

public class Employee {

    String fullname,email,password,conform_password, reason;

    public Employee(){

    }

    public Employee(String fullname, String email, String password, String conform_password, String reason) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.conform_password = conform_password;
        this.reason = reason;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getConform_password() {
        return conform_password;
    }

    public void setConform_password(String conform_password) {
        this.conform_password = conform_password;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
