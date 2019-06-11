package com.example.diabeticpatientcareapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity2 extends AppCompatActivity {

    EditText edit1,edit2,edit3;
    Button updateBtn1,deleteBtn1;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        //receive data from diet activity
        Intent i = getIntent();
        final String mealType = i.getExtras().getString("MEALTYPE");
        final String food = i.getExtras().getString("FOOD");
        final String note = i.getExtras().getString("NOTE");
        final int id = i.getExtras().getInt("ID");

        updateBtn1 = (Button)findViewById(R.id.btn_update1);
        deleteBtn1= (Button)findViewById(R.id.btn_delete1);
        edit1 = (EditText)findViewById(R.id.editMealType);
        edit2 = (EditText)findViewById(R.id.editFood);
        edit3 = (EditText)findViewById(R.id.editNote1);

        edit1.setText(mealType);
        edit2.setText(food);
        edit3.setText(note);

        updateBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(id,edit1.getText().toString(),edit2.getText().toString(),edit3.getText().toString());
            }
        });

        //delete
        deleteBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(id);
            }
        });

    }

    private void update(int id, String newMealType, String newFood, String newNote){

        DatabaseHelper db = new DatabaseHelper(this);
        long result = db.updateDiet(id,newMealType,newFood,newNote);

        if (result>0){
            edit1.setText(newMealType);
            edit2.setText(newFood);
            edit3.setText(newNote);
            Snackbar.make(edit1,"Updated successfully",Snackbar.LENGTH_SHORT).show();
        }
        else {
            Snackbar.make(edit1,"Unable to update",Snackbar.LENGTH_SHORT).show();
        }

        db.close();
    }
    //delete

    private void delete(int id){

        DatabaseHelper db = new DatabaseHelper(this);
//        db.open();
        long result = db.deleteDiet(id);

        if (result>0){
            this.finish();
        }
        else {
            Snackbar.make(edit1,"Unable to delete",Snackbar.LENGTH_SHORT).show();
        }

        db.close();
    }

}
