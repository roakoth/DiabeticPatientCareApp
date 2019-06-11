package com.example.diabeticpatientcareapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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
import com.example.diabeticpatientcareapp.model.medication_model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class medicine extends AppCompatActivity implements View.OnClickListener {
//    String[]MeasureTypes = {"mg","unit","mL","tablet"};
private EditText medication_Edittext;
private EditText unitMeasure;
private EditText dosagetext;
private EditText notes_text;
private Button btn_save1;
private Button btnShow;
private Button btn_date;
private EditText textDate;
private int mYear,mMonth,mDay;
private TextInputLayout textInputLayoutMedication;
private TextInputLayout textInputLayoutUnitmeasure;
    private TextInputLayout textInputLayoutDosage;
private inputValidation inputvalidation;
private DatabaseHelper databasehelper;
private medication_model medicationModel;
    final Calendar myCalendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

//        Spinner spin = (Spinner) findViewById(R.id.spinner1);
//        spin.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the test Types list
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,MeasureTypes);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
//        spin.setAdapter(aa);
        initViews();
        initObjects();
        initListeners();



    }

//initialize views
    private void initViews() {
        medication_Edittext = (EditText) findViewById(R.id.medication_Edittext);
        unitMeasure = (EditText) findViewById(R.id.unitMeasure);
        dosagetext=(EditText)findViewById(R.id.dosagetext);
        notes_text=(EditText)findViewById(R.id.notes_text);
        btn_save1 = (Button)findViewById(R.id.btn_save1);
        btnShow= (Button)findViewById(R.id.btnShow);
        textInputLayoutMedication=(TextInputLayout)findViewById(R.id.textInputLayoutMedication);
        textInputLayoutUnitmeasure=(TextInputLayout)findViewById(R.id.textInputLayoutUnitMeasure);
        textInputLayoutDosage = (TextInputLayout) findViewById(R.id.textInputLayoutDosage);
        textDate = (EditText)findViewById(R.id.in_date);



    }
//This method is to initialise listeners
    private void initObjects(){
     btn_save1.setOnClickListener(this);
     btnShow.setOnClickListener(this);
     textDate.setOnClickListener(this);
//     btn_date.setOnClickListener(this);

    }
//this method is to initialise objects that will be in use
    private void initListeners(){
        inputvalidation = new inputValidation(this);
        databasehelper = new DatabaseHelper(this);
        medicationModel = new medication_model();

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



        //@Override


        public void datePic(){
            // TODO Auto-generated method stub
            new DatePickerDialog(medicine.this, myDate, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }






    @Override



    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_save1:
                postDataToSQLite();
                break;

            case R.id.btnShow:
                Intent accountsIntent = new Intent(this,medicine_list.class);
                accountsIntent.putExtra("MEDICATION",medication_Edittext.getText().toString().trim());
                accountsIntent.putExtra("UNITMEASURE",unitMeasure.getText().toString().trim());
                accountsIntent.putExtra("DOSAGE",dosagetext.getText().toString().trim());
                accountsIntent.putExtra("NOTES",notes_text.getText().toString().trim());
                accountsIntent.putExtra("DATE", textDate.getText().toString().trim());
                emptyEditText();
                startActivity(accountsIntent);
                break;

            case R.id.in_date:
                datePic();
                break;

//            case R.id.btn_date:
//               datePic();
//               break;
        }


    }


    private void postDataToSQLite() {
        if (!inputvalidation.isEditTextFilled(medication_Edittext,textInputLayoutMedication, "Enter medication")){
            return;
        }
        if(!inputvalidation.isEditTextFilled(unitMeasure,textInputLayoutUnitmeasure,"Enter unit of measure")){
            return;
        }
        if(!inputvalidation.isEditTextFilled(dosagetext,textInputLayoutDosage,"Enter dosage")){
            return;
        }




       // if (!databasehelper.checkMedication(medication_Edittext.getText().toString().trim())){
         else{


            medicationModel.setMedication_name(medication_Edittext.getText().toString().trim());
            medicationModel.setMeasure(unitMeasure.getText().toString().trim());
            medicationModel.setDosage(Integer.parseInt(dosagetext.getText().toString().trim()));
            medicationModel.setNote(notes_text.getText().toString().trim());
            medicationModel.setDate(textDate.getText().toString().trim());

            databasehelper.medicationInsert(medication_Edittext.getText().toString(), unitMeasure.getText().toString(),
                    Integer.parseInt(dosagetext.getText().toString()), notes_text.getText().toString(),textDate.getText().toString());

            // databasehelper.addMedication(medicationModel);

            //snack bar to show success message that record saved successfully
            Intent accountsIntent = new Intent(this, medicine_list.class);
            Toast.makeText(this, "Successful entry!", Toast.LENGTH_SHORT).show();
            accountsIntent.putExtra("MEDICATION", medication_Edittext.getText().toString().trim());
            accountsIntent.putExtra("UNITMEASURE", unitMeasure.getText().toString().trim());
            accountsIntent.putExtra("DOSAGE", dosagetext.getText().toString().trim());
            accountsIntent.putExtra("NOTE", notes_text.getText().toString().trim());
            accountsIntent.putExtra("DATE",textDate.getText().toString().trim());
            emptyEditText();
            startActivity(accountsIntent);
            //Toast.makeText(this, "Successful entry!", Toast.LENGTH_SHORT).show();

        }

    }

    private void emptyEditText() {
        medication_Edittext.setText(null);
        unitMeasure.setText(null);
        dosagetext.setText(null);
        notes_text.setText(null);

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        textDate.setText(sdf.format(myCalendar.getTime()));
    }
    }


    //Performing action onItemSelected and onNothing selected
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getApplicationContext(), MeasureTypes[position], Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

