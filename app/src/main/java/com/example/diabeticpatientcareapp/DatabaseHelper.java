package com.example.diabeticpatientcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.diabeticpatientcareapp.model.bloodsugar_model;
import com.example.diabeticpatientcareapp.model.exercise_model;
import com.example.diabeticpatientcareapp.model.meal_model;
import com.example.diabeticpatientcareapp.model.medication_model;
import com.example.diabeticpatientcareapp.model.profile_model;
//import com.example.diabeticpatientcareapp.sql.medicineContract;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user ( email text primary key, password text)");
        db.execSQL("Create table medication ( id integer primary key autoincrement,medication_name text, measure text,note text, dosage int, date text )");
        db.execSQL("Create table bloodsugar ( id integer primary key autoincrement, measured text, results int, note text, date text)");
        db.execSQL("Create table diet ( id integer primary key autoincrement, mealType text, food text, note text,date text)");
        db.execSQL("Create table exercise( id integer primary key autoincrement, exerciseType text, duration int, note text,date text )");
        db.execSQL("Create table profile(id integer primary key autoincrement, name text, gender text, diabetesType text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists bloodsugar");
        db.execSQL("drop table if exists medication");
        db.execSQL("drop table if exists diet");
        db.execSQL("drop table if exists exercise");
        db.execSQL("drop table if exists profile");
        onCreate(db);

    }

    //inserting in database
    public boolean insert(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if (ins == -1) return false;
        else return true;

    }


    public void medicationInsert(String medication_name, String measure, int dosage, String note, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("medication_name", medication_name);
        contentValues.put("dosage", dosage);
        contentValues.put("measure", measure);
        contentValues.put("note", note);
        contentValues.put("date",date);

        db.insert("medication",null,contentValues);
    }

    public void bloodsugarInsert(String measured, int results, String note, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("measured",measured);
        contentValues.put("results",results);
        contentValues.put("note",note);
        contentValues.put("date",date);

        db.insert("bloodsugar",null,contentValues);

    }


    public boolean foodinsert(String mealType, String food, String note, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mealType", mealType);
        contentValues.put("food", food);
        contentValues.put("note",note);
        contentValues.put("date",date);

        long ins = db.insert("diet", null, contentValues);
        if (ins == -1) return false;
        else return true;

    }

    public boolean exerciseInsert(String exerciseType, int duration, String note, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("exerciseType", exerciseType);
        contentValues.put("duration", duration);
        contentValues.put("note", note);
        contentValues.put("date",date);
        long ins = db.insert("exercise",null,contentValues);
        if (ins == -1) return false;
        else return true;
    }

    public boolean profileInsert(String name, String gender, String diabetesType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("gender", gender);
        contentValues.put("diabetesType",diabetesType);
        long ins = db.insert("profile", null, contentValues);
        if (ins == -1) return false;
        else return true;

    }



    //checking if email exists;
    public Boolean chkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //checking the email and password
    public boolean emailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }




    //opens the database
    public DatabaseHelper open() throws SQLException {
      //  db.isOpen();
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }


    public ArrayList getAllMedication_model() {
        ArrayList<medication_model> arraylist = new ArrayList<medication_model>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from medication", null);
  rs.moveToFirst();
  while (!rs.isAfterLast()) {
            int id = rs.getInt(rs.getColumnIndex("id"));
            String measure = rs.getString(rs.getColumnIndex("measure"));

            String medication_name = rs.getString(rs.getColumnIndex("medication_name"));
            int dosage = rs.getInt(rs.getColumnIndex("dosage"));
            String note = rs.getString(rs.getColumnIndex("note"));
      String date = rs.getString(rs.getColumnIndex("date"));

//
//date after id
            arraylist.add(new medication_model(dosage,id,measure,note,medication_name,date));
            rs.moveToNext();
        }
        return arraylist;
    }
public ArrayList getAllbloodsugar_model(){
        ArrayList<bloodsugar_model> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from bloodsugar",null);
        rs.moveToFirst();
        while (!rs.isAfterLast()){
          int id = rs.getInt(rs.getColumnIndex("id"));
          String measured = rs.getString(rs.getColumnIndex("measured"));
          int results = rs.getInt(rs.getColumnIndex("results"));
          String note = rs.getString(rs.getColumnIndex("note"));
            String date = rs.getString(rs.getColumnIndex("date"));


          arrayList.add(new bloodsugar_model(results,id,measured,note,date));
          rs.moveToNext();
        }

        return arrayList;
}

    public ArrayList getAllmeal_model(){
        ArrayList<meal_model> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from diet",null);
        rs.moveToFirst();
        while (!rs.isAfterLast()){
            int id = rs.getInt(rs.getColumnIndex("id"));
            String mealType = rs.getString(rs.getColumnIndex("mealType"));
            String food = rs.getString(rs.getColumnIndex("food"));
            String note = rs.getString(rs.getColumnIndex("note"));
            String date = rs.getString(rs.getColumnIndex("date"));


            arrayList.add(new meal_model(id,mealType,food,note,date));
            rs.moveToNext();
        }

        return arrayList;
    }

    public ArrayList getAllexercise_model(){
        ArrayList<exercise_model> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from exercise",null);
        rs.moveToFirst();
        while (!rs.isAfterLast()){
            int id = rs.getInt(rs.getColumnIndex("id"));
            String exerciseType = rs.getString(rs.getColumnIndex("exerciseType"));
            int duration = rs.getInt(rs.getColumnIndex("duration"));
            String note = rs.getString(rs.getColumnIndex("note"));
            String date = rs.getString(rs.getColumnIndex("date"));

            arrayList.add(new exercise_model(id,exerciseType,duration,note,date));
            rs.moveToNext();
        }

        return arrayList;
    }

    public ArrayList getAllprofile_model(){
        ArrayList<profile_model> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from profile",null);
        rs.moveToFirst();
        while (!rs.isAfterLast()){
            int id = rs.getInt(rs.getColumnIndex("id"));
            String name = rs.getString(rs.getColumnIndex("name"));
            String gender = rs.getString(rs.getColumnIndex("gender"));
            String diabetesType = rs.getString(rs.getColumnIndex("diabetesType"));



            arrayList.add(new profile_model(id,name,gender,diabetesType));
            rs.moveToNext();
        }

        return arrayList;
    }

    public long updateExercise(int id, String exerciseType,int duration,String note){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("exerciseType", exerciseType);
            contentValues.put("duration", duration);
            contentValues.put("note", note);

            return db.update("exercise",contentValues,"id"+ " =?",new String[]{String.valueOf(id)});

        }catch (SQLException e){
          e.printStackTrace();
        }

        return 0;
    }

    //delete
    public long deleteExercise(int id){
        try{


            return db.delete("exercise","id"+ " =?",new String[]{String.valueOf(id)});

        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public long updateDiet(int id, String mealType,String food,String note){
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("mealType", mealType);
            contentValues.put("food", food);
            contentValues.put("note", note);

            return db.update("diet",contentValues,"id"+ " =?",new String[]{String.valueOf(id)});

        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
    //delete
    public long deleteDiet(int id){
        try{


            return db.delete("diet","id"+ " =?",new String[]{String.valueOf(id)});

        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

}







