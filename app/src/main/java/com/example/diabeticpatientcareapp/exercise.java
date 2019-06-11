package com.example.diabeticpatientcareapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.diabeticpatientcareapp.helper.inputValidation;
import com.example.diabeticpatientcareapp.model.exercise_model;
import com.example.diabeticpatientcareapp.view.exerciseAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class exercise extends AppCompatActivity implements View.OnClickListener {
    // String[]exercise = {"Minutes","Hours"};
    EditText e1,e2,e3;
    Button b1,b2;
    private exerciseAdapter mAdapter;
    private DatabaseHelper db;
    private exercise_model exerciseModel;
    private TextInputLayout textInputLayoutExercisetype;
    private TextInputLayout textInputLayoutDuration;
    private EditText textDate2;
    private inputValidation inputvalidation;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        e1 =(EditText)findViewById(R.id.exercise_text);
        e2 = (EditText)findViewById(R.id.duration_text);
        e3= (EditText)findViewById(R.id.notes_editText);
        textDate2 =(EditText)findViewById(R.id.in_date3);
        b1= (Button)findViewById(R.id.btn_save9);
        b2= (Button)findViewById(R.id.btnShow9);
        textInputLayoutExercisetype = (TextInputLayout)findViewById(R.id.textInputLayoutExercisetype);
        textInputLayoutDuration = (TextInputLayout)findViewById(R.id.textInputLayoutDuration);


        initObjects();
        initListeners();


    }

    private void initObjects(){
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        textDate2.setOnClickListener(this);
    }

    private void initListeners(){
        inputvalidation = new inputValidation(this);
        db= new DatabaseHelper(this);
        exerciseModel = new exercise_model();
    }

    final DatePickerDialog.OnDateSetListener myDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    public void datePic(){
        // TODO Auto-generated method stub
        new DatePickerDialog(exercise.this, myDate, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_save9:
                postDataToSQLite();
                break;

            case R.id.btnShow9:
                Intent accountsIntent = new Intent(this,exercise_list.class);
                accountsIntent.putExtra("EXERCISETYPE",e1.getText().toString().trim());
                accountsIntent.putExtra("DURATION",e2.getText().toString().trim());
                accountsIntent.putExtra("NOTE",e3.getText().toString().trim());
                accountsIntent.putExtra("DATE", textDate2.getText().toString().trim());

                emptyEditText();
                startActivity(accountsIntent);
                break;

            case R.id.in_date3:
                datePic();
                break;
        }

    }

    private void postDataToSQLite(){
         if (!inputvalidation.isEditTextFilled(e1,textInputLayoutExercisetype, "Enter type of exercise")){
            return;
        }
        if(!inputvalidation.isEditTextFilled(e2,textInputLayoutDuration,"Enter duration")){
            return;
        }



        else{
            exerciseModel.setExerciseType(e1.getText().toString().trim());
            exerciseModel.setDuration(Integer.parseInt(e2.getText().toString().trim()));
            exerciseModel.setNote(e3.getText().toString().trim());

            boolean insert = db.exerciseInsert(e1.getText().toString(),Integer.parseInt(e2.getText().toString()), e3.getText().toString(),textDate2.getText().toString());

            //snack bar to show success message that record saved successfully
            Toast.makeText(this, "Successful entry!", Toast.LENGTH_SHORT).show();
            Intent accountsIntent = new Intent(this,exercise_list.class);
            accountsIntent.putExtra("EXERCISETYPE",e1.getText().toString().trim());
            accountsIntent.putExtra("DURATION",e2.getText().toString().trim());
            accountsIntent.putExtra("NOTE",e3.getText().toString().trim());
            accountsIntent.putExtra("DATE", textDate2.getText().toString().trim());

            emptyEditText();
            startActivity(accountsIntent);

        }
    }

    private void emptyEditText() {
        e1.setText(null);
        e2.setText(null);
        e3.setText(null);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        textDate2.setText(sdf.format(myCalendar.getTime()));
    }
}

//        //Getting the instance of Spinner and applying OnItemSelectedListener on it
//        Spinner spin1 = (Spinner) findViewById(R.id.spinner2);
//        spin1.setOnItemSelectedListener(this);
////Creating the ArrayAdapter instance having the test Types list
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,exercise);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////Setting the ArrayAdapter data on the Spinner
//        spin1.setAdapter(aa);
//    }


//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getApplicationContext(), exercise[position], Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

