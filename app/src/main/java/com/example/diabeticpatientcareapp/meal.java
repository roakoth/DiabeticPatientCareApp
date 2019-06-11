package com.example.diabeticpatientcareapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.diabeticpatientcareapp.helper.inputValidation;
import com.example.diabeticpatientcareapp.model.meal_model;
import com.example.diabeticpatientcareapp.view.mealAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class meal extends AppCompatActivity implements View.OnClickListener{
   // String[]mealTypes = {"Breakfast","Lunch","Dinner","Before","Late Evening","Night"};
   EditText e1,e2,e3;
   private EditText textDate1;
    Button b1,b2;
    private mealAdapter mAdapter;
    private DatabaseHelper db;
    private meal_model mealModel;
    private TextInputLayout textInputLayoutMealType;
    private TextInputLayout textInputLayoutFood;
    private inputValidation inputvalidation;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        e1 = (EditText)findViewById(R.id.mealType);
        e2 = (EditText)findViewById(R.id.foodText);
        e3 = (EditText)findViewById(R.id.notes_text1);
        b1 = (Button)findViewById(R.id.btn_save3);
        b2 = (Button)findViewById(R.id.btnShow5);
        textDate1=(EditText)findViewById(R.id.in_date1);
        textInputLayoutMealType = (TextInputLayout)findViewById(R.id.textInputLayoutMealType);
        textInputLayoutFood = (TextInputLayout)findViewById(R.id.textInputLayoutFood);

        initObjects();
        initListeners();


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
       // Spinner spin1 = (Spinner) findViewById(R.id.spinner3);
        //spin1.setOnItemSelectedListener(this);
//Creating the ArrayAdapter instance having the test Types list
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mealTypes);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        //spin1.setAdapter(aa);
    }

    private void initObjects(){
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        textDate1.setOnClickListener(this);
    }

    private void initListeners(){
        inputvalidation = new inputValidation(this);
        db= new DatabaseHelper(this);
        mealModel = new meal_model();
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
        new DatePickerDialog(meal.this, myDate, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }






    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save3:
                postDataToSQLite();
                break;

            case R.id.btnShow5:
                Intent accountsIntent = new Intent(this,meal_list.class);
                accountsIntent.putExtra("MEALTYPE",e1.getText().toString().trim());
                accountsIntent.putExtra("FOOD",e2.getText().toString().trim());
                accountsIntent.putExtra("NOTE",e3.getText().toString().trim());
                accountsIntent.putExtra("DATE", textDate1.getText().toString().trim());
                emptyEditText();
                startActivity(accountsIntent);
                break;

            case R.id.in_date1:
                datePic();
                break;

        }

    }

    private void postDataToSQLite(){
        if (!inputvalidation.isEditTextFilled(e1,textInputLayoutMealType, "Enter type of meal")){
            return;
        }
        if(!inputvalidation.isEditTextFilled(e2,textInputLayoutFood,"Enter food/drink")){
            return;
        }
        else{
            mealModel.setMealType(e1.getText().toString().trim());
            mealModel.setFood(e2.getText().toString().trim());
            mealModel.setNote(e3.getText().toString().trim());

            boolean insert = db.foodinsert(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),textDate1.getText().toString());
            //snack bar to show success message that record saved successfully
            Intent accountsIntent = new Intent(this,meal_list.class);
            Toast.makeText(this, "Successful entry!", Toast.LENGTH_SHORT).show();
            accountsIntent.putExtra("MEALTYPE",e1.getText().toString().trim());
            accountsIntent.putExtra("FOOD",e2.getText().toString().trim());
            accountsIntent.putExtra("NOTE",e3.getText().toString().trim());
            accountsIntent.putExtra("DATE", textDate1.getText().toString().trim());

            emptyEditText();
            startActivity(accountsIntent);
        }
    }

    private void emptyEditText() {
        e1.setText(null);
        e2.setText(null);
        e3 .setText(null);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        textDate1.setText(sdf.format(myCalendar.getTime()));
    }


}
