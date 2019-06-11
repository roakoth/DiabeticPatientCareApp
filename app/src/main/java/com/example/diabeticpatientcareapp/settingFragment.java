 package com.example.diabeticpatientcareapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionButton;

 public class settingFragment extends Fragment {

     View view;
     FloatingActionButton fab;
     @Nullable
     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.activity_setting_fragment,container,false);

         final View mRelativelayout = (RelativeLayout) inflater.inflate(R.layout.activity_setting_fragment,container,false);
         fab = (com.github.clans.fab.FloatingActionButton) mRelativelayout.findViewById(R.id.fab_button2);
         fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 // Intent intent = new Intent(getActivity(), add_reminder.class);
                 Intent intent = new Intent(getActivity(), SettingsActivity.class);
                 startActivity(intent);
             }
         });

         return mRelativelayout;}

     }




