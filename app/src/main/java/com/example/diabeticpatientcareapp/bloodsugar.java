package com.example.diabeticpatientcareapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diabeticpatientcareapp.helper.inputValidation;
import com.example.diabeticpatientcareapp.model.bloodsugar_model;
import com.example.diabeticpatientcareapp.utils.MyDividerItemDecoration;
import com.example.diabeticpatientcareapp.utils.RecyclerTouchListener;
import com.example.diabeticpatientcareapp.view.bloodsugarAdapter;

public class bloodsugar extends AppCompatActivity implements View.OnClickListener {
    EditText e1,e2,e3;
    private EditText textDate4;
    Button b1,b2;
   // String[]testTypes = {"Before breakfast","Lunch","Before Dinner","Before sleep","Fasting","Other"};
   private bloodsugarAdapter mAdapter;

    private DatabaseHelper db;
    private bloodsugar_model bloodsugarModel;
    private TextInputLayout textInputLayoutResults;
    private TextInputLayout textInputLayoutTesttype;
    private inputValidation inputvalidation;
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodsugar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
//        setSupportActionBar(toolbar);
        e1=(EditText)findViewById(R.id.testType_text);
        e2=(EditText)findViewById(R.id.notes_text);
        e3=(EditText)findViewById(R.id.results_text);
        textDate4 = (EditText)findViewById(R.id.in_date2);
        b1=(Button)findViewById(R.id.btn_save);
        b2=(Button)findViewById(R.id.btnShow);
        textInputLayoutTesttype=(TextInputLayout)findViewById(R.id.textInputLayoutTesttype);
        textInputLayoutResults=(TextInputLayout)findViewById(R.id.textInputLayoutResults);

//
        initObjects();
        initListeners();
    }
    private void initObjects(){
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        textDate4.setOnClickListener(this);
    }

    private void initListeners(){
        inputvalidation = new inputValidation(this);
        db= new DatabaseHelper(this);
        bloodsugarModel = new bloodsugar_model();
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
        new DatePickerDialog(bloodsugar.this, myDate, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_save:
                postDataToSQLite();
                break;

            case R.id.btnShow:
                Intent accountsIntent = new Intent(this,bloodsugar_list.class);
                accountsIntent.putExtra("TESTTYPE",e1.getText().toString().trim());
                accountsIntent.putExtra("RESULTS",e3.getText().toString().trim());
                accountsIntent.putExtra("NOTE",e2.getText().toString().trim());
                accountsIntent.putExtra("DATE", textDate4.getText().toString().trim());
                // accountsIntent.putExtra("DATE", textDate.getText().toString().trim());
                emptyEditText();
                startActivity(accountsIntent);
                break;

            case R.id.in_date2:
                datePic();
                break;
        }



    }

    private void postDataToSQLite(){
        if (!inputvalidation.isEditTextFilled(e1,textInputLayoutTesttype, "Enter type of test done")){
            return;
        }
        if(!inputvalidation.isEditTextFilled(e3,textInputLayoutResults,"Enter your results")){
            return;
        }
    else{

            bloodsugarModel.setMeasured(e1.getText().toString().trim());
            bloodsugarModel.setResults(Integer.parseInt(e3.getText().toString().trim()));
            bloodsugarModel.setNote(e2.getText().toString().trim());

            db.bloodsugarInsert(e1.getText().toString(), Integer.parseInt(e3.getText().toString()), e2.getText().toString(),textDate4.getText().toString());

            //snack bar to show success message that record saved successfully
            Intent accountsIntent = new Intent(this, bloodsugar_list.class);
            Toast.makeText(this, "Successful entry!", Toast.LENGTH_SHORT).show();
            accountsIntent.putExtra("TESTTYPE", e1.getText().toString().trim());
            accountsIntent.putExtra("RESULTS", e3.getText().toString().trim());
            accountsIntent.putExtra("NOTE", e2.getText().toString().trim());
            accountsIntent.putExtra("DATE", textDate4.getText().toString().trim());

            emptyEditText();
            startActivity(accountsIntent);
        }

    }

    private void emptyEditText() {
        e1.setText(null);
        e3.setText(null);
        e2.setText(null);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        textDate4.setText(sdf.format(myCalendar.getTime()));
    }



    //spinner element
      //  Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //spinner click listener
       // spinner.setOnItemSelectedListener(this);
        //Spinner drop down elements
       // List&lt;String&gt; categories = new ArrayList&lt;String&gt;();
        //categories.add("Automobile");
        //categories.add("Business Services");
        //categories.add("Computers");
        //categories.add("Education");
        //categories.add("Personal");
        //categories.add("Travel");

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
       // Spinner spin = (Spinner) findViewById(R.id.spinner);
        //spin.setOnItemSelectedListener(this);
