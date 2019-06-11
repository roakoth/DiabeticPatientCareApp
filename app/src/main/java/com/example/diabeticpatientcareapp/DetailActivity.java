package com.example.diabeticpatientcareapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    EditText e1,e2,e3;
    Button updateBtn,deleteBtn;
    private SQLiteDatabase db;
     

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //receive data from exercise activity
        Intent i = getIntent();
        final String exerciseType = i.getExtras().getString("EXERCISETYPE");
       final int duration = i.getExtras().getInt("DURATION");
        final String note = i.getExtras().getString("NOTE");
        final int id = i.getExtras().getInt("ID");

        updateBtn = (Button)findViewById(R.id.btn_update);
        deleteBtn = (Button)findViewById(R.id.btn_delete);
        e1 = (EditText)findViewById(R.id.editExerciseType);
        e2 = (EditText)findViewById(R.id.editDuration);
        e3 = (EditText)findViewById(R.id.editNote);

        //assign data to those views
        e1.setText(exerciseType);
        e2.setText(String.valueOf(duration));
        e3.setText(note);

        //update
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               update(id,e1.getText().toString(), Integer.parseInt(e2.getText().toString()),e3.getText().toString());
            }
        });

        //delete
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(id);
            }
        });




    }

    private void update(int id, String newExerciseType, int newDuration, String newNote){

        DatabaseHelper db = new DatabaseHelper(this);
        long result = db.updateExercise(id,newExerciseType,newDuration,newNote);

        if (result>0){
            e1.setText(newExerciseType);
            e2.setText(String.valueOf(newDuration));
            e3.setText(newNote);
            Snackbar.make(e1,"Updated successfully",Snackbar.LENGTH_SHORT).show();
        }
        else {
            Snackbar.make(e1,"Unable to update",Snackbar.LENGTH_SHORT).show();
        }

        db.close();
    }

    //delete

    private void delete(int id){

        DatabaseHelper db = new DatabaseHelper(this);
//        db.open();
        long result = db.deleteExercise(id);

        if (result>0){
            this.finish();
        }
        else {
            Snackbar.make(e1,"Unable to delete",Snackbar.LENGTH_SHORT).show();
        }

        db.close();
    }


}
