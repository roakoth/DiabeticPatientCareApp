package com.example.diabeticpatientcareapp.model;

public class bloodsugar_model {
    private int results;
    private int id;
    private String measured;
    private String note;
    private String date;

    public bloodsugar_model(){

    }


    public bloodsugar_model(int results, int id, String measured, String note, String date) {
        this.results = results;
        this.id = id;
        this.measured = measured;
        this.note = note;
        this.date = date;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeasured() {
        return measured;
    }

    public void setMeasured(String measured) {
        this.measured = measured;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}




