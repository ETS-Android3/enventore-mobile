package com.example.shoppingcart.models;

public class User {
    private String userId , name , email , dietary , dietaryRestrictions , physicalCondition;
    private int contactNo , BMI;

    public User(String userId, String name, String email, String dietary, String dietaryRestrictions, String physicalCondition, int contactNo, int BMI) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.dietary = dietary;
        this.dietaryRestrictions = dietaryRestrictions;
        this.physicalCondition = physicalCondition;
        this.contactNo = contactNo;
        this.BMI = BMI;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getDietary() {
        return dietary;
    }

    public void setDietary(String dietary) {
        this.dietary = dietary;
    }

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(String dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public String getPhysicalCondition() {
        return physicalCondition;
    }

    public void setPhysicalCondition(String physicalCondition) {
        this.physicalCondition = physicalCondition;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public int getBMI() {
        return BMI;
    }

    public void setBMI(int BMI) {
        this.BMI = BMI;
    }
}
