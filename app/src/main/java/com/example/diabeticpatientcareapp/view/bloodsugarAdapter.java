package com.example.diabeticpatientcareapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diabeticpatientcareapp.CustomItemClicklistener;
import com.example.diabeticpatientcareapp.DetailActivity;
import com.example.diabeticpatientcareapp.R;
import com.example.diabeticpatientcareapp.blood_detailActivity;
import com.example.diabeticpatientcareapp.model.bloodsugar_model;
import com.example.diabeticpatientcareapp.model.medication_model;

import java.util.List;

public class bloodsugarAdapter extends RecyclerView.Adapter<bloodsugarAdapter.bloodsugar_modelViewHolder> {
    private List<bloodsugar_model> listbloodsugar_model;
    private Context mContext;

    public bloodsugarAdapter(List<bloodsugar_model> listbloodsugar_model, Context mContext){
        this.listbloodsugar_model = listbloodsugar_model;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public bloodsugar_modelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflating recycler item view
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_blood_recycler, viewGroup,false);
        return new bloodsugar_modelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final bloodsugar_modelViewHolder holder, int position) {
        holder.textviewTestType.setText(listbloodsugar_model.get(position).getMeasured());
        holder.textviewResults.setText(String.valueOf(listbloodsugar_model.get(position).getResults()));
        holder.textviewNote3.setText(listbloodsugar_model.get(position).getNote());
        holder.textviewTimestamp2.setText(listbloodsugar_model.get(position).getDate());

        //handle item clicks
        holder.setItemClickListener(new CustomItemClicklistener() {
            @Override
            public void onItemClick(View v, int position) {
                //opens detail activity and pass data
                Intent i = new Intent(mContext, blood_detailActivity.class);

                //load data
                i.putExtra("TESTTYPE",listbloodsugar_model.get(position).getMeasured());
                i.putExtra("RESULTS",listbloodsugar_model.get(position).getResults());
                i.putExtra("NOTE",listbloodsugar_model.get(position).getNote());
                i.putExtra("ID",listbloodsugar_model.get(position).getId());

                //start activity
                mContext.startActivity(i);

            }
        });
    }



    @Override
    public int getItemCount() {
        return listbloodsugar_model.size();
    }
    public class bloodsugar_modelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public AppCompatTextView textviewTestType;
        public AppCompatTextView textviewNote3;
        public AppCompatTextView textviewResults;
        public AppCompatTextView textviewTimestamp2;
        private CustomItemClicklistener itemClickListener2;



        public bloodsugar_modelViewHolder(@NonNull View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            textviewTestType = (AppCompatTextView) itemView.findViewById(R.id.textviewTestType);
            textviewResults = (AppCompatTextView) itemView.findViewById(R.id.textviewResults);
            textviewNote3 = (AppCompatTextView) itemView.findViewById(R.id.textviewNote3);
            textviewTimestamp2=(AppCompatTextView)itemView.findViewById(R.id.textviewTimestamp2);

        }

        public void onClick(View v) {

            this.itemClickListener2.onItemClick(v,getLayoutPosition());

        }

        public void setItemClickListener(CustomItemClicklistener ic){
            this.itemClickListener2 = ic;
        }
    }


}


