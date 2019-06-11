package com.example.diabeticpatientcareapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.diabeticpatientcareapp.model.profile_model;
import com.example.diabeticpatientcareapp.view.profileAdapter;

import java.util.ArrayList;
import java.util.List;

public class profile_list extends Activity {
    Button show;
    DatabaseHelper database;
    RecyclerView recyclerView;
    profileAdapter recycler;
    List<profile_model> profileModel;
    private Context nContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);

        show = (Button)findViewById(R.id.profileShow);
        profileModel = new ArrayList<profile_model>();
        recyclerView = (RecyclerView)findViewById(R.id.recycleProfile);
        nContext = profile_list.this;
        database = new DatabaseHelper(profile_list.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(profile_list.this);
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
        database = new DatabaseHelper(profile_list.this);
        profileModel.clear();
        profileModel = database.getAllprofile_model();
        recycler = new profileAdapter(profileModel ,getApplicationContext());
        recyclerView.setAdapter(recycler);
    }

    protected void onResume(){
        super.onResume();
        getData();
    }
}
