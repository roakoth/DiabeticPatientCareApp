package com.example.diabeticpatientcareapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.diabeticpatientcareapp.model.bloodsugar_model;
import com.example.diabeticpatientcareapp.view.bloodsugarAdapter;

import java.util.ArrayList;
import java.util.List;

public class bloodsugar_list extends Activity {
    Button show;
    DatabaseHelper database;
    RecyclerView recyclerView;
    bloodsugarAdapter recycler;
    List<bloodsugar_model> bloodsugarModel;
    private Context nContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_bloodsugar);

        show = (Button) findViewById(R.id.view1);
        bloodsugarModel = new ArrayList<bloodsugar_model>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle1);
        nContext = bloodsugar_list.this;
        database = new DatabaseHelper(bloodsugar_list.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(bloodsugar_list.this);
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
        database = new DatabaseHelper(bloodsugar_list.this);
        bloodsugarModel.clear();
        bloodsugarModel = database.getAllbloodsugar_model();
        recycler = new bloodsugarAdapter(bloodsugarModel ,getApplicationContext());
        recyclerView.setAdapter(recycler);
    }


    }


