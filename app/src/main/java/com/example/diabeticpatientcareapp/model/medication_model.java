package com.example.diabeticpatientcareapp.model;

public class medication_model {

    private int dosage;
    private int id;
    private String measure;
    private String note;
    private String medication_name;
    private String date;

    public medication_model() {
    }

    public medication_model(int dosage, int id, String measure, String note, String medication_name,String date) {
        this.dosage = dosage;
        this.id = id;
        this.measure = measure;
        this.note = note;
        this.medication_name = medication_name;
        this.date = date;
    }

   ;


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id =id;
    }

    public int getDosage(){
        return dosage;
    }
    public void setDosage(int dosage){
        this.dosage = dosage;
    }
    public String getMeasure(){
        return measure;
    }
    public void setMeasure(String measure){
        this.measure = measure;
    }
    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }
    public String getMedication_name(){
        return medication_name;
    }
    public void setMedication_name(String medication_name){
        this.medication_name=medication_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
