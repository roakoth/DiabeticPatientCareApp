package com.example.diabeticpatientcareapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diabeticpatientcareapp.CustomItemClicklistener;
import com.example.diabeticpatientcareapp.R;

import java.util.ArrayList;

public class helpAdapter extends RecyclerView.Adapter<helpAdapter.MyViewHolder>  {

    private Context mContext;
    private ArrayList<String> titleHelpList;
    private CustomItemClicklistener clickListener;

    public helpAdapter(Context mContext, ArrayList<String> titleHelpList, CustomItemClicklistener clickListener) {
        this.mContext = mContext;
        this.titleHelpList = titleHelpList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.titlehelp,parent,false);

        final MyViewHolder viewHolder = new MyViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(view,viewHolder.getPosition());
            }
        });
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title_helptext.setText(titleHelpList.get(position).replace("_"," "));
    }

    @Override
    public int getItemCount() {
        return titleHelpList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title_helptext;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title_helptext = (TextView) itemView.findViewById(R.id.title_helptext);
        }
    }
}
