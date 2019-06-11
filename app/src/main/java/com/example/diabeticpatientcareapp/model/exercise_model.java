package com.example.diabeticpatientcareapp.model;

public class exercise_model {
    private int id;
    private String exerciseType;
    private int duration;
    private String note;
    private String date;

    public exercise_model(){

    }

    public exercise_model(int id, String exerciseType, int duration, String note, String date) {
        this.id = id;
        this.exerciseType = exerciseType;
        this.duration = duration;
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

