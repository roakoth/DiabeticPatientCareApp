package com.example.diabeticpatientcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diabeticpatientcareapp.helper.inputValidation;
import com.example.diabeticpatientcareapp.model.profile_model;
import com.example.diabeticpatientcareapp.view.profileAdapter;

public class ManageprofileFragment extends Fragment implements View.OnClickListener {
    EditText e1, e2, e3;
    Button b1, b2;
    private profileAdapter pAdapter;
    private DatabaseHelper db;
    private profile_model profileModel;
    private TextInputLayout textInputLayoutname;
    private TextInputLayout textInputLayoutGender;
    private inputValidation inputvalidation;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.fragment_manageprofile,container,false);

        final View mRelativelayout = inflater.inflate(R.layout.fragment_manageprofile, container, false);

        e1 = (EditText) mRelativelayout.findViewById(R.id.profileName);
        e2 = (EditText) mRelativelayout.findViewById(R.id.choose_gender);
        e3 = (EditText) mRelativelayout.findViewById(R.id.diabetes_type);
        b1 = (Button) mRelativelayout.findViewById(R.id.btn_saveProfile);
        b2 = (Button) mRelativelayout.findViewById(R.id.btnProfileShow);
        textInputLayoutname = (TextInputLayout) mRelativelayout.findViewById(R.id.textInputLayoutName);
        textInputLayoutGender = (TextInputLayout) mRelativelayout.findViewById(R.id.textInputLayoutGender);


        initObjects();
        initListeners();


        return mRelativelayout;

    }

    private void initObjects() {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    private void initListeners() {
        inputvalidation = new inputValidation(getActivity());
        db = new DatabaseHelper(getContext());
        profileModel = new profile_model();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_saveProfile:
                postDataToSQLite();
                break;

            case R.id.btnProfileShow:
                Intent accountsIntent = new Intent(getActivity(), profile_list.class);
                accountsIntent.putExtra("NAME", e1.getText().toString().trim());
                accountsIntent.putExtra("GENDER", e2.getText().toString().trim());
                accountsIntent.putExtra("DIABETESTYPE", e3.getText().toString().trim());
                emptyEditText();
                startActivity(accountsIntent);
                break;
        }
    }

    private void postDataToSQLite() {
        if (!inputvalidation.isEditTextFilled(e1, textInputLayoutname, "Enter your name")) {
            return;
        }
        if (!inputvalidation.isEditTextFilled(e2, textInputLayoutGender, "Enter your gender")) {
            return;
        } else {
            profileModel.setName(e1.getText().toString().trim());
            profileModel.setGender(e2.getText().toString().trim());
            profileModel.setDiabetesType(e3.getText().toString().trim());

            boolean insert = db.profileInsert(e1.getText().toString(), e2.getText().toString(), e3.getText().toString());
            Intent accountsIntent = new Intent(getActivity(), profile_list.class);
            Toast.makeText(getActivity(), "Profile is created successfully!", Toast.LENGTH_SHORT).show();
            accountsIntent.putExtra("NAME", e1.getText().toString().trim());
            accountsIntent.putExtra("GENDER", e2.getText().toString().trim());
            accountsIntent.putExtra("DIABETESTYPE", e3.getText().toString().trim());
            emptyEditText();
            startActivity(accountsIntent);

        }


    }

    private void emptyEditText() {
        e1.setText(null);
        e2.setText(null);
        e3.setText(null);
    }

}

