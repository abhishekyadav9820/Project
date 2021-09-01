package com.example.feesstructure;

public class student {
    private String name;
    private String rfid;
    private String password;
    private String balance;
    private String sem;
    private String email;
    private String department;
    private String doj;
    private String fees;
    private String contact;


    public student() {

    }

    public student(String name,String contact, String rfid, String sem, String email, String password, String balance, String department, String doj, String fees) {
        this.name = name;
        this.rfid = rfid;
        this.password = password;
        this.balance = balance;
        this.department = department;
        this.doj = doj;
        this.fees = fees;
        this.sem = sem;
        this.email = email;
        this.contact=contact;
    }



    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }
}