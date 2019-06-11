package com.example.diabeticpatientcareapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class blood_detailActivity extends AppCompatActivity {
    EditText e1,e2,e3;
    Button updateBtn,deleteBtn;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_detail);

        //receive data from exercise activity
        Intent i = getIntent();
        final String measured  = i.getExtras().getString("TESTTYPE");
        final int results = i.getExtras().getInt("RESULTS");
        final String note = i.getExtras().getString("NOTE");
        final int id = i.getExtras().getInt("ID");

        updateBtn = (Button)findViewById(R.id.btn_updateBlood);
        deleteBtn = (Button)findViewById(R.id.btn_deleteBlood);
        e1 = (EditText)findViewById(R.id.editMeasured);
        e2 = (EditText)findViewById(R.id.editResults);
        e3 = (EditText)findViewById(R.id.noteEdit);

        //assign data to those views
        e1.setText(measured);
        e2.setText(String.valueOf(results));
        e3.setText(note);



    }
}
