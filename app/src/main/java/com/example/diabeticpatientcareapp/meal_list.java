package com.example.diabeticpatientcareapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.diabeticpatientcareapp.model.meal_model;
import com.example.diabeticpatientcareapp.view.mealAdapter;

import java.util.ArrayList;
import java.util.List;

public class meal_list extends Activity {
    Button show;
    DatabaseHelper database;
    RecyclerView recyclerView;
    mealAdapter recycler;
    List<meal_model> mealModel;
    private Context nContext;
    ArrayList<meal_model> mealModel2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_food);

        show = (Button)findViewById(R.id.view2);
        mealModel = new ArrayList<meal_model>();
        recyclerView = (RecyclerView)findViewById(R.id.recycle2);
        nContext = meal_list.this;
        database = new DatabaseHelper(meal_list.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(meal_list.this);
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
        database = new DatabaseHelper(meal_list.this);
        mealModel.clear();
        mealModel = database.getAllmeal_model();
        recycler = new mealAdapter(mealModel ,getApplicationContext());
        recyclerView.setAdapter(recycler);
    }

    protected void onResume(){
        super.onResume();
        getData();
    }

    }

