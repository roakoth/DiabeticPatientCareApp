package com.example.diabeticpatientcareapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class medicineDetail extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button updateBtn,deleteBtn;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);

        //receive data from exercise activity
        Intent i = getIntent();
        final String medication_name  = i.getExtras().getString("MEDICATION");
        final String measure  = i.getExtras().getString("UNITMEASURE");
        final int dosage = i.getExtras().getInt("DOSAGE");
        final String note = i.getExtras().getString("NOTE");
        final int id = i.getExtras().getInt("ID");

        updateBtn = (Button)findViewById(R.id.btn_updateMed);
        deleteBtn = (Button)findViewById(R.id.btn_deleteMed);
        e1 = (EditText)findViewById(R.id.editMedicine);
        e2 = (EditText)findViewById(R.id.measureEdit);
        e3 = (EditText)findViewById(R.id.dosageEdit);
        e4 = (EditText)findViewById(R.id.noteEdit);

        //assign data to those views
        e1.setText(medication_name);
        e2.setText(measure);
        e3.setText(String.valueOf(dosage));
        e4.setText(note);

    }
}
