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
import com.example.diabeticpatientcareapp.DetailActivity2;
import com.example.diabeticpatientcareapp.R;
import com.example.diabeticpatientcareapp.model.meal_model;

import java.util.List;

public class mealAdapter extends RecyclerView.Adapter<mealAdapter.meal_modelViewHolder> {
    private List<meal_model> listmeal_model;
    private Context mContext;

    public mealAdapter(List<meal_model> listmeal_model, Context mContext){
        this.listmeal_model = listmeal_model;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public meal_modelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflating recycler item view
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_diet_recycler, viewGroup,false);
        return new meal_modelViewHolder(itemView);
    }

    public void onBindViewHolder(final meal_modelViewHolder holder, int position) {
        holder.textviewMealType.setText(listmeal_model.get(position).getMealType());
        holder.textviewFood.setText(listmeal_model.get(position).getFood());
        holder.textviewNote2.setText(listmeal_model.get(position).getNote());
        holder.textviewTimestamp1.setText(listmeal_model.get(position).getDate());


//handle item clicks
        holder.setItemClickListener(new CustomItemClicklistener() {
            @Override
            public void onItemClick(View v, int position) {
                //opens detail activity and pass data
                Intent i = new Intent(mContext, DetailActivity2.class);

                //load data
                i.putExtra("MEALTYPE",listmeal_model.get(position).getMealType());
                i.putExtra("FOOD",listmeal_model.get(position).getFood());
                i.putExtra("NOTE",listmeal_model.get(position).getNote());
                i.putExtra("ID",listmeal_model.get(position).getId());

                //start activity
                mContext.startActivity(i);

            }
        });


    }


    @Override
    public int getItemCount() {
        return listmeal_model.size(); }

        public class meal_modelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public AppCompatTextView textviewMealType;
            public AppCompatTextView textviewNote2;
            public AppCompatTextView textviewFood;
            public AppCompatTextView textviewTimestamp1;
            private CustomItemClicklistener itemClickListener;


            public meal_modelViewHolder (@NonNull View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                textviewMealType = (AppCompatTextView) itemView.findViewById(R.id.textviewMealType);
                textviewFood = (AppCompatTextView) itemView.findViewById(R.id.textviewFood);
                textviewNote2 = (AppCompatTextView) itemView.findViewById(R.id.textviewNote2);
                textviewTimestamp1=(AppCompatTextView)itemView.findViewById(R.id.textviewTimestamp1);
            }


            @Override
            public void onClick(View v) {
                this.itemClickListener.onItemClick(v,getLayoutPosition());
            }

            public void setItemClickListener(CustomItemClicklistener ic){
                this.itemClickListener = ic;
            }
        }
}




