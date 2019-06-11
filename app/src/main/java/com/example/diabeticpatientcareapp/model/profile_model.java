package com.example.diabeticpatientcareapp.model;

public class profile_model {

    private int id;
    private String name;
    private String gender;
    private String diabetesType;

    public profile_model(){

    }

    public profile_model(int id, String name, String gender, String diabetesType) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.diabetesType = diabetesType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDiabetesType() {
        return diabetesType;
    }

    public void setDiabetesType(String diabetesType) {
        this.diabetesType = diabetesType;
    }
}
