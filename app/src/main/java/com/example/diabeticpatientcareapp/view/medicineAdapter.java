package com.example.diabeticpatientcareapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.diabeticpatientcareapp.R;
import com.example.diabeticpatientcareapp.model.medication_model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class medicineAdapter extends RecyclerView.Adapter<medicineAdapter.medication_modelViewHolder> {
    private List<medication_model> listMedication_model;
    private Context mContext;

    public medicineAdapter(List<medication_model> listMedication_model, Context mContext) {
        this.listMedication_model = listMedication_model;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public medication_modelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflating recycler item view
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_medicine_recycler, viewGroup,false);
        return new medication_modelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final medication_modelViewHolder holder, int position) {


        holder.textviewMedication.setText(listMedication_model.get(position).getMedication_name());
        holder.textviewNote.setText(listMedication_model.get(position).getNote());
        holder.textviewUnitMeasure.setText(listMedication_model.get(position).getMeasure());
        holder.textviewDosage.setText(String.valueOf(listMedication_model.get(position).getDosage()));
        holder.textviewTimestamp.setText(listMedication_model.get(position).getDate());


    }


    @Override
    public int getItemCount() {
        return listMedication_model.size();
    }

    public class medication_modelViewHolder extends RecyclerView.ViewHolder{
        public AppCompatTextView textviewMedication;
        public AppCompatTextView textviewUnitMeasure;
        public AppCompatTextView textviewDosage;
        public AppCompatTextView textviewNote;
        public AppCompatTextView textviewTimestamp;

        public medication_modelViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewMedication = (AppCompatTextView) itemView.findViewById(R.id.textviewMedication);
            textviewUnitMeasure = (AppCompatTextView) itemView.findViewById(R.id.textviewUnitMeasure);
            textviewDosage = (AppCompatTextView) itemView.findViewById(R.id.textviewDosage);
            textviewNote = (AppCompatTextView) itemView.findViewById(R.id.textviewNote);
            textviewTimestamp=(AppCompatTextView)itemView.findViewById(R.id.textviewTimestamp);
        }
    }
    }









