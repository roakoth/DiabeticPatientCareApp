package com.example.diabeticpatientcareapp;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.diabeticpatientcareapp.view.TitleAdapter;

import java.util.ArrayList;

public class diabetes_care_tips extends AppCompatActivity {
    private static final String TAG = "diabetes_care_tips";
    private Context mContext;
    ArrayList<String> titleArrayList;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes_care_tips);

        mContext = diabetes_care_tips.this;

        titleArrayList = new ArrayList<String>();

        titleArrayList.add(constants.REDUCE_BLOOD_SUGAR_THROUGH_DIET);
        titleArrayList.add(constants.EXERCISE_TIPS_FOR_DIABETIC_PATIENTS);
        titleArrayList.add(constants.DIET_SPECIFIED_FOR_LOW_BLOOD_SUGAR_PATIENTS);
        titleArrayList.add(constants.GENERAL_TIPS);
        titleArrayList.add(constants.TIPS_FOR_MANAGING_LOW_BLOOD_SUGAR);

        mRecyclerView = (RecyclerView) findViewById(R.id.title_layout_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        TitleAdapter adapter = new TitleAdapter(mContext, titleArrayList, new CustomItemClicklistener() {
            @Override
            public void onItemClick(View v, int position) {

                Intent desIntent = new Intent(mContext,descriptionActivity.class);
                desIntent.putExtra("titles",titleArrayList.get(position));

                startActivity(desIntent);

                Toast.makeText(mContext,"clicked" +titleArrayList.get(position),Toast.LENGTH_SHORT).show();

            }
        });

        mRecyclerView.setAdapter(adapter);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
               // Toast.makeText(this,"Forum is selected",Toast.LENGTH_SHORT).show();
                Intent i = new Intent (diabetes_care_tips.this,forum.class);
                startActivity(i);
                return true;

            default:return super.onOptionsItemSelected(item);

        }



        }

    // @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);

        return true;

    }
}
