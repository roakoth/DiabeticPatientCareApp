package com.example.diabeticpatientcareapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.diabeticpatientcareapp.CustomItemClicklistener;
import com.example.diabeticpatientcareapp.DetailActivity;
import com.example.diabeticpatientcareapp.DetailActivity2;
import com.example.diabeticpatientcareapp.R;
import com.example.diabeticpatientcareapp.model.exercise_model;

import java.util.List;

public class exerciseAdapter extends RecyclerView.Adapter<exerciseAdapter.exercise_modelViewHolder> {
    private List<exercise_model> listexercise_model;
    private Context mContext;


    public exerciseAdapter(List<exercise_model> listexercise_model, Context mContext){
        this.listexercise_model = listexercise_model;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public exercise_modelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflating recycler item view
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_exercise_recycler, viewGroup,false);
        return new exercise_modelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final exercise_modelViewHolder holder, int position) {
holder.textviewExerciseType.setText(listexercise_model.get(position).getExerciseType());
holder.textviewDuration.setText(String.valueOf(listexercise_model.get(position).getDuration()));
holder.textviewNote1.setText(listexercise_model.get(position).getNote());
        holder.textviewTimestamp3.setText(listexercise_model.get(position).getDate());

//handle item clicks
        holder.setItemClickListener(new CustomItemClicklistener() {
            @Override
            public void onItemClick(View v, int position) {
     //opens detail activity and pass data
                Intent i = new Intent(mContext, DetailActivity.class);

                //load data
                i.putExtra("EXERCISETYPE",listexercise_model.get(position).getExerciseType());
                i.putExtra("DURATION",listexercise_model.get(position).getDuration());
                i.putExtra("NOTE",listexercise_model.get(position).getNote());
                i.putExtra("ID",listexercise_model.get(position).getId());

                //start activity
                mContext.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listexercise_model.size();
    }

    public static class exercise_modelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public AppCompatTextView textviewExerciseType;
        public AppCompatTextView textviewDuration;
        public AppCompatTextView textviewNote1;
        public AppCompatTextView textviewTimestamp3;
        private CustomItemClicklistener itemClickListener2;




        public exercise_modelViewHolder(@NonNull View itemView
        ) {
            super(itemView);
            itemView.setOnClickListener(this);
            textviewExerciseType = (AppCompatTextView) itemView.findViewById(R.id.textviewExerciseType);
            textviewDuration = (AppCompatTextView) itemView.findViewById(R.id.textviewDuration);
            textviewNote1 = (AppCompatTextView) itemView.findViewById(R.id.textviewNote1);
            textviewTimestamp3=(AppCompatTextView)itemView.findViewById(R.id.textviewTimestamp3);

//
        }

        @Override
        public void onClick(View v) {

            this.itemClickListener2.onItemClick(v,getLayoutPosition());

        }

        public void setItemClickListener(CustomItemClicklistener ic){
            this.itemClickListener2 = ic;
        }
    }


}
