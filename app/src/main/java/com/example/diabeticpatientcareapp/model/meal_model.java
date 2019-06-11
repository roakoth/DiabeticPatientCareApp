package com.example.diabeticpatientcareapp.model;

public class meal_model {
    private int id;
    private String mealType;
    private String food;
    private String note;
    private String date;

    public meal_model(){

    }

    public meal_model(int id, String mealType, String food, String note, String date) {
        this.id = id;
        this.mealType = mealType;
        this.food = food;
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
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
