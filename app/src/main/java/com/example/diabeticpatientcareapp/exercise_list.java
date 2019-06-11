package com.example.diabeticpatientcareapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.diabeticpatientcareapp.model.exercise_model;
import com.example.diabeticpatientcareapp.view.exerciseAdapter;

import java.util.ArrayList;
import java.util.List;

public class exercise_list extends Activity implements CustomItemClicklistener {
    Button show;
    DatabaseHelper database;
    RecyclerView recyclerView;
    exerciseAdapter recycler;
    List<exercise_model> exerciseModel;
    private Context nContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_exercise);

        show = (Button) findViewById(R.id.view2);
        exerciseModel = new ArrayList<exercise_model>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle2);
        nContext = exercise_list.this;
        database = new DatabaseHelper(exercise_list.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(exercise_list.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
        database = new DatabaseHelper(exercise_list.this);
        exerciseModel.clear();
        exerciseModel = database.getAllexercise_model();
        recycler = new exerciseAdapter(exerciseModel ,getApplicationContext());
        recyclerView.setAdapter(recycler);

  }

    protected void onResume(){
        super.onResume();
        getData();
    }

    @Override
    public void onItemClick(View v, int position) {

    }
}
