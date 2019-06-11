package com.example.diabeticpatientcareapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import static android.widget.Toast.*;

public class DailymonitorFragment extends Fragment {
    FloatingActionButton f1;
    FloatingActionButton f2;
    FloatingActionButton f3;
    FloatingActionButton f4;
    //View view;
//    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // view= inflater.inflate(R.layout.fragment_dailymonitor,container,false);
       // Log.d("Tag","Huurray !");
        final RelativeLayout mRelativelayout = (RelativeLayout) inflater.inflate(R.layout.fragment_dailymonitor,container,false);
        f1=(com.github.clans.fab.FloatingActionButton)mRelativelayout.findViewById(R.id.fab_bloodsugar);
        //f1.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
               // Toast.makeText(getActivity(),"Blood sugar",Toast.LENGTH_SHORT).show();
              //  Intent intent = new Intent(getActivity(), bloodsugar.class);
                //startActivity(intent);
            //}
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), bloodsugar.class);
                startActivity(intent);
            }
        });

        f2=(com.github.clans.fab.FloatingActionButton)mRelativelayout.findViewById(R.id.fab_medicine);
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), medicine.class);
                startActivity(intent);
            }
        });


       // f2=(com.github.clans.fab.FloatingActionButton)view.findViewById(R.id.fab_medicine);
       // f2.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {
             //   Toast.makeText(getActivity(),"Medicine",Toast.LENGTH_SHORT).show();
          //  }
      //  });

        f3=(com.github.clans.fab.FloatingActionButton)mRelativelayout.findViewById(R.id.fab_exercise);
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), exercise.class);
                startActivity(intent);
            }
        });

        f4=(com.github.clans.fab.FloatingActionButton)mRelativelayout.findViewById(R.id.fab_diet);
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), meal.class);
                startActivity(intent);
            }
        });
       // f3=(com.github.clans.fab.FloatingActionButton)view.findViewById(R.id.fab_exercise);
     //   f3.setOnClickListener(new View.OnClickListener() {
          //  @Override
         //   public void onClick(View v) {
           //     Toast.makeText(getActivity(),"Exercise",Toast.LENGTH_SHORT).show();
         //   }
    //    });

       // f4=(com.github.clans.fab.FloatingActionButton)view.findViewById(R.id.fab_diet);
       // f4.setOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View v) {
              //  Toast.makeText(getActivity(),"Diet",Toast.LENGTH_SHORT).show();
           // }
       // });




        return  mRelativelayout;
    }



}