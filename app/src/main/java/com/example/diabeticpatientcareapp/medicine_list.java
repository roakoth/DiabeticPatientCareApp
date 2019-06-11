package com.example.diabeticpatientcareapp;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.diabeticpatientcareapp.model.medication_model;
import com.example.diabeticpatientcareapp.view.medicineAdapter;

import java.util.ArrayList;
import java.util.List;

public class medicine_list extends Activity {
    Button show;
    DatabaseHelper database;
    RecyclerView recyclerView;
    medicineAdapter recycler;
    List<medication_model> Medication_model;
    private Context nContext;



    ArrayList<medication_model> Medication_model2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_medicine);
        show = (Button) findViewById(R.id.view);
        Medication_model = new ArrayList<medication_model>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        nContext = medicine_list.this;

        database = new DatabaseHelper(medicine_list.this);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(medicine_list.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
            }
        });
    }

    private void getData() {
        database = new DatabaseHelper(medicine_list.this);
        Medication_model.clear();
        Medication_model = database.getAllMedication_model();
        recycler = new medicineAdapter(Medication_model,getApplicationContext());
        //Log.i("HIteshdata", "" + Medication_model);
        recyclerView.setAdapter(recycler);
    }
}
