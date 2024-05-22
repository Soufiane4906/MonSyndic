package com.example.monsyndic.entities;


public class UserInfo {

    private String userName;
    private String address;
    private String contactNumber;
    private String email;

    private String Immeuble;
    // Constructor
    public UserInfo(String userName, String address, String contactNumber, String email , String imm) {
        this.userName = userName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.Immeuble=imm;
        this.email = email;
    }

    public UserInfo() {
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImmeuble() {
        return Immeuble;
    }

    public void setImmeuble(String immeuble) {
        Immeuble = immeuble;
    }

    public String getEmail() {
        return email;
    }
}