//Creating the ArrayAdapter instance having the test Types list
        //ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,testTypes);
        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        //spin.setAdapter(aa);



//
//    private void showActionsDialog(final int position) {
//        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Choose option");
//        builder.setItems(colors, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which == 0) {
//                    showNote_measure_dialog(true, notesList.get(position), position);
//                } else {
//                    deleteNote(position);
//                }
//            }
//        });
//        builder.show();
//
//    }
//    /**
//     * Shows alert dialog with EditText options to enter / edit
//     * a note.
//     * when shouldUpdate=true, it automatically displays old note and changes the
//     * button text to UPDATE
//     */
//
//    private void showNote_measure_dialog(final boolean shouldUpdate, final bloodsugar_model bloodsugar_model, final int position) {
//        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
//        View view = layoutInflaterAndroid.inflate(R.layout.note_measure_dialog, null);
//
//        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(bloodsugar.this);
//        alertDialogBuilderUserInput.setView(view);
//
//        final EditText inputNote = view.findViewById(R.id.note);
//        final EditText inputMeasured = view.findViewById(R.id.measure);
//        final EditText inputResults = view.findViewById(R.id.results4);
//        TextView dialogTitle = view.findViewById(R.id.dialog_title);
//        TextView dialogMeasure = view.findViewById(R.id.dialog_measure);
//        TextView dialogResults = view.findViewById(R.id.dialog_results);
//        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_note_title) : getString(R.string.lbl_edit_note_title));
//        dialogMeasure.setText(!shouldUpdate ? getString(R.string.lbl_new_measure) : getString(R.string.lbl_edit_measure));
//        dialogResults.setText(!shouldUpdate ? getString(R.string.lbl_new_results) : getString(R.string.lbl_edit_results));
//        if (shouldUpdate && bloodsugar_model != null) {
//            inputNote.setText(bloodsugar_model.getNote());
//            inputMeasured.setText(bloodsugar_model.getMeasured());
//            inputResults.setText(bloodsugar_model.getResults());
//        }
//        alertDialogBuilderUserInput
//                .setCancelable(false)
//                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialogBox, int id) {
//
//                    }
//                })
//                .setNegativeButton("cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialogBox, int id) {
//                                dialogBox.cancel();
//                            }
//                        });
//        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
//        alertDialog.show();
//
//        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Show toast message when no text is entered
//                if (TextUtils.isEmpty(inputNote.getText().toString())) {
//                    Toast.makeText(bloodsugar.this, "Enter note!", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//                    alertDialog.dismiss();
//                }
//
//                // check if user updating note
//                if (shouldUpdate && bloodsugar_model != null) {
//                    // update note by it's id
//                    updateNote(inputNote.getText().toString(), position);
//                } else {
//                    // create new note
//                    createNote(inputNote.getText().toString());
//                }
//
//                if (TextUtils.isEmpty(inputMeasured.getText().toString())) {
//                    Toast.makeText(bloodsugar.this, "Enter when you took the measure!", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//                    alertDialog.dismiss();
//                }
//
//                // check if user updating measured
//                if (shouldUpdate && bloodsugar_model != null) {
//                    // update note by it's id
//                    updateMeasured(inputMeasured.getText().toString(), position);
//                } else {
//                    // create new note
//                    createNote(inputMeasured.getText().toString());
//                }
//
//                if (TextUtils.isEmpty(inputResults.getText().toString())) {
//                    Toast.makeText(bloodsugar.this, "Enter your results!", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//                    alertDialog.dismiss();
//                }
//
//                // check if user updating results
//                if (shouldUpdate && bloodsugar_model != null) {
//                    // update note by it's id
//                    updateResults(Integer.parseInt(inputResults.getText().toString()), position);
//                } else {
//                    // create new note
//                    createNote(inputResults.getText().toString());
//                }
//
//            }
//        });
//
//    }
    //Performing action onItemSelected and onNothing selected
    //@Override
    //public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      //  Toast.makeText(getApplicationContext(), testTypes[position], Toast.LENGTH_LONG).show();
    //}

    //@Override
    //public void onNothingSelected(AdapterView<?> parent) {
// TODO Auto-generated method stub
   // }
}
