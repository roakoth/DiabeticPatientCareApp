package com.example.diabeticpatientcareapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diabeticpatientcareapp.R;
import com.example.diabeticpatientcareapp.model.profile_model;

import java.util.List;

public class profileAdapter extends RecyclerView.Adapter<profileAdapter.profile_modelViewHolder> {
    private List<profile_model> listprofile_model;
    private Context mContext;

    public profileAdapter(List<profile_model> listprofile_model, Context mContext) {
        this.listprofile_model = listprofile_model;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public profile_modelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_profile_recycler, viewGroup,false);
        return new profile_modelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final profile_modelViewHolder holder, int position) {

        holder.textviewName.setText(listprofile_model.get(position).getName());
        holder.textviewGender.setText(listprofile_model.get(position).getGender());
        holder.textviewDiabetesType.setText(listprofile_model.get(position).getDiabetesType());
    }

    @Override
    public int getItemCount() {
        return listprofile_model.size();
    }

    public class profile_modelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public AppCompatTextView textviewName;
        public AppCompatTextView textviewGender;
        public AppCompatTextView textviewDiabetesType;

        public profile_modelViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textviewName = (AppCompatTextView)itemView.findViewById(R.id.textviewName);
            textviewGender = (AppCompatTextView)itemView.findViewById(R.id.textviewGender);
            textviewDiabetesType = (AppCompatTextView)itemView.findViewById(R.id.textviewDiabetesType);
        }


        @Override
        public void onClick(View v) {


        }
    }
}
